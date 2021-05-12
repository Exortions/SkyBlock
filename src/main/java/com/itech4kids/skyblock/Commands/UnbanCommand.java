package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        if(Config.getBanned(target) == true){
            try {
                Config.setBanned(target, false);
                Config.setBanReason(target, "None");
                sender.sendMessage(ChatColor.GREEN + "Succesfully unbanned player " + target.getName() + "!");
            }catch(IOException e){
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED + "An error occured while trying to unban player " + target.getName());
            }
        } else{
            sender.sendMessage(ChatColor.RED + "That player isn't banned!");
        }
        return false;
    }
}