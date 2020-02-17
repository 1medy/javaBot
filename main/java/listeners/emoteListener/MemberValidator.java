package listeners.emoteListener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import javax.annotation.Nonnull;


class MemberValidator extends Validator {

    @Override
    boolean validate(@Nonnull MessageReactionAddEvent event) {
        Member member = event.getMember();

        if (member == null || member.getUser().isBot()) {
            return false;
        }

        return next.validate(event);
    }
}
