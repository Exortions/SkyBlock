package com.itech4kids.skyblock.Util;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Events.SkyblockMagicDamageEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class Util {

    public static String intToNumeral(int i){
        String str = null;
        if(i == 1){
            str = "I";
        } else if(i == 2){
            str = "II";
        } else if(i == 3){
            str = "III";
        } else if(i == 4){
            str = "IV";
        } else if(i == 5){
            str = "V";
        } else if(i == 6){
            str = "VI";
        } else if(i == 7){
            str = "VII";
        } else if(i == 8){
            str = "VIII";
        } else if(i == 9){
            str = "IX";
        } else if(i == 10){
            str = "X";
        } else if(i == 11){
            str = "XI";
        } else if(i == 12){
            str = "XII";
        } else if(i == 13){
            str = "XIII";
        } else if(i == 14){
            str = "XIV";
        } else if(i == 15){
            str = "XV";
        } else if(i == 16){
            str = "XVI";
        } else{
            str = Integer.toString(i);
        }
        return str;
    }

    public static int percentage(int c, int m){
        int i = c*100/m;
        return i;
    }

    public static List<String> returnLore(ItemMeta meta){
        List<String> lore = meta.getLore();
        return lore;
    }

    public static void setItemLore(ItemMeta meta, List<String> lore){
        meta.setLore(lore);
    }

    public static void addLore(ItemMeta meta, List<String> lore){
        List<String> itemLore = meta.getLore();
        for(String str : lore){
            itemLore.add(str);
        }
    }

    public static ArmorStand throwItem(Player owner, ItemStack head, Location loc, Vector vec){
        ArmorStand armorStand = loc.getWorld().spawn(loc, ArmorStand.class);
        armorStand.setItemInHand(head);
        armorStand.setVelocity(vec);
        armorStand.setVisible(false);
        return armorStand;
    }

}