package logs;

import java.time.ZonedDateTime;

import static core.statics.DateTimeInfo.DEFAULT_FORMATTER;
import static core.statics.DateTimeInfo.ZONE_ID;


public abstract class AbstractLogWriter {

    final boolean saveLogs;

    AbstractLogWriter(boolean save) { saveLogs = save; }

    public abstract void writeLogs(String... args);

    String createMessage(String... args) {
        String message = String.format("[%s]:",
                ZonedDateTime
                        .now(ZONE_ID)
                        .format(DEFAULT_FORMATTER));

        StringBuilder sb = new StringBuilder(message);

        for (String arg : args) {
            sb.append("\n ");
            sb.append(arg);
        }

        return sb.toString();
    }
}
