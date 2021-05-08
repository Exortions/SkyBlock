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
        collectionPickup(player, "farming", "wheat", 296, e);
        collectionPickup(player, "farming", "carrot", 391, e);
        collectionPickup(player, "farming", "potato", 392, e);
    }

    public void collectionPickup(Player player, String collectionType, String collection, int typeID, PlayerPickupItemEvent e){
        int collected = Config.getCollectionCollected(player, collectionType, collection);
        if(e.getItem().getItemStack().getTypeId() == typeID){
            try {
                Config.setCollectionCollected(player, collectionType, collection, Config.getCollectionCollected(player, collectionType, collection.toLowerCase()) + 1);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            if(Config.getCollectionCollected(player, collectionType, collection) == 1){
                try{
                    Config.setCollectionUnlocked(player, collectionType, collection, true);
                } catch(IOException exception){
                    exception.printStackTrace();
                    player.sendMessage("DEBUG");
                }
                player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "COLLECTION UNLOCKED " + ChatColor.YELLOW + " " + StringUtils.capitalize(collection));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 1);
            } else if(collection.equalsIgnoreCase("wheat")){
                // Wheat collection code here
                if(collected == 50){
                    new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(0), "I", 1, ChatColor.BLUE + "Wheat Minion " + ChatColor.GRAY + "Recipes");
                }
            } else if(collection.equalsIgnoreCase("carrot")){
                // Carrot collection code here
                if(collected == 100){
                    new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(0), "I", 1, ChatColor.BLUE + "Carrot Minion " + ChatColor.GRAY + "Recipes");
                }
            } else if(collection.equalsIgnoreCase("potato")){
                // Potato collection code here
                if(collected == 100){
                    new SkyblockCollectionLevelUpEvent(player, collectionType, collection, Integer.toString(0), "I", 1, ChatColor.BLUE + "Potato Minion " + ChatColor.GRAY + "Recipes");
                }
            }
        }
    }
}
