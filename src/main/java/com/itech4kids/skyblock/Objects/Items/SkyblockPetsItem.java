package com.itech4kids.skyblock.Objects.Items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyblockPetsItem extends SkyblockItem {

    private List<String> statsLore;
    private List<String> abilitiesLore;
    private List<String> rarityLore;
    private List<String> mainLore;
    private String name;
    private HashMap<String, Integer> stats;
    private SkyblockItem sbItem;
    private int level;
    private int exp;

    public SkyblockPetsItem(String skullOwner, String petName) {
        super(new ItemStack(Material.SKULL_ITEM));
        name = petName;
        SkullMeta meta = (SkullMeta) super.getBukkitItem().getItemMeta();
        meta.setOwner(skullOwner);
        meta.setDisplayName(name);
        super.getBukkitItem().setItemMeta(meta);
        level = 1;
        exp = 0;
        sbItem = super.getSbItem();
        stats = new HashMap<String, Integer>();
        statsLore = new ArrayList<String>();
        rarityLore = new ArrayList<String>();
        abilitiesLore = new ArrayList<String>();
        mainLore = new ArrayList<String>();
        for (String s : statsLore){ mainLore.add(s);}
        for (String s : rarityLore){ mainLore.add(s);}
        for (String s : abilitiesLore){ mainLore.add(s);}
    }

    public String getName(){return name;}
    public HashMap<String, Integer> getStats(){return stats;}
    public SkyblockItem getSkyblockItem(){return sbItem;}

}
