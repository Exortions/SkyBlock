package com.itech4kids.skyblock.Objects.Items.GuiItems;

import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkyblockSkillGuiItem extends ItemStack {

    public List<String> lore;
    public ItemMeta itemMeta;
    public int i;

    public SkyblockSkillGuiItem(SkillType skillType, SkyblockPlayer skyblockPlayer, String description, String otherName, List<String> rewardsLore, int typeId, int damageValue){
        this.lore = new ArrayList<>();
        this.setAmount(1);
        this.setTypeId(typeId);
        this.setDurability((short) damageValue);
        this.itemMeta = this.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase().replaceAll(" ", " ")));

        ItemUtil.addLoreMessage(description, this);

        i = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + 1;

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Progress to Level " + i + ": " + ChatColor.RED + "N/A");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Level " + i + " rewards:");
        lore.add(ChatColor.YELLOW + " " + otherName + " " + i);
        if (rewardsLore != null) {
            for (String s : rewardsLore) {
                lore.add(s);
            }
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view!");

        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }
}
