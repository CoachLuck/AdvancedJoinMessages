/*
 *     File: AJMCommand.java
 *     Last Modified: 7/28/20, 9:22 PM
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

package io.github.coachluck.advancedjoinmessages;

import io.github.coachluck.advancedjoinmessages.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AJMCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        AJM plugin = AJM.getPlugin(AJM.class);
        if(!sender.hasPermission(plugin.getConfig().getString("General.Reload-Permission"))) {
            ChatUtil.sendMsg(sender, plugin.getConfig().getString("General.Permission-Message"));
            return true;
        }
        plugin.reloadMessages();

        ChatUtil.sendMsg(sender, plugin.getConfig().getString("General.Reloaded-Message"));
        return true;
    }

}
