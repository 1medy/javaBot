package listeners.emoteListener;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import javax.annotation.Nonnull;


class EmoteValidator extends Validator {

    @Override
    boolean validate(@Nonnull MessageReactionAddEvent event) {
        String emote = event.getReactionEmote().getEmoji();

        if (!EmoteListener.getTrackedEmotes().containsKey(emote)) {
            return false;
        }

        return next.validate(event);
    }
}
