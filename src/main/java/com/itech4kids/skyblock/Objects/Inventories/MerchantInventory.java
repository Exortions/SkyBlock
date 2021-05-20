package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Objects.Items.Item;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantInventory extends CraftInventoryCustom {

    public MerchantInventory(String name, HashMap<ItemStack, Integer> shopItems) {
        super(null, 54, name);

        ItemStack sellItem = new ItemStack(Material.HOPPER);
        ItemMeta meta = sellItem.getItemMeta();
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Click items in your inventory to", ChatColor.GRAY + "sell them to this Shop!"));
        meta.setDisplayName(ChatColor.GREEN + "Sell Item");
        sellItem.setItemMeta(meta);

        for (int i = 0; i < 9; ++i){
            this.setItem(i, ItemHandler.createEmptySpace());
        }

        this.setItem(9, ItemHandler.createEmptySpace());
        this.setItem(17, ItemHandler.createEmptySpace());
        this.setItem(18, ItemHandler.createEmptySpace());
        this.setItem(26, ItemHandler.createEmptySpace());
        this.setItem(27, ItemHandler.createEmptySpace());
        this.setItem(35, ItemHandler.createEmptySpace());
        this.setItem(36, ItemHandler.createEmptySpace());
        this.setItem(44, ItemHandler.createEmptySpace());
        this.setItem(45, ItemHandler.createEmptySpace());

        for (int i = 45; i < 54; ++i){
            this.setItem(i, ItemHandler.createEmptySpace());
        }

        this.setItem(49, sellItem);

        for (Map.Entry<ItemStack, Integer> entry : shopItems.entrySet()){
            this.addItem(ItemHandler.createMerchantItem(entry.getKey(), entry.getValue()));
        }
    }
}
