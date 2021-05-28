package com.itech4kids.skyblock.CustomMobs.Slayer;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.RevenantBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.SvenBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.TarantulaBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss.SvenPups;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPet;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.itech4kids.skyblock.Util.LaunchPadConfig;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class SlayerAI {

    public static Vector getDifferentialVector(Location from, Location to) {
        return new Vector((to.getX() - from.getX()), to.getY() - from.getY(), (to.getZ() - from.getZ()));
    }

    public static void setSpeed(Vector v, double speed){
        double init = v.length();
        double m = speed/init;
        v.multiply(m);
    }

    public static void runTarantulaAI(TarantulaBoss tarantula, int bossLevel, Player spawner, CaveSpider caveSpider){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (tarantula.getVanillaEntity().isDead()){
                    caveSpider.remove();
                    cancel();
                }else{
                    if (tarantula.getVanillaEntity().getType().equals(EntityType.SPIDER)) {
                        Spider spider = (Spider) tarantula.getVanillaEntity();
                        if (spawner.isOnline()) {
                            spider.setTarget(spawner);
                        }else{
                        }

                        if (spider.getLocation().distance(spawner.getLocation()) > 10){
                            Vector vector = getDifferentialVector(spider.getLocation(), spawner.getLocation());
                            spider.setVelocity(vector);
                        }

                        caveSpider.setCustomNameVisible(true);
                        caveSpider.setCustomName(tarantula.getVanillaEntity().getCustomName());
                        tarantula.getVanillaEntity().setPassenger(caveSpider);

                        if (bossLevel >= 2){
                            if (new Random().nextInt(20*6) == 1){
                                for (Entity entity : spider.getNearbyEntities(3, 3, 3)){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(spider, entity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, tarantula.getAttackDamage()));
                                    if (entity instanceof Player){
                                        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(entity.getName());
                                        ((Player) entity).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((tarantula.getAttackDamage() - (tarantula.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                    }
                                }
                            }
                        }

                        AttributeInstance attributes = ((EntityInsentient)((CraftEntity)tarantula.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                        attributes.setValue(0.4);
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 20);
    }

    public static void runSvenAI(SvenBoss sven, int bossLevel, Player spawner){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (sven.getVanillaEntity().isDead()){
                    for (SvenPups pups : sven.pups){
                        pups.subtractHealth(Integer.MAX_VALUE);
                    }
                    cancel();
                }else{
                    if (sven.getVanillaEntity().getType().equals(EntityType.WOLF)) {
                        Wolf wolf = (Wolf) sven.getVanillaEntity();
                        wolf.setAngry(true);
                        if (spawner.isOnline()) {
                            wolf.setTarget(spawner);
                        }else{
                        }

                        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(spawner.getName());
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, skyblockPlayer.getStat(SkyblockStats.HEALTH) - sven.getTrueDPS());
                        ItemUtil.setDamageIndicator(spawner.getLocation(), ChatColor.GRAY + "" + sven.getTrueDPS() + "");
                        ((Player) spawner).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((sven.getTrueDPS() * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH)))))));


                        if (bossLevel >= 3) {
                            if (!sven.hasCalledPups()) {
                                if (sven.getHealth() <= sven.getMaximumHealth() / 2) {
                                    for (int i = 0; i < 10; ++i) {
                                        SvenPups pups = new SvenPups(wolf.getLocation(), sven);
                                        sven.pups.add(pups);
                                    }
                                    sven.callPups();
                                    spawner.playSound(spawner.getLocation(), Sound.WOLF_HOWL, 10, 2);
                                }
                            }
                        }

                        AttributeInstance attributes = ((EntityInsentient)((CraftEntity)sven.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                        attributes.setValue(0.5);
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 20);
    }

    public static void runRevenantAI(RevenantBoss revenant, int bossLevel, Player spawner){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (revenant.getVanillaEntity().isDead()){
                    cancel();
                }else{
                    if (revenant.getVanillaEntity().getType().equals(EntityType.ZOMBIE)){
                        Zombie zombie = (Zombie) revenant.getVanillaEntity();
                        Random rand = new Random();

                        if (spawner.isOnline()){
                            zombie.setTarget(spawner);
                        }else{
                        }
                        switch (bossLevel){
                            case 1:
                                if (rand.nextInt(4*20) == 1){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageEvent(spawner, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(spawner.getName());
                                    ((Player) spawner).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                }
                                break;
                            case 2:
                                if (rand.nextInt(4*20) == 1){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageEvent(spawner, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(spawner.getName());
                                    ((Player) spawner).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                }
                                if (rand.nextInt(6*20) == 1){
                                    for (Entity entity : zombie.getNearbyEntities(3, 3, 3)){
                                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                        if (entity instanceof Player){
                                            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(entity.getName());
                                            ((Player) entity).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                        }
                                    }
                                }
                                break;
                            case 3:
                                if (rand.nextInt(4*20) == 1){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageEvent(spawner, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(spawner.getName());
                                    ((Player) spawner).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                }
                                if (rand.nextInt(6*20) == 1){
                                    for (Entity entity : zombie.getNearbyEntities(3, 3, 3)){
                                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                        if (entity instanceof Player){
                                            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(entity.getName());
                                            ((Player) entity).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                        }
                                    }
                                }
                                if (rand.nextInt(6*20) == 1){
                                    for (Entity entity : zombie.getNearbyEntities(3, 3, 3)){
                                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                        if (entity instanceof Player){
                                            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(entity.getName());
                                            ((Player) entity).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                        }
                                    }
                                }
                                break;
                            case 4:
                                if (rand.nextInt(4*20) == 1){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageEvent(spawner, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(spawner.getName());
                                    ((Player) spawner).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                }
                                if (rand.nextInt(6*20) == 1){
                                    for (Entity entity : zombie.getNearbyEntities(3, 3, 3)){
                                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                        if (entity instanceof Player){
                                            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(entity.getName());
                                            ((Player) entity).setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((revenant.getAttackDamage() - (revenant.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                                        }
                                    }
                                }
                                if (rand.nextInt(40*20) == 1){
                                    ItemStack oldChestplate = zombie.getEquipment().getChestplate();
                                    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                    LeatherArmorMeta chestplateMeta2 = (LeatherArmorMeta) chestplate.getItemMeta();
                                    chestplateMeta2.setColor(Color.RED);
                                    chestplate.addEnchantment(Enchantment.DURABILITY, 1);
                                    chestplate.setItemMeta(chestplateMeta2);

                                    zombie.getEquipment().setChestplate(chestplate);
                                    revenant.addAttackDamage(revenant.getAttackDamage());

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            zombie.getEquipment().setChestplate(oldChestplate);
                                            revenant.setAttackDamage(revenant.getAttackDamage()/2);
                                        }
                                    }.runTaskLater(Main.getMain(), 600);

                                }
                                break;
                            case 5:
                                if (rand.nextInt(4) == 1){
                                    Bukkit.getPluginManager().callEvent(new EntityDamageEvent(spawner, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                }
                                if (rand.nextInt(6) == 1){
                                    for (Entity entity : zombie.getNearbyEntities(3, 3, 3)){
                                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, revenant.getAttackDamage()));
                                    }
                                }
                                if (rand.nextInt(40*20) == 1){
                                    ItemStack oldChestplate = zombie.getEquipment().getChestplate();
                                    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                                    LeatherArmorMeta chestplateMeta2 = (LeatherArmorMeta) chestplate.getItemMeta();
                                    chestplateMeta2.setColor(Color.RED);
                                    chestplate.addEnchantment(Enchantment.DURABILITY, 1);
                                    chestplate.setItemMeta(chestplateMeta2);

                                    zombie.getEquipment().setChestplate(chestplate);
                                    revenant.addAttackDamage(revenant.getAttackDamage());

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            zombie.getEquipment().setChestplate(oldChestplate);
                                            revenant.setAttackDamage(revenant.getAttackDamage()/2);
                                        }
                                    }.runTaskTimer(Main.getMain(), 5L, 400);

                                }
                                break;
                        }

                        AttributeInstance attributes = ((EntityInsentient)((CraftEntity)revenant.getVanillaEntity()).getHandle()).getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);

                        attributes.setValue(0.4);

                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 1);
    }
}
