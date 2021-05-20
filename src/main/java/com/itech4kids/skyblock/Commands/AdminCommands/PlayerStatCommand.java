package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;

public class PlayerStatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "Please enter a player name!");
            sender.sendMessage(ChatColor.RED + "/setPlayerStat <player name> <stat name> <amount>");
        }else if (args.length == 1){
            sender.sendMessage(ChatColor.RED + "Please enter a stat name!");
            sender.sendMessage(ChatColor.RED + "/setPlayerStat <player name> <stat name> <amount>");
        }else if (args.length == 2){
            sender.sendMessage(ChatColor.RED + "Please enter the amount you want to set it to!");
            sender.sendMessage(ChatColor.RED + "/setPlayerStat <player name> <stat name> <amount>");
        }else{
            Player target = Bukkit.getPlayer(args[0]);
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(target.getName());
            skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.NOTE_PLING, 10, 1);
            skyblockPlayer.getBukkitPlayer().sendMessage(ChatColor.GREEN + "Success!");

            Player player = skyblockPlayer.getBukkitPlayer();

            if (args[1].equalsIgnoreCase("coins")){
                try {
                    Config.setPurseCoins(target, Integer.parseInt(args[2]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (args[1].equalsIgnoreCase("bits")){
                try {
                    Config.setBits(target, Integer.parseInt(args[2]));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                for (Map.Entry<SkyblockStats, Integer> entry : skyblockPlayer.getStats().entrySet()) {
                    if (entry.getKey().name().equalsIgnoreCase(args[1])) {
                        entry.setValue(Integer.parseInt(args[2]));
                        if (entry.getKey().equals(SkyblockStats.HEALTH)){
                            skyblockPlayer.setStat(SkyblockStats.MAX_HEALTH, Integer.parseInt(args[2]));
                            skyblockPlayer.setStat(SkyblockStats.HEALTH, 1);
                            skyblockPlayer.getBukkitPlayer().setHealth(skyblockPlayer.getBukkitPlayer().getMaxHealth());
                        }else if (entry.getKey().equals(SkyblockStats.MANA)){
                            skyblockPlayer.setStat(SkyblockStats.MAX_MANA, Integer.parseInt(args[2]));
                        }
                    }
                }
            }
        }
        return false;
    }
}
