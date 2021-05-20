package com.itech4kids.skyblock.CustomMobs.Dragon;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.minecraft.server.v1_8_R3.EntityEnderDragon;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class SkyblockDragon extends EntityEnderDragon implements Listener {

    public String name;
    public ArrayList<SkyblockPlayer> damagers;
    public int health;
    public int maxHealth;

    public SkyblockDragon(SkyblockDragonType dragonType, World world) {
        super(world);
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        this.damagers = new ArrayList<SkyblockPlayer>();
        Entity entity = this.getBukkitEntity();
        Damageable enderDragon = (Damageable) entity;
        if (dragonType.equals(SkyblockDragonType.SUPERIOR)) {
            this.name = ChatColor.RED + "Superior Dragon";
            this.health = 12000000;
            this.maxHealth = 12000000;
        } else if (dragonType.equals(SkyblockDragonType.OLD)) {
            this.name = ChatColor.RED + "Old Dragon";
            this.health = 16000000;
            this.maxHealth = 16000000;
            super.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
        } else if (dragonType.equals(SkyblockDragonType.PROTECTOR)) {
            this.name = ChatColor.RED + "Protector Dragon";
            this.health = 7500000;
            this.maxHealth = 7500000;
        } else if (dragonType.equals(SkyblockDragonType.STRONG)) {
            this.name = ChatColor.RED + "Strong Dragon";
            this.health = 7500000;
            this.maxHealth = 7500000;
        } else if (dragonType.equals(SkyblockDragonType.UNSTABLE)) {
            this.name = ChatColor.RED + "Unstable Dragon";
            this.health = 7500000;
            this.maxHealth = 7500000;
        } else if (dragonType.equals(SkyblockDragonType.WISE)) {
            this.name = ChatColor.RED + "Wise Dragon";
            this.health = 7500000;
            this.maxHealth = 7500000;
        } else if (dragonType.equals(SkyblockDragonType.YOUNG)) {
            this.name = ChatColor.RED + "Young Dragon";
            this.health = 6000000;
            this.maxHealth = 6000000;
            super.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(3);
        }
        this.setCustomName(name);
        this.setCustomNameVisible(false);
        enderDragon.setHealth(1);
        enderDragon.setMaxHealth(1);
    }
}
