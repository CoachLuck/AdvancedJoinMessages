/*
 *     File: Utils.java
 *     Last Modified: 6/20/20, 9:37 PM
 *     Project: AdvancedJoinMessages
 *     Copyright (C) 2020 CoachL_ck
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.coachluck.advancedjoinmessages.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Utils {

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
