package com.itech4kids.skyblock.Mechanics.Anvil;

import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AnvilInventory {

    public static ItemStack createAnvilItems(int i){
        if(i == 0){
            // Gray pane
            return ItemHandler.createEmptySpace();
        } else if(i == 1){
            // Red pane
            return ItemHandler.createRedEmptySpace();
        } else if(i == 2) {
            // Green pane
            return ItemHandler.createGreenEmptySpace();
        } else if(i == 3){
            // Item to upgrade
            ItemStack tmp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "The item you want to");
            l.add(ChatColor.GRAY + "upgrade should be placed in");
            l.add(ChatColor.GRAY + "the slot on this side.");
            m.setDisplayName(ChatColor.GOLD + "Item to Upgrade");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 4){
            // Item to sacrifice
            ItemStack tmp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "The item you are");
            l.add(ChatColor.GRAY + "sacrificing in order to");
            l.add(ChatColor.GRAY + "upgrade the item on the");
            l.add(ChatColor.GRAY + "left should be placed in");
            l.add(ChatColor.GRAY + "the slot on this side.");
            m.setDisplayName(ChatColor.GOLD + "Item to Sacrifice");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 5){
            // Anvil barrier
            ItemStack tmp = new ItemStack(Material.BARRIER);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "Place a target item in the left");
            l.add(ChatColor.GRAY + "slot and a sacrifice item in the");
            l.add(ChatColor.GRAY + "right slot to combine");
            l.add(ChatColor.GRAY + "Enchantments!");
            m.setDisplayName(ChatColor.RED + "Anvil");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 6){
            // Anvil item
            ItemStack tmp = new ItemStack(Material.ANVIL);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "Combine the items in the");
            l.add(ChatColor.GRAY + "slots to the left and right");
            l.add(ChatColor.GRAY + "below.");
            m.setDisplayName(ChatColor.GREEN + "Combine Items");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 7){
            // Error
            ItemStack tmp = new ItemStack(Material.BARRIER);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "You cannot combine those items!");
            m.setDisplayName(ChatColor.RED + "Error!");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 8){
            // Green item to upgrade
            ItemStack tmp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "The item you want to");
            l.add(ChatColor.GRAY + "upgrade should be placed in");
            l.add(ChatColor.GRAY + "the slot on this side.");
            m.setDisplayName(ChatColor.GOLD + "Item to Upgrade");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 9){
            // Green item to sacrifice
            ItemStack tmp = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
            ItemMeta m = tmp.getItemMeta();
            List<String> l = new ArrayList<>();
            l.add(ChatColor.GRAY + "The item you are");
            l.add(ChatColor.GRAY + "sacrificing in order to");
            l.add(ChatColor.GRAY + "upgrade the item on the");
            l.add(ChatColor.GRAY + "left should be placed in");
            l.add(ChatColor.GRAY + "the slot on this side.");
            m.setDisplayName(ChatColor.GOLD + "Item to Sacrifice");
            m.setLore(l);
            tmp.setItemMeta(m);
            return tmp;
        } else if(i == 10){
            return ItemHandler.createExitBarrier();
        }
        return new ItemStack(Material.AIR);
    }

}