package com.itech4kids.skyblock.Objects.Pets;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.Items.SkyblockItem;
import com.itech4kids.skyblock.Util.ItemUtil;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyblockPetsItem extends ItemStack {

    public String name;
    public int level;
    public int exp;
    public List<String> lore;
    public ItemStack itemStack;
    public SkullMeta meta;

    public String skullID;

    public Integer strength;
    public Integer critChance;
    public Integer critDMG;
    public Integer atkSpeed;
    public Integer intelligence;
    public Integer speed;
    public Integer defense;
    public Integer ferocity;

    public String abilityName1;
    public String ability1Desc;
    public String abilityName2;
    public String ability2Desc;
    public String abilityName3;
    public String ability3Desc;

    public ChatColor rarityColor;
    public String rarity;

    public SkyblockPetsItem(String skullID, int lvl, String petName, ChatColor rarityColor, String rarity, String abilityName1, String ability1Desc, String abilityName2, String ability2Desc, String abilityName3, String ability3Desc, Integer strength, Integer critChance, Integer critDMG, Integer atkSpeed, Integer intelligence, Integer speed, Integer defense, Integer ferocity, Double strengthPerLvl, Double critChancePerLvl, Double critDMGPerLvl, Double atkSpeedPerLvl, Double intelligencePerLvl, Double speedPerLvl, Double defensePerLvl, Double ferocityPerLvl) {
        this.setTypeId(397);
        this.setDurability((short) 3);
        this.setAmount(1);

        this.skullID = skullID;
        this.rarity = rarity;
        this.strength = strength;
        this.critChance = critChance;
        this.critDMG = critDMG;
        this.atkSpeed = atkSpeed;
        this.intelligence = intelligence;
        this.speed = speed;
        this.defense = defense;
        this.ferocity = ferocity;

        this.ability1Desc = ability1Desc;
        this.ability2Desc = ability2Desc;
        this.ability3Desc = ability3Desc;

        this.abilityName1 = abilityName1;
        this.abilityName2 = abilityName2;
        this.abilityName3 = abilityName3;

        this.rarityColor = rarityColor;

        name = petName;
        level = lvl;

        itemStack = Main.getMain().IDtoSkull(this, skullID);

        this.meta = (SkullMeta) itemStack.getItemMeta();

        level = lvl;
        exp = 0;
        lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.GRAY + "[Lvl " + level + "] " + this.rarityColor + name);

        if(this.ferocity != null){
            lore.add(ChatColor.GRAY + "Ferocity: " + ChatColor.RED + "+" + (this.ferocity + Math.round(((level - 1) * ferocityPerLvl))));
        }
        if(this.strength != null){
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + (this.strength + Math.round(((level - 1) * strengthPerLvl))));
        }
        if(this.critChance != null){
            lore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + (this.critChance + Math.round(((level - 1) * critChancePerLvl)) + "%"));
        }
        if(this.critDMG != null){
            lore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + (this.critDMG + Math.round(((level - 1) * critDMGPerLvl)) + "%"));
        }
        if(this.atkSpeed != null){
            lore.add(ChatColor.GRAY + "Attack Speed: " + ChatColor.RED + "+" + (this.atkSpeed + Math.round(((level - 1) * atkSpeedPerLvl)) + "%"));
        }
        if(this.speed != null && this.intelligence != null && this.defense != null){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + this.intelligence + Math.round(((level - 1) * intelligencePerLvl)));
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + this.speed + Math.round(((level - 1) * speedPerLvl)));
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + this.defense + Math.round(((level - 1) * defensePerLvl)));
        } else if(this.intelligence != null){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + this.intelligence + Math.round(((level - 1) * intelligencePerLvl)));
        } else if(this.speed != null){
            lore.add("");
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + this.speed + Math.round(((level - 1) * speedPerLvl)));
        } else if(this.defense != null){
            lore.add("");
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + this.defense + Math.round(((level - 1) * defensePerLvl)));
        }
        lore.add(" ");
        lore.add(ChatColor.GOLD + this.abilityName1);
        ItemUtil.addLoreMessage(this.ability1Desc, this);
        lore.add(" ");
        lore.add(ChatColor.GOLD + this.abilityName2);
        ItemUtil.addLoreMessage(this.ability2Desc, this);
        if (abilityName3 != null) {
            lore.add(" ");
            lore.add(ChatColor.GOLD + this.abilityName3);
            ItemUtil.addLoreMessage(this.ability3Desc, this);
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Right-click to add this pet to");
        lore.add(ChatColor.YELLOW + "your pet menu!");
        lore.add(" ");
        lore.add(this.rarity);

        meta.setLore(lore);
        this.setItemMeta(meta);
        ItemHandler.setMaxStackSize(CraftItemStack.asNMSCopy(this).getItem(), 1);
    }

    public String getName(){return name;}

}
