package actions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public interface Action {

    ActionResult perform(String[] args, MessageReceivedEvent event);

    String usage();
    String description();

    default String name() { return this.getClass().getSimpleName().toLowerCase(); }
}
