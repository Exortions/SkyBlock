package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockGuiItem;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockSkillGuiItem;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockStatItem;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPet;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPetsItem;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemUtil {

    public static void addLoreMessage(String string, SkyblockGuiItem item){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                item.lore.add(ChatColor.GRAY + tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            item.lore.add(ChatColor.GRAY + tmp);
        }
    }

    public static void addLoreMessage(String string, SkyblockStatItem item){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                item.lore.add(ChatColor.GRAY + tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            item.lore.add(ChatColor.GRAY + tmp);
        }
    }

    public static void addLoreMessage(String string, SkyblockSkillGuiItem item){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                item.lore.add(ChatColor.GRAY + tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            item.lore.add(ChatColor.GRAY + tmp);
        }
    }

    public static void addLoreMessage(String string, SkyblockPetsItem item){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                item.lore.add(tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            item.lore.add(tmp);
        }
    }

    public static List<String> addLoreMessage(String string, List<String> itemMeta){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                itemMeta.add(ChatColor.GRAY + tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            itemMeta.add(ChatColor.GRAY + tmp);
        }
        return itemMeta;
    }

    public static void addItalicLore(String string, SkyblockStatItem item){
        String[] strings = string.split(" ");
        String tmp = "";
        for (String s : strings){
            if ((s.length() + 1 + tmp.length()) <= 32){
                tmp = tmp + s + " ";
            } else {
                item.lore.add(ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + tmp);
                tmp = s + " ";
            }
        }
        if (tmp.length() > 0) {
            item.lore.add(ChatColor.DARK_GRAY + " " + ChatColor.ITALIC + tmp);
        }
    }

    public static void setCarriedItem(Enderman entity, ItemStack item) {
        entity.setCarriedMaterial(item.getData());
    }

    public static void setDamageIndicator(final Location loc, final String displayname) {
        double randomX = Math.random();
        double randomY = Math.random();
        double randomZ = Math.random();
        randomX -= 0.5;
        randomY += 0.25;
        randomZ -= 0.5;
        final ArmorStand as = (ArmorStand)loc.getWorld().spawnEntity(loc.add(randomX, randomY, randomZ), EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setGravity(false);
        as.setCustomName(displayname);
        final NBTEntity nbtas = new NBTEntity(as);
        nbtas.setBoolean("Invisible", Boolean.valueOf(true));
        nbtas.setBoolean("Gravity", Boolean.valueOf(false));
        nbtas.setBoolean("CustomNameVisible", Boolean.valueOf(true));
        nbtas.setBoolean("Marker", Boolean.valueOf(true));
        nbtas.setBoolean("Invulnerable", Boolean.valueOf(true));
        Main.damage_indicator.add(as);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (!as.isDead()) {
                    as.remove();
                }
                if (as.isDead()) {
                    Main.damage_indicator.remove(as);
                }
            }
        }, 20L);
    }

    public static String addCritTexture(final String str) {
        String new_string = null;
        if (str.length() == 1) {
            new_string = "§f\u2726§e" + str + "§c\u2726";
        }
        if (str.length() == 2) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§c\u2726";
        }
        if (str.length() == 3) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c\u2726";
        }
        if (str.length() == 4) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + "\u2726";
        }
        if (str.length() == 5) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + String.valueOf(str.charAt(4)) + "§f\u2726";
        }
        if (str.length() == 6) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + "§c" + String.valueOf(str.charAt(3)) + String.valueOf(str.charAt(4)) + String.valueOf(str.charAt(5)) + "§f\u2726";
        }
        if (str.length() == 7) {
            new_string = "§f\u2726" + String.valueOf(str.charAt(0)) + "§e" + String.valueOf(str.charAt(1)) + "§6" + String.valueOf(str.charAt(2)) + String.valueOf(str.charAt(3)) + "§c" + String.valueOf(str.charAt(4)) + String.valueOf(str.charAt(5)) + String.valueOf(str.charAt(6)) + "§f\u2726";
        }
        return new_string;
    }
}
