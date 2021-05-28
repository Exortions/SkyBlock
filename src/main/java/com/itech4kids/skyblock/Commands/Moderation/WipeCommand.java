package com.itech4kids.skyblock.Commands.Moderation;

import com.itech4kids.skyblock.Objects.Island.IslandManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WipeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "Specify the player please!");
        }else{
            try {
                Player cheater = Bukkit.getPlayer(args[0]);
                cheater.sendMessage(ChatColor.RED + "Cheating has been detected on one or more of your Skyblock Profiles and your profiles have been wiped.");
                cheater.performCommand("warp hub");
                IslandManager.deleteWorld(cheater);
                if (sender instanceof Player){
                    ((Player) sender).performCommand("ban " + cheater.getName() + " Boosting detected on multiple Skyblock profiles");
                }else{
                    cheater.kickPlayer(ChatColor.RED + "Boosting detected on multiple Skyblock profiles");
                }
            }catch (Exception e){
                sender.sendMessage(ChatColor.RED + "This player is not onlinr!");
            }
        }

        return false;
    }
}
