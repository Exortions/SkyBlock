package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DirectMessageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.isOp()){
            if(args.length >= 2){
                Player target = Bukkit.getPlayer(args[0]);
                String msg = ChatColor.translateAlternateColorCodes('&', String.join(" ", Arrays.asList(args).subList(1, args.length).toArray(new String[]{})));
                if(target.isOnline()){
                    target.sendMessage(msg);
                    target.playSound(target.getLocation(), Sound.NOTE_PLING, 100, 1);
                } else{
                    sender.sendMessage(MessageConfig.notOnline());
                }
            } else if(args.length == 1){
                sender.sendMessage(MessageConfig.directMessageCmdSpecifyMsg());
            } else {
                sender.sendMessage(MessageConfig.specifyPlayer());
            }
        } else {
            sender.sendMessage(MessageConfig.noPermission());
        }
        return false;
    }
}