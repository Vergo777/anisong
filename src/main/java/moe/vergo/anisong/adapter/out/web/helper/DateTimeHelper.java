package moe.vergo.anisong.adapter.out.web.helper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    private DateTimeHelper() {}

    public static Instant rfcFormatStringToInstant(String isoFormatString) {
        return Instant.from(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(isoFormatString));
    }

    public static Instant simpleDateFormatStringToInstant(String simpleDateString) {
        LocalDate localDate = LocalDate.parse(simpleDateString);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.toInstant(ZoneOffset.UTC);
    }
}
