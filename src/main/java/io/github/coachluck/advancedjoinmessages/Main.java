package io.github.coachluck.advancedjoinmessages;

import io.github.coachluck.advancedjoinmessages.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Main extends JavaPlugin {

    public List<String> leaveMessage = new ArrayList<>();
    public List<String> joinMessage = new ArrayList<>();

    @Override
    public void onLoad() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new JoinLeaveListener();
            getCommand("ajm").setExecutor(new MainCommand());
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }

        leaveMessage = getMessageList("Messages.Leave.Text");
        joinMessage = getMessageList("Messages.Join.Text");
    }

    @Override
    public void onDisable() {

    }

    /**
     * Gets the Message list for the desired Join/Leave
     * @param path the path to the config value of the message
     * @return The list of elements to append
     */
    public List<String> getMessageList(String path) {
        String pattern1 = "<";
        String pattern2 = ">";
        String text = getConfig().getString(path);
        List<String> parts = new ArrayList<>();

        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
        Matcher m = p.matcher(text);
        while (m.find()) {
            parts.add(Util.format(m.group(1)));
        }
        return parts;
    }

    public void reloadMessages() {
        reloadConfig();
        leaveMessage = getMessageList("Messages.Leave.Text");
        joinMessage = getMessageList("Messages.Join.Text");
    }
}
