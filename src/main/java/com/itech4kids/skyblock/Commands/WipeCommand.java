package com.itech4kids.skyblock.Commands;

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
            Player cheater = Bukkit.getPlayer(args[0]);
            Player player = (Player) sender;
            if(player.getServer().getOnlinePlayers().contains(cheater)){
                cheater.sendMessage(ChatColor.RED + "Cheating has been detected on one or more of your Skyblock Profiles and your profiles have been wiped.");
                cheater.performCommand("warp hub");
                IslandManager.deleteWorld(cheater);
                if (sender instanceof Player){
                    player.performCommand("ban " + cheater.getName() + " Cheating_has_been_detected_on_one_or_more_of_your_Skyblock_Profiles_and_your_profiles_have_been_wiped.");
                }
            } else{
                player.sendMessage(ChatColor.RED + "That player isn't online! Try again when they're online.");
            }
        }

        return false;
    }
}
