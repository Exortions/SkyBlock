package com.itech4kids.skyblock.CustomMobs;

import com.itech4kids.skyblock.Main;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class SEntityAI {

    public static void runWeaverSpiderAI(SEntity weaverSpider){

    }

    public static void runDasherSpiderAI(SEntity sEntity){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (sEntity.getVanillaEntity().isDead()){
                    cancel();
                }else{
                    for (Entity entity : sEntity.getVanillaEntity().getNearbyEntities(5, 2, 5)){
                        if (entity instanceof Player){
                            if (sEntity.getVanillaEntity().getType().equals(EntityType.SPIDER)){
                                Spider spider = (Spider) sEntity.getVanillaEntity();
                                spider.setTarget((LivingEntity) entity);
                                spider.setVelocity(spider.getLocation().getDirection().multiply(1.0D));
                                AttributeInstance attributes = ((EntityInsentient)((CraftEntity)sEntity.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                                attributes.setValue(0.2);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 40);
    }

    public static void runSpiderAI(SEntity sEntity){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (sEntity.getVanillaEntity().isDead()){
                    cancel();
                }else{
                    for (Entity entity : sEntity.getVanillaEntity().getNearbyEntities(5, 2, 5)){
                        if (entity instanceof Player){
                            if (sEntity.getVanillaEntity().getType().equals(EntityType.WOLF)){
                                Spider wolf = (Spider) sEntity.getVanillaEntity();
                                wolf.setTarget((LivingEntity) entity);
                                AttributeInstance attributes = ((EntityInsentient)((CraftEntity)sEntity.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                                attributes.setValue(0.4);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 40);
    }

    public static void runWolfAI(SEntity sEntity){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (sEntity.getVanillaEntity().isDead()){
                    cancel();
                }else{
                    for (Entity entity : sEntity.getVanillaEntity().getNearbyEntities(5, 2, 5)){
                        if (entity instanceof Player){
                            if (sEntity.getVanillaEntity().getType().equals(EntityType.WOLF)){
                                Wolf wolf = (Wolf) sEntity.getVanillaEntity();
                                wolf.setAngry(true);
                                wolf.setTarget((LivingEntity) entity);
                                AttributeInstance attributes = ((EntityInsentient)((CraftEntity)sEntity.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                                attributes.setValue(0.4);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 40);
    }

    public static void runGhoulAI(SEntity sEntity){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (sEntity.getVanillaEntity().isDead()){
                    cancel();
                }else{
                    for (Entity entity : sEntity.getVanillaEntity().getNearbyEntities(5, 2, 5)){
                        if (entity instanceof Player){
                            if (sEntity.getVanillaEntity().getType().equals(EntityType.ZOMBIE)){
                                Zombie zombie = (Zombie) sEntity.getVanillaEntity();
                                zombie.setTarget((LivingEntity) entity);
                                zombie.setVillager(false);
                                AttributeInstance attributes = ((EntityInsentient)((CraftEntity)sEntity.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                                attributes.setValue(0.3);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 40);
    }
}
