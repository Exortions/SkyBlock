package com.itech4kids.skyblock.Mechanics.Anvil;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class AnvilListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(e.getAction() == null) { return; }
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock() == null) { return; }
            Material clickedBlock = e.getClickedBlock().getType();
            if(clickedBlock.equals(Material.ANVIL)){
                e.setCancelled(true);
                e.getPlayer().performCommand("anvil");
            }
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        if(e.getClickedInventory() == null) { return; }
        if(e.getClickedInventory().getName().equals("Anvil")){
            if(e.getSlot() == 29 || e.getSlot() == 33){
                e.setCancelled(false);
            } else{
                e.setCancelled(true);
            }
        }
    }

}
