package com.itech4kids.skyblock.Mechanics.Anvil;

import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilActions {

    public void combineItems(Inventory inv, Player player){
        ItemStack air = ItemHandler.createAir();
        inv.setItem(29, air);
        inv.setItem(33, air);
        player.playSound(player.getLocation(), Sound.ANVIL_USE, 100, 1);
    }

}
