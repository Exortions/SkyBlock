package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Util.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemFlag;

public class CollectionsListener implements Listener {

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e){
        Player player = e.getPlayer();
        if(e.getItem().equals(null)) { return; }
            if(e.getItem().getItemStack().getTypeId() == 296){
                e.getItem().getItemStack().getItemMeta().addItemFlags(ItemFlag.HIDE_PLACED_ON);
                Config.getCollectionCollected(player, "farming", "wheat");
            }
        }
}