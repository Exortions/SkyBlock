package com.itech4kids.skyblock.Commands.PlayerCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.TradeInventory;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please specify a player name!");
            }else{
                Player target = Bukkit.getPlayer(args[0]);
                SkyblockPlayer targetSkyblock = Main.getMain().getPlayer(target.getName());
                if (!skyblockPlayer.tradedPlayers.contains(target)) {
                    if (targetSkyblock.tradedPlayers.contains(player)){
                        targetSkyblock.tradedPlayers.remove(player);

                        TradeInventory tradeInventory = new TradeInventory(player, target);
                        TradeInventory tradeInventory2 = new TradeInventory(target, player);

                        player.openInventory(tradeInventory);
                        target.openInventory(tradeInventory2);
                    }else {
                        player.sendMessage(ChatColor.GREEN + "You have sent a trade request to " + ChatColor.YELLOW + target.getName());
                        target.sendMessage(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " has sent you a trade request. Click " + ChatColor.AQUA + "here " + ChatColor.GREEN + "to accept it");
                        player.playSound(player.getLocation(), Sound.VILLAGER_YES, 10, 1);
                        target.playSound(target.getLocation(), Sound.VILLAGER_YES, 10, 1);
                        skyblockPlayer.tradedPlayers.add(target);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                skyblockPlayer.tradedPlayers.remove(target);
                            }
                        }.runTaskLater(Main.getMain(), 20 * 60);
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "You have already sent a trade request to this person");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 0);
                }
            }
        }
        return false;
    }
}
