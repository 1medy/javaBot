package actions;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nonnull;


public class ActionHandler {

    public ActionResult performAction(String[] args, @Nonnull MessageReceivedEvent event) {
        Action action = parseAction(args);

        return (action == null)
                ? ActionResult.ACTION_NOT_FOUND
                : action.perform(args, event);
    }

    private Action parseAction(String[] args) {
        if (args.length == 0) {
            return null;
        }

        String name = args[0];
        return Actions.getMap().getOrDefault(name, null);
    }
}
