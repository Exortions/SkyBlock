package com.itech4kids.skyblock.Objects.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Item {

    public static ItemStack createBasicItem(Material mat, String name, List<String> lore, short data, boolean enchantmentGlint){
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setDurability(data);
        if(enchantmentGlint == true){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createInGameItem(Material mat, String name, String reforge,int amount, List<String> description, boolean enchantGlint, boolean hasAbility, String abilityName, List<String> abilityDesc, String abilityType, int abilityCost, String abilityCD, String rarity, int damage, int strength, int critChance, int critDMG, int atkSpeed, int intelligence, int speed, int defense, boolean reforgeAble){
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(damage != 0){
            lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if(strength != 0){
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + strength);
        }
        if(critChance != 0){
            lore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if(critDMG != 0){
            lore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + critDMG + "%");
        }
        if(atkSpeed != 0){
            lore.add(ChatColor.GRAY + "Attack Speed: " + ChatColor.RED + "+" + atkSpeed + "%");
        }
        if(speed != 0 && intelligence != 0 && defense != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + intelligence);
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        } else if(intelligence != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + intelligence);
        } else if(speed != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
        } else if(defense != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        }
        lore.add("");

        if (description != null){
            for (String s : description){
                lore.add(s);
            }
        }
        if(hasAbility){
            //lore.add(" ");
            lore.add(ChatColor.GOLD + "Item Ability: " + abilityName + " " + ChatColor.YELLOW + ChatColor.BOLD  + abilityType);
            for(String temp : abilityDesc){
                lore.add(temp);
            }
            if(abilityCost != 0){
                lore.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.AQUA + abilityCost);
            }
            if(abilityCD != ""){
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + abilityCD);
            }
            lore.add("");
        }
        if(reforgeAble == true){
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        }
        if(rarity.contains("LEGENDARY") || rarity.contains("legendary")){
            lore.add("" + ChatColor.GOLD + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("EPIC") || rarity.contains("epic")){
            lore.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("RARE") || rarity.contains("rare")){
            lore.add("" + ChatColor.BLUE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("UNCOMMON") || rarity.contains("uncommon")) {
            lore.add("" + ChatColor.GREEN + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("COMMON") || rarity.contains("common")) {
            lore.add("" + ChatColor.WHITE + ChatColor.BOLD + rarity.toUpperCase());
        }

        if(!(reforge == "")){
            meta.setDisplayName(reforge + " " +name);
        } else{
            meta.setDisplayName(name);
        }
        meta.setLore(lore);
        if(enchantGlint){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createNavigationArrow(String direction, int newPageNumber){
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Page " + newPageNumber);
        if(direction == "next"){
            meta.setDisplayName(ChatColor.GREEN + "Next Page");
        } else if(direction == "previous"){
            meta.setDisplayName(ChatColor.GREEN + "Previous Page");
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPageBackArrow(String newPage){
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "To " + newPage);
        meta.setDisplayName(ChatColor.GREEN + "Go Back");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createExitBarrier(){
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Close");
        item.setItemMeta(meta);
        return item;
    }

}