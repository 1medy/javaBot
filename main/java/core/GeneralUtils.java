package core;

import core.statics.BotInfo;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;


public class GeneralUtils {
    public static Color getColor(Guild guild) {
        Member member = guild.getMemberById(BotInfo.ID);

        return (member != null) ? member.getColor() : Color.WHITE;
    }

    public static boolean isBotCalled(String message) {
        return (message.startsWith(String.format("<@!%s>", BotInfo.ID)) ||
                message.startsWith(BotInfo.NAME + " "));
    }

    public static boolean isMe(User user) { return user.getId().equals(BotInfo.ID); }
}
