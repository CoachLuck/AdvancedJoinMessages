/*
 *     File: Main.java
 *     Last Modified: 7/28/20, 9:09 PM
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
import io.github.coachluck.advancedjoinmessages.utils.JsonMessage;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main extends JavaPlugin {

    public ArrayList<List<String>> joinMessage;
    public ArrayList<List<String>> leaveMessage;
    private boolean hasPapi = false;

    @Override
    public void onLoad() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            hasPapi = true;
        } else {
            Bukkit.getLogger().warning("PlaceholderAPI is not installed, it is highly recommended for this plugin!");
            hasPapi = false;
        }

        new JoinLeaveListener();
        getCommand("ajm").setExecutor(new MainCommand());
        compileMessages();
    }

    public void reloadMessages() {
        reloadConfig();
        joinMessage.clear();
        leaveMessage.clear();
        compileMessages();
    }

    private void compileMessages() {
        joinMessage = getFinalMessageList("Messages.Join.Text");
        leaveMessage = getFinalMessageList("Messages.Leave.Text");
    }

    public ArrayList<List<String>> getFinalMessageList(String path) {
        ArrayList<List<String>> finalMessage = new ArrayList<>();
        for(String line : getConfig().getStringList(path)) {
            List<String> parts = getMessageList(line);
            finalMessage.add(parts);
        }
        return finalMessage;
    }

    /**
     * Gets the Message list for the desired Join/Leave
     * @param line the to separate into parts
     * @return The list of elements to append
     */
    public List<String> getMessageList(String line) {
        String pattern1 = "<";
        String pattern2 = ">";
        List<String> parts = new ArrayList<>();

        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
        Matcher m = p.matcher(line);
        while (m.find()) {
            parts.add(ChatUtil.format(m.group(1)));
        }
        return parts;
    }

    /**
     * Sends the desired message to the player
     * @param player The player to send the message too
     * @param onJoin True if onJoinEvent, false if onQuitEvent
     */
    public void sendMessage(Player player, boolean onJoin, int id, List<String> parts) {
        String mainPath = "Messages.";

        if(onJoin) {
            mainPath = mainPath + "Join.";
        }
        else {
            mainPath = mainPath + "Leave.";
        }

        sendMessagePath(player, parts, mainPath, id);

    }

    private void sendMessagePath(Player player, List<String> parts, String mainPath, int id) {
        JsonMessage finalMessage = new JsonMessage();
        int i = 0;
        for(String s : parts) {
            String str;
            if(hasPapi) {
                str = PlaceholderAPI.setPlaceholders(player, s);
            } else {
                str = s.replaceAll("%player_name%", player.getName());
            }

            final String path = mainPath + "ids." + id + "." + i + ".";

            boolean hover = getConfig().getBoolean(path + "Hover.Enabled");
            boolean click = getConfig().getBoolean(path + "Click.Enabled");

            String hoverText = "";
            if(hover) {
                hoverText = getConfig().getString(path + "Hover.Text");
            }

            String clickType = "";
            String data = "";
            if(click){
                clickType = getConfig().getString(path + "Click.Type");
                data = getConfig().getString(path + "Click.Data");
            }

            if(!data.isEmpty()) {
                data = data.replaceAll("%player_name%", player.getName());
                data = ChatUtil.format(data);
            }

            if(!hoverText.isEmpty()) {
                hoverText = hoverText.replaceAll("%player_name%", player.getName());
                hoverText = ChatUtil.format(hoverText);
            }

            if(!clickType.isEmpty()) {
                clickType = clickType.toUpperCase();
            }

            // If both hover and click elements should be applied
            if(hover && click) {
                if(clickType.startsWith("O")) {
                    finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsURL(data).save();
                }
                else {
                    data = ChatUtil.format(data);
                    if(clickType.startsWith("S")) {
                        finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsSuggestCmd(data).save();
                    } else {
                        finalMessage.append(str).setHoverAsTooltip(hoverText).setClickAsExecuteCmd(data).save();
                    }
                }
            }

            // If only hover elements should be applied
            else if(hover) {
                finalMessage.append(str).setHoverAsTooltip(hoverText).save();
            }

            // if only click elements should be applied
            else if(click) {
                if(clickType.startsWith("O")) {
                    finalMessage.append(str).setClickAsURL(data).save();
                }
                else {
                    if(clickType.startsWith("S")) {
                        finalMessage.append(str).setClickAsSuggestCmd(data).save();
                    } else {
                        finalMessage.append(str).setClickAsExecuteCmd(data).save();
                    }
                }
            }
            else {
                finalMessage.append(str).save();
            }
            i++;
        }
        finalMessage.send();
    }
}