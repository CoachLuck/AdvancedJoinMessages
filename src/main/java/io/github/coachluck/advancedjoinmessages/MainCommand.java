package io.github.coachluck.advancedjoinmessages;

import io.github.coachluck.advancedjoinmessages.Utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Main plugin = Main.getPlugin(Main.class);
        if(!sender.hasPermission(plugin.getConfig().getString("General.Reload-Permission"))) {
            Util.sendMsg(sender, plugin.getConfig().getString("General.Permission-Message"));
        }
        plugin.reloadMessages();

        Util.sendMsg(sender, plugin.getConfig().getString("General.Reloaded-Message"));
        return true;
    }
}
