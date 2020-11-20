package moe.vergo.anisong.domain;

import java.time.Instant;

public class AnisongEvent {
    private final String description;
    private final String url;
    private final Instant startTime;
    private final Instant endTime;

    public AnisongEvent(String description, String url, Instant startTime, Instant endTime) {
        this.description = description;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
