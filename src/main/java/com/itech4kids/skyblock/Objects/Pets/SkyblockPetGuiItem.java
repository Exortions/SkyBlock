package com.itech4kids.skyblock.Objects.Pets;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SkyblockPetGuiItem extends ItemStack {

    public SkyblockPetGuiItem(ItemStack itemStack){
        this.setTypeId(itemStack.getTypeId());
        this.setDurability(itemStack.getDurability());
        this.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for (int i = 0; i < lore.size(); ++i){
            if (lore.get(i).startsWith(ChatColor.YELLOW + "Right-click to add")){
                lore.set(i, ChatColor.GRAY + "Progress to Level " + (Integer.parseInt(itemMeta.getDisplayName().split("Lvl ")[1].split("]")[0]) + 1) + ": " + ChatColor.YELLOW + "0%");
            }else if ((lore.get(i).startsWith(ChatColor.YELLOW + "your pet menu!"))){
                lore.set(i, ChatColor.GRAY + "--------------------");
            }
        }
        itemMeta.setLore(lore);
        this.setItemMeta(itemMeta);
    }

}
