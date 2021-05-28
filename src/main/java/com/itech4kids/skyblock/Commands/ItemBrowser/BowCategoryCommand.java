package com.itech4kids.skyblock.Commands.ItemBrowser;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BowCategoryCommand implements CommandExecutor {

    private ItemHandler itemHandler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        this.itemHandler = itemHandler;
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "[Skyblock] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Bows", Bukkit.createInventory(null, 54, "Bows"));
        Inventory menu = skyblockPlayer.getInventory("Bows");

        ItemStack emptySpace = itemHandler.createEmptySpace();

        for(int index = 0; index < 9; index++){
            menu.setItem(index, emptySpace);
        }

        menu.setItem(9, emptySpace);
        menu.setItem(17, emptySpace);
        menu.setItem(18, emptySpace);
        menu.setItem(26, emptySpace);
        menu.setItem(27, emptySpace);
        menu.setItem(36, emptySpace);
        menu.setItem(35, emptySpace);

        for (int i = 44; i < 54; ++i){
            menu.setItem(i, emptySpace);
        }

        // Navigation items
        ItemStack go_back = itemHandler.createPageBackArrow("Item Browser");
        ItemStack close = itemHandler.createExitBarrier();
        ItemStack next_page = itemHandler.createNavigationArrow("next", 2);

        // Add items to menu
        int i = 1;

        for (Map.Entry<String, ItemStack> entry : ItemHandler.bowMap.entrySet()){
            menu.addItem(entry.getValue());
        }

        // Add navigation items to the menu
        menu.setItem(48, go_back);
        menu.setItem(49, close);
        menu.setItem(53, next_page);

        player.openInventory(skyblockPlayer.getInventory("Bows"));
        return false;
    }
}