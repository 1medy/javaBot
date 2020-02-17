package listeners.emoteListener;

import actions.ActionResult;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

import static core.GeneralUtils.getColor;


public class EmoteListener extends ListenerAdapter {

    private static final HashMap<String, String> trackedEmotes;
    private static final Validator validator;

    static {
        validator = new MemberValidator();
        validator.setNext(new EmoteValidator())
                 .setNext(new BotReactionValidator());

        trackedEmotes = new HashMap<>();

        for (ActionResult value : ActionResult.values()) {
            trackedEmotes.put(value.getEmote(), value.getDescription());
        }
    }

    static Map<String, String> getTrackedEmotes() { return trackedEmotes; }

    @Override
    public void onMessageReactionAdd(@Nonnull MessageReactionAddEvent event) {
        boolean success = validator.validate(event);

        if (!success) return;

        String emote = event.getReactionEmote().getEmoji();
        MessageEmbed embed = embedEmoteDescription(emote, event);

        event.getChannel().sendMessage(embed).queue();
    }

    private MessageEmbed embedEmoteDescription(String emote, @Nonnull MessageReactionAddEvent event) {
        return new EmbedBuilder()
                        .setColor(getColor(event.getGuild()))
                        .addField("emote", emote, true)
                        .addField("description", trackedEmotes.get(emote), true)
                        .build();
    }
}
