package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemCommand implements CommandExecutor {

    List<ItemStack> items = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String s, java.lang.String[] args) {
        items.add(ItemHandler.aspect_of_the_jerry);
        items.add(ItemHandler.aspect_of_the_jerry);
        items.add(ItemHandler.fancy_sword);
        items.add(ItemHandler.rogue_sword);
        items.add(ItemHandler.spider_sword);
        items.add(ItemHandler.undead_sword);
        items.add(ItemHandler.end_sword);
        items.add(ItemHandler.cleaver);
        items.add(ItemHandler.flaming_sword);
        items.add(ItemHandler.prismarine_blade);
        items.add(ItemHandler.hunter_knife);
        items.add(ItemHandler.tatician_sword);
        items.add(ItemHandler.thick_tatician_sword);
        items.add(ItemHandler.ember_rod);
        items.add(ItemHandler.frozen_scythe);
        items.add(ItemHandler.golem_sword);
        items.add(ItemHandler.raider_axe);
        items.add(ItemHandler.revenant_falchion);
        items.add(ItemHandler.silver_fang);
        items.add(ItemHandler.shaman_sword);
        items.add(ItemHandler.aspect_of_the_end);
        items.add(ItemHandler.scorpion_foil);
        items.add(ItemHandler.thick_scorpion_foil);
        items.add(ItemHandler.zombie_sword);
        items.add(ItemHandler.grappling_hook);
        items.add(ItemHandler.ornate_zombie_sword);
        items.add(ItemHandler.recluse_fang);
        items.add(ItemHandler.end_stone_sword);
        items.add(ItemHandler.reaper_falchion);
        items.add(ItemHandler.pooch_sword);
        items.add(ItemHandler.edibleMace);

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length > 1) {
            player.sendMessage(ChatColor.RED + "Incorrect usage!\n /item <item>");
            return true;
        } else if(args.length == 0){
            player.sendMessage(ChatColor.RED + "Incorrect usage!\n /item <item>");
            return true;
        } else if(args.length == 1){
            player.sendMessage(ChatColor.GREEN + args[0].toUpperCase());
            for(int i = 0; i < items.size(); i++){
                String old_item_name = ChatColor.stripColor(items.get(i).getItemMeta().getDisplayName().toUpperCase());
                String new_item_name = ChatColor.stripColor(old_item_name.replace(' ', '_'));
                if(args[0].toUpperCase().equals(new_item_name)){
                    player.getInventory().addItem(items.get(i));
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}