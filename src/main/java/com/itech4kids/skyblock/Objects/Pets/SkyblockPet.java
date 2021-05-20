package com.itech4kids.skyblock.Objects.Pets;

import com.itech4kids.skyblock.Main;
import net.citizensnpcs.npc.ai.speech.Chat;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.EntityZombie;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class SkyblockPet {

    public static ArmorStand spawnArmorStand(Player owner, ItemStack item) {

        Location loc = owner.getLocation();
        Vector dir = loc.getDirection();
        dir.normalize();
        dir.multiply(-2);
        loc.add(dir);

        ArmorStand armorStand = owner.getWorld().spawn(loc, ArmorStand.class);

        updateDisplay(owner, armorStand);
        armorStand.setHelmet(item);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        if (item.getItemMeta().getDisplayName().split(" ").length == 3) {
            armorStand.setCustomName(item.getItemMeta().getDisplayName().split(" ")[0] + item.getItemMeta().getDisplayName().split(" ")[1] + " " + ChatColor.getLastColors(item.getItemMeta().getDisplayName()) + owner.getName() + "'s " + item.getItemMeta().getDisplayName().split(" ")[2]);
        }else{
            armorStand.setCustomName(item.getItemMeta().getDisplayName().split(" ")[0] + item.getItemMeta().getDisplayName().split(" ")[1] + " " + ChatColor.getLastColors(item.getItemMeta().getDisplayName()) + owner.getName() + "'s " + item.getItemMeta().getDisplayName().split(" ")[2] + " " + item.getItemMeta().getDisplayName().split(" ")[3]);
        }
        armorStand.setCustomNameVisible(true);

        return armorStand;
    }

    private static void updateDisplay(Player player, ArmorStand armorStand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Location entity = player.getLocation();
                Vector dir = entity.getDirection();
                dir.normalize();
                dir.multiply(-2);
                dir.setZ(dir.getZ() + 1);
                entity.add(dir);

                if (armorStand != null) {
                    armorStand.setRemoveWhenFarAway(false);
                    armorStand.teleport(new Location(entity.getWorld(), entity.getX(), entity.getY() + 0.75, entity.getZ(), player.getLocation().getYaw(), player.getLocation().getPitch()));
                } else {
                    cancel();
                }

            }
        }.runTaskTimer(Main.getMain(), 5L, 5L);
    }
}

