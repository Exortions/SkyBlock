package com.itech4kids.skyblock.Commands.Moderation;

import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Arrays;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length <= 1 || args.length >= 3) {
                player.sendMessage(ChatColor.RED + "Incorrect usage!\n/ban <player> <reason>");
                return true;
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String reason = String.join(" ", Arrays.asList(args).subList(1, args.length).toArray(new String[]{}));
                if (player.getServer().getOnlinePlayers().contains(target)) {
                    if (!(reason.equals("null"))) {
                        try {
                            Config.setBanned(target, true);
                            Config.setBanReason(target, reason);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            Config.setBanned(target, true);
                            Config.setBanReason(target, "None provided.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    target.kickPlayer(ChatColor.RED + "You are permanently banned from this server!\n\n" + ChatColor.GRAY + "Reason: " + ChatColor.WHITE + Config.getBanReason(player) + "\n" + ChatColor.GRAY + "Find out more: " + ChatColor.AQUA + "" + ChatColor.UNDERLINE + "https://www.hypixel.net/appeal\n\n" + ChatColor.GRAY + "Ban ID: " + ChatColor.WHITE + "#1379254\n" + ChatColor.GRAY + "Sharing your ban ID may affect the process of your appeal!");
                }
            }
        } else{
            player.sendMessage(MessageConfig.noPermission());
        }
        return false;
    }
}
