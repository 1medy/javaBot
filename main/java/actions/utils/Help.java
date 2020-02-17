package actions.utils;

import actions.Action;
import actions.Actions;
import actions.ActionResult;
import core.GeneralUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Map;


public class Help implements Action {

    @Override
    public ActionResult perform(String[] args, MessageReceivedEvent event) {

        MessageEmbed embed;

        if (args.length == 2) {
            Action action = Actions.getMap().get(args[1]);

            if (action == null) {
                return ActionResult.ILLEGAL_ARGUMENT;
            }

            embed = actionEmbed(event.getGuild(), action);
        } else {
            embed = defaultEmbed(event.getGuild());
        }

        event.getChannel().sendMessage(embed).queue();
        return ActionResult.NONE;
    }

    @Override
    public String usage() { return "help <command>"; }

    @Override
    public String description() {
        return "'help' with no arguments displays what I can do.\n" +
               "'help <command>' shows detailed description.";
    }

    private MessageEmbed defaultEmbed(Guild guild) {
        final Map<String, Action> actions = Actions.getMap();

        EmbedBuilder builder =
                new EmbedBuilder()
                        .setTitle("Help")
                        .setColor(GeneralUtils.getColor(guild));

        for (String name : actions.keySet()) {
            builder.addField(actions.get(name).usage(), "", true);
        }

        return builder.build();
    }

    private MessageEmbed actionEmbed(Guild guild, Action action) {
        return new EmbedBuilder()
                .setTitle("help: '" + action.name() + "'")
                .setColor(GeneralUtils.getColor(guild))
                .addField("info ", action.description(), true)
                .build();
    }
}
