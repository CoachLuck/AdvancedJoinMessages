package io.github.coachluck.advancedjoinmessages;

import io.github.coachluck.advancedjoinmessages.Utils.JsonMessage;
import io.github.coachluck.advancedjoinmessages.Utils.Util;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;


public class JoinLeaveListener implements Listener {
    private final Main plugin;

    public JoinLeaveListener() {
        plugin = Main.getPlugin(Main.class);
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
        sendMessage(e.getPlayer(), true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeave(PlayerQuitEvent e) {
        if(!plugin.getConfig().getBoolean("Messages.Leave.Enabled")) {
            if(plugin.getConfig().getBoolean("General.Remove-Default-Message")) {
                e.setQuitMessage(null);
            }
            return;
        }
        e.setQuitMessage(null);
        sendMessage(e.getPlayer(), false);
    }

    /**
     * Sends the desired message to the player
     * @param player The player to send the message too
     * @param onJoin True if onJoinEvent, false if onQuitEvent
     */
    private void sendMessage(Player player, boolean onJoin) {
        String mainPath = "Messages.";
        List<String> parts;

        if(onJoin) {
            mainPath = mainPath + "Join.";
            parts = plugin.joinMessage;
        }
        else {
            mainPath = mainPath + "Leave.";
            parts = plugin.leaveMessage;
        }

        JsonMessage finalMessage = new JsonMessage();
        int i = 0;
        for(String s : parts) {
            final String str = PlaceholderAPI.setPlaceholders(player, s);
            final String path = mainPath + "ids." + i + ".";

            boolean hover = plugin.getConfig().getBoolean(path + "Hover.Enabled");
            boolean click = plugin.getConfig().getBoolean(path + "Click.Enabled");
            final String hoverText = Util.format(plugin.getConfig().getString(path + "Hover.Text").replaceAll("%player%", player.getDisplayName()));
            final String clickType = plugin.getConfig().getString(path + "Click.Type");
            String data = plugin.getConfig().getString(path + "Click.Data").replaceAll("%player%", player.getName());

            if(hover && click) {
                if(clickType.startsWith("O") || clickType.startsWith("o")) {
                    finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsURL(data).save();
                }
                else {
                    data = Util.format(data);
                    if(clickType.startsWith("S") || clickType.startsWith("s")) {
                        finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsSuggestCmd(data).save();
                    } else {
                        finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsExecuteCmd(data).save();
                    }
                }
            }
            else if(click) {
                finalMessage.append(str).setHoverAsTooltip(hoverText);

            } else if(hover) {
                if(clickType.startsWith("O")) {
                    finalMessage.append(str).setClickAsURL(data).save();
                }
                else {
                    if(clickType.startsWith("s")) {
                        finalMessage.append(str).setClickAsSuggestCmd(data).save();
                    } else {
                        finalMessage.append(str).setClickAsExecuteCmd(data).save();
                    }
                }
            }
            i++;
        }
        finalMessage.send();
    }
}
