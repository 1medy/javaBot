package listeners;

import actions.ActionResult;
import core.Bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

import static core.GeneralUtils.isBotCalled;


public class ActionListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw().trim();

        if (!isBotCalled(message)) return;

        String[] args = getArgsRaw(message);
        ActionResult result = Bot.performAction(args, event);
        result.response(event);
    }

    private String[] getArgsRaw(String message) {
        int fromIndex = message.indexOf(" ") + 1;

        return message.substring(fromIndex).trim().split("\\s+");
    }
}
