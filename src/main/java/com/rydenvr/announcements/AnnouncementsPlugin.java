
package com.rydenvr.announcements;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AnnouncementsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RydenAnnouncements enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("announce")) {
            return false;
        }

        if (!sender.hasPermission("rydenannouncements.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /announce <message>");
            return true;
        }

        String message = String.join(" ", args);

        String announcement =
            ChatColor.GOLD + "" + ChatColor.BOLD + "[GLOBAL ANNOUNCEMENT] "
            + ChatColor.YELLOW + message;

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(announcement);
        }

        Bukkit.getConsoleSender().sendMessage(announcement);
        return true;
    }
}
