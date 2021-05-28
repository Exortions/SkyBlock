package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Events.SkyblockCollectionLevelUpEvent;
import com.itech4kids.skyblock.Util.Config;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.io.IOException;

public class CollectionsListener implements Listener {

    @EventHandler
    public void onCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Farming Collection")){
                player.performCommand("collections farming");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Mining Collection")){
                player.performCommand("collections mining");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Combat Collection")){
                player.performCommand("collections combat");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Foraging Collection")){
                player.performCommand("collections foraging");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Fishing Collection")){
                player.performCommand("collections fishing");
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "Boss Collection")){
                player.performCommand("collections boss");
            }
        }
    }

    @EventHandler
    public void onCollectionShiftClick(InventoryClickEvent e){
        if(e.getInventory().getName().contains("Collection")){
            if(e.getClick().isShiftClick()){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFarmingCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Farming Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onMiningCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Mining Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onCombatCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Combat Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onForagingCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Foraging Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onFishingCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Fishing Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onBossCollectionInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) { return; }
        if(e.getInventory().getName().equals("Boss Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("collections main");
            }
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        if(e.getItem() == null) { return; }
        farmingCollectionPickup(player, e);
        miningCollectionPickup(player, e);
    }

    public void farmingCollectionPickup(Player player, PlayerPickupItemEvent e){
        collectionPickup(player, "farming", "wheat", 296, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "carrot", 391, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "potato", 392, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "pumpkin", 86, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "melon", 360, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "seeds", 295, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "mushroom", 40, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "cocoa_beans", 351, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 3);
        collectionPickup(player, "farming", "cactus", 81, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "sugarcane", 338, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "feather", 288, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "leather", 334, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "raw_porkchop", 319, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "raw_chicken", 365, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "mutton", 423, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "raw_rabbit", 411, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
        collectionPickup(player, "farming", "nether_wart", 372, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
    }

    public void miningCollectionPickup(Player player, PlayerPickupItemEvent e){
        collectionPickup(player, "mining", "cobblestone", 4, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","", (short) 0);
        collectionPickup(player, "mining", "coal", 263, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","", (short) 0);
        collectionPickup(player, "mining", "iron", 265, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","", (short) 0);
    }

    public void collectionPickup(Player player, String collectionType, String collection, int typeID, PlayerPickupItemEvent e, String rewards, String rewards2, String rewards3, String rewards4, String rewards5, String rewards6, String rewards7, String rewards8, String rewards9, String rewards10, String rewards11, String rewards12, String rewards13, String rewards14, String rewards15, String rewards16, Short data){
        int collected = Config.getCollectionCollected(player, collectionType, collection);
        if(e.getItem().getItemStack().getTypeId() == typeID && e.getItem().getItemStack().getDurability() == data){
            int amount = e.getItem().getItemStack().getAmount();
            //int amount = 1;
            try {
                Config.setCollectionCollected(player, collectionType, collection, Config.getCollectionCollected(player, collectionType, collection.toLowerCase()) + amount);
                player.sendMessage(Integer.toString(Config.getCollectionCollected(player, collectionType, collection)));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if(!Config.getCollectionUnlocked(player, collectionType, collection)) {
                try {
                    Config.setCollectionUnlocked(player, collectionType, collection, true);
                    Config.setCollectionLevel(player, collectionType, collection, 1);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "YIKES!" + ChatColor.RESET + ChatColor.GRAY + "An error occured with the collections system! Oh no!");
                }
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "COLLECTION UNLOCKED " + ChatColor.YELLOW + " " + WordUtils.capitalize(collection.replace('_', ' ')));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 1);
            } else if (collected == 100) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(0), "I", 1, rewards);
            } else if (collected == 250) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(1), "II", 2, rewards2);
            } else if (collected == 750) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(2), "III", 3, rewards3);
            } else if (collected == 1500) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(3), "IV", 4, rewards4);
            } else if (collected == 3000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(4), "V", 5, rewards5);
            } else if (collected == 5000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(5), "VI", 6, rewards6);
            } else if (collected == 10000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(6), "VII", 7, rewards7);
            } else if (collected == 25000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(7), "VIII", 8, rewards8);
            } else if (collected == 50000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(8), "IX", 9, rewards9);
            } else if (collected == 200000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(9), "X", 10, rewards10);
            } else if (collected == 400000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(10), "XI", 11, rewards11);
            } else if (collected == 600000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(11), "XII", 12, rewards12);
            } else if (collected == 800000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(12), "XIII", 13, rewards13);
            } else if (collected == 1000000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(13), "XIV", 14, rewards14);
            } else if (collected == 1200000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(14), "XV", 15, rewards15);
            } else if (collected == 1400000) {
                new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(15), "XVI", 16, rewards16);
            }
        }
    }
}
