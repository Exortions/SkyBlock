package com.itech4kids.skyblock.Commands.Setup;

import com.itech4kids.skyblock.Util.LaunchPadConfig;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LaunchPadSetUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                return false;
            }else if (args.length == 1){
                return false;
            }else{
                if (args[0].equalsIgnoreCase("create")){
                    try {
                        LaunchPadConfig.addLaunchPad(args[1].toLowerCase(), player.getLocation(), new Location(player.getWorld(), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), Integer.parseInt(args[11]), Integer.parseInt(args[12])), new Location(player.getWorld(), Integer.parseInt(args[5]), Integer.parseInt(args[6]), Integer.parseInt(args[7])), new Location(player.getWorld(), Integer.parseInt(args[8]), Integer.parseInt(args[9]), Integer.parseInt(args[10]), Integer.parseInt(args[11]), Integer.parseInt(args[12])));
                        player.sendMessage(ChatColor.GREEN + args[1].toLowerCase() + " has been created!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
