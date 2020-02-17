package logs;

import net.dv8tion.jda.api.entities.TextChannel;


public class DiscordLogWriter extends AbstractLogWriter {

    private final TextChannel logChannel;

    public DiscordLogWriter(TextChannel channel, boolean save) {
        super(save);
        logChannel = channel;
    }

    @Override
    public void writeLogs(String... args) {
        if (!saveLogs) return;

        logChannel.sendMessage(createMessage(args)).queue();
    }
}
