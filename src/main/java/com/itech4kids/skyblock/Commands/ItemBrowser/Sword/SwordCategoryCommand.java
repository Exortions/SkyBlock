package com.itech4kids.skyblock.Commands.ItemBrowser.Sword;

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

public class SwordCategoryCommand implements CommandExecutor {

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
        skyblockPlayer.setInventory("Swords", Bukkit.createInventory(null, 54, "Swords"));
        Inventory menu = skyblockPlayer.getInventory("Swords");

        ItemStack emptySpace = itemHandler.createEmptySpace();

        for (int index = 0; index < 54; index++) {
            menu.setItem(index, emptySpace);
        }
        // Navigation items
        ItemStack go_back = itemHandler.createPageBackArrow("Item Browser");
        ItemStack close = itemHandler.createExitBarrier();
        ItemStack next_page = itemHandler.createNavigationArrow("next", 2);

        // Add items to menu
        menu.setItem(10, ItemHandler.aspect_of_the_jerry);
        menu.setItem(11, ItemHandler.fancy_sword);
        menu.setItem(12, ItemHandler.rogue_sword);
        menu.setItem(13, ItemHandler.spider_sword);
        menu.setItem(14, ItemHandler.undead_sword);
        menu.setItem(15, ItemHandler.end_sword);
        menu.setItem(16, ItemHandler.cleaver);
        menu.setItem(19, ItemHandler.flaming_sword);
        menu.setItem(20, ItemHandler.prismarine_blade);
        menu.setItem(21, ItemHandler.hunter_knife);
        menu.setItem(22, ItemHandler.tatician_sword);
        menu.setItem(23, ItemHandler.thick_tatician_sword);
        menu.setItem(24, ItemHandler.ember_rod);
        menu.setItem(25, ItemHandler.frozen_scythe);
        menu.setItem(28, ItemHandler.golem_sword);
        menu.setItem(29, ItemHandler.raider_axe);
        menu.setItem(30, ItemHandler.revenant_falchion);
        menu.setItem(31, ItemHandler.silver_fang);
        menu.setItem(32, ItemHandler.shaman_sword);
        menu.setItem(33, ItemHandler.aspect_of_the_end);
        menu.setItem(34, ItemHandler.scorpion_foil);
        menu.setItem(37, ItemHandler.thick_scorpion_foil);
        menu.setItem(38, ItemHandler.zombie_sword);
        menu.setItem(39, ItemHandler.grappling_hook);

        // Add navigation items to the menu
        menu.setItem(48, go_back);
        menu.setItem(49, close);
        menu.setItem(53, next_page);

        player.openInventory(skyblockPlayer.getInventory("Swords"));
        return false;
    }
}