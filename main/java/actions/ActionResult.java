package actions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public enum ActionResult {
    NONE("", ""),

    ILLEGAL_ARGUMENT(
            "❓",
            "Missing/Invalid arguments. See usage with 'help [command name]'."),
    ACTION_NOT_FOUND(
            "❌",
            "Command not found. Use 'help' to get a list of all available commands."),
    ACCESS_DENIED(
            "⛔",
            "These aren't the commands you're looking for..."),
    SUCCESS(
            "✅",
            "Command performed successfully.");

    private final String emoteCode;
    private final String description;

    ActionResult(String emoteCode, String description) {
        this.emoteCode = emoteCode;
        this.description = description;
    }

    public String getEmote() { return emoteCode; }
    public String getDescription() { return description; }
    public String getName() {
        return this.toString().replaceAll("_", " ").toLowerCase();
    }

    public boolean isNull() { return this == ActionResult.NONE; }

    public void response(MessageReceivedEvent event) {
        if (this.isNull()) return;

        long id = event.getMessageIdLong();
        event.getChannel().addReactionById(id, emoteCode).queue();
    }
}