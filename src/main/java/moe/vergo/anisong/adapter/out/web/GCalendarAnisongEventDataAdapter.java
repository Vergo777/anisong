package moe.vergo.anisong.adapter.out.web;

import moe.vergo.anisong.adapter.out.web.dto.GCalendarEventDateDto;
import moe.vergo.anisong.adapter.out.web.dto.GCalendarEventDto;
import moe.vergo.anisong.adapter.out.web.dto.GCalendarEventsResponse;
import moe.vergo.anisong.adapter.out.web.helper.DateTimeHelper;
import moe.vergo.anisong.application.port.out.AnisongEventDataPort;
import moe.vergo.anisong.domain.AnisongEvent;
import org.springframework.boot.system.SystemProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GCalendarAnisongEventDataAdapter implements AnisongEventDataPort {
    private static final String CALENDAR_EVENT_ENDPOINT = "https://www.googleapis.com/calendar/v3/calendars/";

    private final RestTemplate restTemplate;
    private final GCalendarAnisongEventDataProperties gCalendarAnisongEventDataProperties;

    public GCalendarAnisongEventDataAdapter(RestTemplate restTemplate, GCalendarAnisongEventDataProperties gCalendarAnisongEventDataProperties) {
        this.restTemplate = restTemplate;
        this.gCalendarAnisongEventDataProperties = gCalendarAnisongEventDataProperties;
    }

    @Override
    @Cacheable(value = "anisongEventDataCache", key = "#root.methodName")
    public List<AnisongEvent> getAnisongEventData() {
        List<GCalendarEventDto> anisongEventDataFromEndpoint = getAnisongEventDataFromEndpoint();

        return anisongEventDataFromEndpoint.stream()
                .map(e -> new AnisongEvent(
                        e.getSummary(),
                        e.getDescription(),
                        getExactEventTimeOrDefaultToUTC(e.getStart()),
                        getExactEventTimeOrDefaultToUTC(e.getEnd())
                ))
                .collect(Collectors.toList());
    }

    private Instant getExactEventTimeOrDefaultToUTC(GCalendarEventDateDto eventTime) {
        if(eventTime.getDateTime() != null) {
            return DateTimeHelper.rfcFormatStringToInstant(eventTime.getDateTime());
        } else {
            return DateTimeHelper.simpleDateFormatStringToInstant(eventTime.getDate());
        }
    }

    public List<GCalendarEventDto> getAnisongEventDataFromEndpoint() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", httpHeaders);

        String url = CALENDAR_EVENT_ENDPOINT +
                gCalendarAnisongEventDataProperties.getId() + "/" +
                "events?key=" + SystemProperties.get("apiKey") + "&" +
                "maxResults=2500&" +
                "timeMin=" + Instant.now().toString();

        System.out.println("Making request to endpoint");
        ResponseEntity<GCalendarEventsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, GCalendarEventsResponse.class);

        return Objects.requireNonNull(response.getBody()).getItems();
    }

}
