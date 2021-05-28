package com.itech4kids.skyblock.Commands.Moderation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can run that command!");
            return true;
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please specify a player to kick!");
                return true;
            } else if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "Please specify a reason!");
                return true;
            } else if (args.length == 3) {
                player.sendMessage(ChatColor.RED + "Incorrect usage!\n/kick <player> <reason>");
                return true;
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String reason = args[1];
                if (player.getServer().getOnlinePlayers().contains(target)) {
                    target.kickPlayer(ChatColor.RED + "You have been kicked from \nHypixel SkyBlock!\nKicked by: " + ChatColor.GRAY + player.getName() + ChatColor.RED + "\nReason: " + ChatColor.GRAY + reason);
                } else {
                    player.sendMessage(ChatColor.RED + "That player isn't online!");
                    return true;
                }
            }
        }
        return false;
    }
}