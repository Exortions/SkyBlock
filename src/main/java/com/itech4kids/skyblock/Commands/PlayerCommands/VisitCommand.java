package com.itech4kids.skyblock.Commands.PlayerCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class VisitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please input a name!");
            }else{
                OfflinePlayer target = Bukkit.getPlayer(args[0]);
                if (target.hasPlayedBefore()){
                    Player player = (Player) sender;
                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                    skyblockPlayer.setInventory("Visit " + target.getName(),
                            Bukkit.createInventory(null, 36, "Visit " + target.getName()));
                    Inventory menu = skyblockPlayer.getInventory("Visit " + target.getName());

                    for (int i = 0; i < menu.getSize(); ++i){
                        menu.setItem(i, ItemHandler.createEmptySpace());
                    }

                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
                    SkullMeta itemMeta = (SkullMeta) skull.getItemMeta();
                    itemMeta.setOwner(target.getName());
                    List<String> lore = new ArrayList<>();
                    itemMeta.setDisplayName(ChatColor.GREEN + "Visit player island");
                    lore.add(ChatColor.GRAY + "Players:");
                    lore.add(ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + target.getName());
                    lore.add(" ");
                    lore.add(ChatColor.GRAY + "Profile: " + ChatColor.YELLOW + "Strawberry");
                    lore.add(" ");
                    lore.add(ChatColor.GRAY + "Players: " + ChatColor.GREEN + IslandManager.getIsland(target.getPlayer()).getPlayers().size() + "/5");
                    lore.add(ChatColor.GRAY + "Server: " + ChatColor.DARK_GRAY + "Island_" + target.getName());
                    lore.add(" ");
                    lore.add(ChatColor.YELLOW + "Click to visit!");
                    itemMeta.setLore(lore);
                    skull.setItemMeta(itemMeta);

                    menu.setItem(31, ItemHandler.createExitBarrier());
                    menu.setItem(13, skull);

                    player.openInventory(menu);
                }
            }
        }

        return false;
    }
}
