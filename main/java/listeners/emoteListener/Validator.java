package listeners.emoteListener;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;


abstract class Validator {
    Validator next;

    Validator setNext(Validator next) {
        this.next = next;
        return next;
    }

    abstract boolean validate(MessageReactionAddEvent event);
}
