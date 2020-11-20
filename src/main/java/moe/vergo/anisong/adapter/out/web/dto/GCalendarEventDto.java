package moe.vergo.anisong.adapter.out.web.dto;

public class GCalendarEventDto {
    private String htmlLink;
    private String summary;
    private String description;
    private GCalendarEventDateDto start;
    private GCalendarEventDateDto end;

    public String getHtmlLink() {
        return htmlLink;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public GCalendarEventDateDto getStart() {
        return start;
    }

    public GCalendarEventDateDto getEnd() {
        return end;
    }
}
