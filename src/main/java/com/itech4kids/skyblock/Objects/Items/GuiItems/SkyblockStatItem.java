package com.itech4kids.skyblock.Objects.Items.GuiItems;

import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkyblockStatItem extends ItemStack {

    public List<String> lore;
    public ItemMeta itemMeta;

    public SkyblockStatItem(SkyblockStats stat, SkyblockPlayer skyblockPlayer, String name, String description, String lore1, int type, int id){
        this.setTypeId(type);
        this.setDurability((short) id);
        this.setAmount(1);
        itemMeta = this.getItemMeta();
        lore = new ArrayList<>();

        itemMeta.setDisplayName(name + " " + ChatColor.WHITE + skyblockPlayer.getStat(stat));

        ItemUtil.addLoreMessage(description, this);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Base " + StringUtils.capitalize(stat.name().toLowerCase().replaceAll("_", " ")) + ": " + ChatColor.GREEN + skyblockPlayer.getStat(stat));
        ItemUtil.addItalicLore(lore1, this);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Bonus " + StringUtils.capitalize(stat.name().toLowerCase().replaceAll("_", " ")) + ": +" + ChatColor.YELLOW +  skyblockPlayer.getStat(stat));
        ItemUtil.addItalicLore("Increase your bonus " + stat.name().toLowerCase().replaceAll("_", " ") + "by equipping items and armor, and storing accessories in your inventory", this);

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);

        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
