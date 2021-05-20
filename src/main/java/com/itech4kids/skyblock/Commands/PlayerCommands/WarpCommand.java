package com.itech4kids.skyblock.Commands.PlayerCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            if (sender instanceof Player){
                Player player = (Player) sender;
                SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                skyblockPlayer.setInventory("Fast Travel", Bukkit.createInventory(null, 54, "Fast Travel"));
                Inventory menu = skyblockPlayer.getInventory("Fast Travel");
            }
        }else{
            if (sender instanceof Player){
                Player player = (Player) sender;
                if (args[0].equals("hub")){
                    player.teleport(new Location(Bukkit.getWorld("world"), -2.5, 70, -69.5, -180, 0));
                }else if (args[0].equals("home")){
                    player.teleport(new Location(IslandManager.getIsland(player), 0, 100, 0));
                }
            }
        }
        return false;
    }
}
