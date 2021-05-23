package com.itech4kids.skyblock.Mechanics.Anvil;

import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilListener implements Listener {

    private AnvilActions anvilActions;

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
            Inventory inv = e.getClickedInventory();
            Player player = (Player) e.getWhoClicked();
            ItemStack air = ItemHandler.createAir();
            if(e.getCurrentItem().getType().equals(Material.ANVIL)) {
                player.sendMessage(ChatColor.RED + "LOL");
                if (inv.getItem(29).equals(air) && inv.getItem(33) == air) {
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 100, 1);
                }
            }
            e.setCancelled(e.getSlot() != 29 && e.getSlot() != 33);
        }
    }

}
