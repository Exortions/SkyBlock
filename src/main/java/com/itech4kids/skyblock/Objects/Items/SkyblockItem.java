package com.itech4kids.skyblock.Objects.Items;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyblockItem implements Listener {

    private ItemStack bukkitItem;
    private HashMap<SkyblockStats, Integer> properties;
    private List<String> statsLore;
    private List<String> enchantsLore;
    private List<String> abilityLore;
    private List<String> rarityLore;
    private List<String> mainLore;
    private SkyblockItem sbItem;

    public SkyblockItem(ItemStack itemStack){
        this.bukkitItem = itemStack;
        sbItem = this;
        this.properties = new HashMap<SkyblockStats, Integer>();
        ItemMeta meta = bukkitItem.getItemMeta();
        statsLore = new ArrayList<String>();
        enchantsLore = new ArrayList<String>();
        abilityLore = new ArrayList<String>();
        rarityLore = new ArrayList<String>();
        mainLore = new ArrayList<String>();
        rewriteLore();
        bukkitItem.setItemMeta(meta);
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());

    }

    public ItemStack getBukkitItem(){
        return bukkitItem;
    }

    public void rewriteLore(){
        mainLore.clear();
        for (String string : statsLore){ mainLore.add(string); }
        for (String string : enchantsLore){ mainLore.add(string); }
        for (String string : abilityLore){ mainLore.add(string); }
        for (String string : rarityLore){ mainLore.add(string); }
    }

    public HashMap<SkyblockStats, Integer> getProperties(){
        return properties;
    }

    public SkyblockItem getSbItem(){return sbItem;}

    public void setProperty(SkyblockStats string, Integer i){
        properties.put(string, i);
        mainLore.clear();
        for (String s : statsLore){
            if (ChatColor.stripColor(s).equalsIgnoreCase(string.name().replace("_", " "))){
                //To-do
            }
        }
    }
}
