package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Events.SkyblockCollectionLevelUpEvent;
import com.itech4kids.skyblock.Util.Config;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.io.IOException;
import java.util.List;

public class CollectionsListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(e.getCurrentItem().equals(null)) { return; }
        if(e.getInventory().getName().equals("Collection")){
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Farming Collection")){
                player.performCommand("collections farming");
            }
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        if(e.getItem().equals(null)) { return; }
        collectionPickup(player, "farming", "wheat", 296, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        collectionPickup(player, "farming", "carrot", 391, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        collectionPickup(player, "farming", "potato", 392, e, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    // I = 100
    // II = 250
    // III = 750
    // IV = 1.5k
    // V = 3k
    // VI = 5k
    // VII = 10k
    // VIII = 25k
    // IX = 50k
    // X = 200k
    // XI = 400k
    // XII = 600k
    // XIII = 800k
    // XIV = 1M
    // XV = 1.2M
    // XVI = 1.4M

    public void collectionPickup(Player player, String collectionType, String collection, int typeID, PlayerPickupItemEvent e, String rewards, String rewards2, String rewards3, String rewards4, String rewards5, String rewards6, String rewards7, String rewards8, String rewards9, String rewards10, String rewards11, String rewards12, String rewards13, String rewards14, String rewards15, String rewards16){
        int collected = Config.getCollectionCollected(player, collectionType, collection);
        if(e.getItem().getItemStack().getTypeId() == typeID){
            try {
                Config.setCollectionCollected(player, collectionType, collection, Config.getCollectionCollected(player, collectionType, collection.toLowerCase()) + 1);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if(collected == 1) {
                try {
                    Config.setCollectionUnlocked(player, collectionType, collection, true);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    player.sendMessage("DEBUG");
                }
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "COLLECTION UNLOCKED " + ChatColor.YELLOW + " " + StringUtils.capitalize(collection));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 2);
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
