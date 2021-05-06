package com.itech4kids.skyblock.Objects.Items;

import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkyblockGuiItem extends ItemStack {

    public List<String> lore;
    public ItemMeta itemMeta;

    public SkyblockGuiItem(String name, String descLore, ArrayList<String> otherLore, Material material){
        lore = new ArrayList<>();
        itemMeta = new ItemStack(material).getItemMeta();
        this.setAmount(1);
        if (descLore != null) {
            //ItemUtil.addLoreMessage("h", this);
        }
        this.setType(material);
        itemMeta.setDisplayName(name);
        if (otherLore != null) {
            lore.add(" ");
            //for (String s : otherLore) { ItemUtil.addLoreMessage("Test", this); }
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view!");
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
