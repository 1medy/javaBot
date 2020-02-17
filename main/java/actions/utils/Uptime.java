package actions.utils;

import actions.Action;
import actions.ActionResult;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Instant;
import java.time.LocalTime;
import java.time.Period;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static core.statics.DateTimeInfo.getLaunchDateTime;
import static core.statics.DateTimeInfo.ZONE_ID;


public class Uptime implements Action {

    @Override
    public ActionResult perform(String[] args, MessageReceivedEvent event) {
        Instant launchTime = getLaunchDateTime().toInstant();
        Instant currentTime = Instant.now();

        LocalTime elapsedTime =
                currentTime
                        .minusMillis(launchTime.toEpochMilli())
                        .atZone(ZONE_ID)
                        .toLocalTime();

        Period elapsedPeriod = Period.between(
                launchTime.atZone(ZONE_ID).toLocalDate(),
                currentTime.atZone(ZONE_ID).toLocalDate());

        String message =
                parseDateTime(elapsedPeriod, elapsedTime);

        event.getChannel().sendMessage(message).queue();
        return ActionResult.SUCCESS;
    }

    @Override
    public String usage() { return "uptime"; }

    @Override
    public String description() {
        return "Shows online time.";
    }

    private String partialParse(int value, String one, String several) {
        if (value == 0) return null;

        return String.format("%s %s", value, (value % 10 == 1) ? one : several);
    }

    private String parseDateTime(Period period, LocalTime time) {
        String result =
                Stream.of(
                        partialParse(period.getYears(),  "year",   "years"),
                        partialParse(period.getMonths(), "month",  "months"),
                        partialParse(period.getDays(),   "day",    "days"),
                        partialParse(time.getHour(),     "hour",   "hours"),
                        partialParse(time.getMinute(),   "minute", "minutes"),
                        partialParse(time.getSecond(),   "second", "seconds"))
                        .filter(Objects::nonNull)
                        .collect(Collectors.joining(", "));

        return result.isEmpty()
                ? "Starting..."
                : result;
    }
}
