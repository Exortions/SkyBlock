package com.itech4kids.skyblock.Commands.ItemBrowser.Boots;

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

public class BootsCategoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command! If you are the console, log into your account and run the command on Minecraft.");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Boots", Bukkit.createInventory(null, 54, "Boots"));
        Inventory menu = skyblockPlayer.getInventory("Boots");

        ItemStack emptySpace = ItemHandler.createEmptySpace();

        for(int index = 0; index < 54; index++){
            menu.setItem(menu.firstEmpty(), emptySpace);
        }

        // Navigation items
        ItemStack go_back = ItemHandler.createPageBackArrow("Item Browser");
        ItemStack close = ItemHandler.createExitBarrier();
        ItemStack next_page = ItemHandler.createNavigationArrow("next", 2);

        // Add items to menu
        menu.setItem(10, ItemHandler.superior_dragon_boots);
        menu.setItem(11, ItemHandler.farm_suit_boots);
        menu.setItem(11, ItemHandler.mushroom_boots);

        // Add navigation items to the menu
        menu.setItem(48, go_back);
        menu.setItem(49, close);
        menu.setItem(53, next_page);

        player.openInventory(skyblockPlayer.getInventory("Boots"));

        return false;
    }
}