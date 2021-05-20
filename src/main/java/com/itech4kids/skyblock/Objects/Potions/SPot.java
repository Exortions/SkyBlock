package com.itech4kids.skyblock.Objects.Potions;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Util.ItemUtil;
import net.minecraft.server.v1_8_R3.ItemPotion;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;

import java.time.LocalTime;
import java.util.*;

import static org.apache.commons.lang.StringUtils.repeat;

public class SPot {

    private ItemStack item;
    private ItemMeta meta;
    private List<String> lore;

    private ChatColor color;
    private SPotType type;

    private int duration;
    private int potency;

    public SPot(SPotType type, int potency, int duration){

        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        lore = new ArrayList<>();

        this.type = type;
        this.duration = duration;
        this.potency = potency;

        switch (type){
            case SPEED:
                color = ChatColor.WHITE;
                break;
            case JUMP_BOOST:
                color = ChatColor.AQUA;
                break;
            case HEALING:
            case FIRE_RESISTANCE:
                color = ChatColor.RED;
                break;
            case POISON:
                color = ChatColor.DARK_GREEN;
                break;
            case WATER_BREATHING:
            case MANA:
                color = ChatColor.BLUE;
                break;
            case ABSORPTION:
                color = ChatColor.GOLD;
                break;
            case RABBIT:
                color = ChatColor.GREEN;
                break;

        }

        lore.add(" ");
        lore.add(color + WordUtils.capitalize(type.name().toLowerCase().replaceAll("_", " ")) + " "  + intToRomanNumeral(potency) + ChatColor.WHITE + " (" + convertTime(duration) + ")");

        switch (type){
            case SPEED:
                ItemUtil.addLoreMessage("Grants +" + (potency * 5) + "✦ Speed.", lore);
                break;
            case JUMP_BOOST:
                ItemUtil.addLoreMessage("Increases your jump height by " + potency + " blocks.", lore);
                break;
            case HEALING:
                ItemUtil.addLoreMessage("Instantly heals for " + (potency * 20) + " HP", lore);
                break;
            case POISON:
                ItemUtil.addLoreMessage("Deals " + (potency * 15) + " damager per second.", lore);
                break;
            case WATER_BREATHING:
                ItemUtil.addLoreMessage("Grants a " + (potency * 15) + "% chance of not taking drowning damage.", lore);
                break;
            case FIRE_RESISTANCE:
                ItemUtil.addLoreMessage("Grants immunity to fire and lava", lore);
                break;
            case ABSORPTION:
                ItemUtil.addLoreMessage("Grants +" + (potency * 30) + " HP of absorption.", lore);
                break;
            case RABBIT:
                ItemUtil.addLoreMessage("Grants Jump Boost " + (potency - 1) + " and +" + (potency*10) + "✦ Speed", lore);
                break;
            case MANA:
                ItemUtil.addLoreMessage("Grants +" + potency + " RR✎ Mana per second.", lore);
                break;
        }

        lore.add(" ");
        switch (potency){
            case 1:
            case 2:
                lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                meta.setDisplayName(ChatColor.WHITE + WordUtils.capitalize(type.name().toLowerCase().replaceAll("_", " ")) + " " + intToRomanNumeral(potency));
                break;
            case 3:
            case 4:
                lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");
                meta.setDisplayName(ChatColor.GREEN + WordUtils.capitalize(type.name().toLowerCase().replaceAll("_", " ")) + " " + intToRomanNumeral(potency));
                break;
            case 5:
            case 6:
                lore.add(ChatColor.BLUE + "" + ChatColor.BOLD + "RARE");
                meta.setDisplayName(ChatColor.BLUE + WordUtils.capitalize(type.name().toLowerCase().replaceAll("_", " ")) + " " + intToRomanNumeral(potency));
                break;
            case 7:
            case 8:
                lore.add(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "EPIC");
                meta.setDisplayName(ChatColor.DARK_PURPLE + WordUtils.capitalize(type.name().toLowerCase().replaceAll("_", " ")) + " " + intToRomanNumeral(potency));
                break;
        }

        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public int getPotency(){
        return potency;
    }

    public int getDuration(){
        return duration;
    }

    public SPotType getType(){
        return type;
    }

    public ItemStack getItem(){
        return item;
    }

    private String convertTime(int nSecondTime) {
        return LocalTime.MIN.plusSeconds(nSecondTime).toString();
    }

    private String intToRomanNumeral(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        String res = "";
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res += repeat(entry.getKey(), matches);
            Int = Int % entry.getValue();
        }
        return res;
    }

    public String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

}
