package com.itech4kids.skyblock.Objects.Items.GuiItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ClickGuiItem extends ItemStack {

    public List<String> lore;
    public ItemMeta itemMeta;

    public ClickGuiItem(String name, String click, int type, int id){
        lore = new ArrayList<>();
        itemMeta = new ItemStack(Material.DIAMOND_SWORD).getItemMeta();
        this.setTypeId(type);
        this.setDurability((short) id);
        this.setAmount(1);
        itemMeta.setDisplayName(ChatColor.GREEN + name);
        lore.add(ChatColor.YELLOW + click);

        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
