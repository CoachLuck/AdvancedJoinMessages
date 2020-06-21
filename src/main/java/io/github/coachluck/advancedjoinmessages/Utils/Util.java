package io.github.coachluck.advancedjoinmessages.Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {
    /**
     * Colorizes a string
     * @param str the string to colorize
     * @return the formatted string
     */
    public static String format(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    /**
     * Sends a message to the command sender
     * @param sender the one executing the command
     * @param message the message to send them (will be formatted)
     */
    public static void sendMsg(CommandSender sender, String message) {
        sender.sendMessage(format(message));
    }
}
