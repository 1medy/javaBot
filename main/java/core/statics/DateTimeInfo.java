package core.statics;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeInfo {

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    public static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    private static ZonedDateTime launchDateTime;

    public static ZonedDateTime getLaunchDateTime() {
        return launchDateTime;
    }

    public static void setLaunchDateTime(ZonedDateTime zdt) {
        if (launchDateTime == null) { launchDateTime = zdt; }
    }
}
