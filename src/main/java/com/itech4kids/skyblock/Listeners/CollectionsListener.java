package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Util.Config;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.util.StringUtil;

import java.io.IOException;
import java.util.Locale;

public class CollectionsListener implements Listener {

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        if(e.getItem().equals(null)) { return; }
        collectionPickup(player, "farming", "wheat", 296, e);
        collectionPickup(player, "farming", "carrot", 391, e);
        collectionPickup(player, "farming", "potato", 392, e);
    }

    public void collectionPickup(Player player, String collectionType, String collection, int typeID, PlayerPickupItemEvent e){
        if(e.getItem().getItemStack().getTypeId() == typeID){
            try {
                Config.setCollectionCollected(player, collectionType, collection, Config.getCollectionCollected(player, collectionType, collection.toLowerCase()) + 1);
                Config.setCollectionLevel(player, collectionType, collection, Config.getCollectionLevel(player, collectionType, collection.toLowerCase()) + 1);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if(Config.getCollectionCollected(player, collectionType, collection) == 1){
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "COLLECTION UNLOCKED " + ChatColor.YELLOW + " " + StringUtils.capitalize(collection));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 1);
            }
        }
    }
}
