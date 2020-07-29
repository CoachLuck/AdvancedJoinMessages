/*
 *     File: JoinLeaveListener.java
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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;


public class JoinLeaveListener implements Listener {

    private final AJM plugin;

    public JoinLeaveListener() {
        plugin = AJM.getPlugin(AJM.class);
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent e) {
        if(!plugin.getConfig().getBoolean("Messages.Join.Enabled")) {
            if(plugin.getConfig().getBoolean("General.Remove-Default-Message")) {
                e.setJoinMessage(null);
            }
            return;
        }

        e.setJoinMessage(null);
        Player player = e.getPlayer();
        int i = 0;
        for(List<String> parts : plugin.joinMessage) {
            plugin.sendMessage(player, true, i, parts);
            i++;
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent e) {
        if(!plugin.getConfig().getBoolean("Messages.Leave.Enabled")) {
            if(plugin.getConfig().getBoolean("General.Remove-Default-Message")) {
                e.setQuitMessage(null);
            }
            return;
        }

        int i = 0;
        Player player = e.getPlayer();
        for(List<String> parts : plugin.leaveMessage) {
            plugin.sendMessage(player, false, i, parts);
            i++;
        }
        e.setQuitMessage(null);
    }
}
