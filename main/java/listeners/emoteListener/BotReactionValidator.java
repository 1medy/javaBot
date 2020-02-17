package listeners.emoteListener;

import core.GeneralUtils;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.requests.restaction.pagination.ReactionPaginationAction;

import javax.annotation.Nonnull;
import java.util.Optional;


class BotReactionValidator extends Validator {

    @Override
    boolean validate(@Nonnull MessageReactionAddEvent event) {
        long id = event.getMessageIdLong();
        Message message =
                event.getChannel()
                        .retrieveMessageById(id)
                        .complete();

        if (!message.getAuthor().equals(event.getUser())) {
            return false;
        }

        String emote = event.getReactionEmote().getEmoji();

        ReactionPaginationAction users = getUsersReacted(message, emote);

        if (users == null) { return false; }

        return botPresentIn(users);
    }

    private ReactionPaginationAction getUsersReacted(Message message, String emote) {
        Optional<MessageReaction> reaction =
                message.getReactions()
                    .stream()
                    .filter(e -> e.getReactionEmote().getEmoji().equals(emote))
                    .findAny();

        return reaction.map(MessageReaction::retrieveUsers).orElse(null);
    }

    private boolean botPresentIn(ReactionPaginationAction users) {
        Optional<User> bot =
                users.stream()
                        .filter(GeneralUtils::isMe)
                        .findAny();

        return bot.isPresent();
    }
}
