package com.itech4kids.skyblock.Commands.Setup;

import com.itech4kids.skyblock.Util.CustomMobSpawning;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MobSpawnSetUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length != 0) {
                try {
                    CustomMobSpawning.addMobSpawnLocation(player.getLocation(), args[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
