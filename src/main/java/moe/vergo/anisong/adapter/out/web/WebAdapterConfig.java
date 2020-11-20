package moe.vergo.anisong.adapter.out.web;

import moe.vergo.anisong.application.port.out.AnisongEventDataPort;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebAdapterConfig {
    @Bean
    @ConfigurationProperties(prefix = "web.gcalendar.anisong", ignoreUnknownFields = false)
    public GCalendarAnisongEventDataProperties gCalendarAnisongEventDataProperties() {
        return new GCalendarAnisongEventDataProperties();
    }

    @Bean
    public AnisongEventDataPort anisongEventDataPort(RestTemplate restTemplate, GCalendarAnisongEventDataProperties gCalendarAnisongEventDataProperties) {
        return new GCalendarAnisongEventDataAdapter(restTemplate, gCalendarAnisongEventDataProperties);
    }

}
