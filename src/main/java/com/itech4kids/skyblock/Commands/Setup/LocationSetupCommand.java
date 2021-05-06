package com.itech4kids.skyblock.Commands.Setup;

import com.itech4kids.skyblock.Util.LocationsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class LocationSetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                LocationsManager.createNewLocation(player.getLocation(),
                        new Location(player.getWorld(), Integer.parseInt(args[0]),
                                Integer.parseInt(args[1]), Integer.parseInt(args[2])), args[3],
                        ChatColor.valueOf(args[4]), Integer.parseInt(args[5]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
