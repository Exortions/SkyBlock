package com.itech4kids.skyblock.Commands.Moderation;

import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class KickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        if (player.isOp()) {
            if (args.length == 0) {
                player.sendMessage(MessageConfig.specifyPlayer());
                return true;
            } else if (args.length == 1) {
                player.sendMessage(MessageConfig.specifyReason());
                return true;
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                String reason = String.join(" ", Arrays.asList(args).subList(1, args.length).toArray(new String[]{}));
                if (player.getServer().getOnlinePlayers().contains(target)) {
                    target.kickPlayer(ChatColor.RED + "You have been kicked from \nHypixel SkyBlock!\nKicked by: " + ChatColor.GRAY + player.getName() + ChatColor.RED + "\nReason: " + ChatColor.GRAY + reason);
                } else {
                    player.sendMessage(MessageConfig.notOnline());
                    return true;
                }
            }
        } else {
            player.sendMessage(MessageConfig.noPermission());
            return true;
        }
        return false;
    }
}
