package com.itech4kids.skyblock.Commands.ItemBrowser.Material;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class MaterialCategoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command! If you are the console, log into your account and run the command on Minecraft.");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Materials", Bukkit.createInventory(null, 54, "Materials"));
        Inventory menu = skyblockPlayer.getInventory("Materials");

        ItemStack emptySpace = ItemHandler.createEmptySpace();

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
        ItemStack go_back = ItemHandler.createPageBackArrow("Item Browser");
        ItemStack close = ItemHandler.createExitBarrier();
        ItemStack next_page = ItemHandler.createNavigationArrow("next", 2);

        // Add items to menu
        for (Map.Entry<String, ItemStack> entry : ItemHandler.materialMap.entrySet()){
            menu.addItem(entry.getValue());
        }

        // Add navigation items to the menu
        menu.setItem(48, go_back);
        menu.setItem(49, close);
        menu.setItem(53, next_page);

        player.openInventory(skyblockPlayer.getInventory("Materials"));

        return false;
    }
}
