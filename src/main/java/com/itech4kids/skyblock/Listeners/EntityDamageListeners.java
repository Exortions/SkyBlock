package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.Events.SkyblockEntitySkillGainEvent;
import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.ItemUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDamageEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class EntityDamageListeners implements Listener {

    private Main main;

    public EntityDamageListeners(){
       main = Main.getMain();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);

        if (e.getEntity() instanceof Player){
            if (!e.getEntity().hasMetadata("NPC")) {
                if (!e.isCancelled()) {
                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                    if (Math.round(e.getFinalDamage() - (e.getFinalDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) >= 1000000){
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ChatColor.GRAY + "" + Main.format(Math.round(e.getFinalDamage() - (e.getFinalDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE) / (skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                    }else {
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ChatColor.GRAY + "" + formatter.format(Math.round(e.getFinalDamage() - (e.getFinalDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE) / (skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                    }
                    skyblockPlayer.setStat(SkyblockStats.HEALTH, (int) (skyblockPlayer.getStat(SkyblockStats.HEALTH) - (e.getFinalDamage() - (e.getFinalDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                    e.setDamage(0);
                    skyblockPlayer.getBukkitPlayer().setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((e.getFinalDamage() - (e.getFinalDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                }
            }
        }else{
            if (!e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)){
                if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                    if (e.getFinalDamage() >= 1000000){
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ChatColor.GRAY + "" + Main.format((Math.round(e.getFinalDamage()))));
                    }else{
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ChatColor.GRAY + "" + formatter.format(Math.round(e.getFinalDamage())));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onNpcDamage(NPCDamageByEntityEvent e) throws IOException {
        int divider = 0;
        LivingEntity livingEntity = (LivingEntity) e.getNPC().getEntity();

        NPC npc = e.getNPC();

        e.setDamage(0);
        if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")){
            divider = 2000000;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 3000);
            //e.getNPC().setName(ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format(Math.round(livingEntity.getHealth() * 2000000)) + ChatColor.RED + "❤");
            //CustomAI.yetiAI(npc);

        }else if (e.getNPC().getName().contains(ChatColor.RED + "Frozen Steve")){
            divider = 700;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 0);
            //e.getNPC().setName(ChatColor.RED + "Frozen Steve " + ChatColor.GREEN + Math.round(livingEntity.getHealth() * 700) + ChatColor.RED + "❤");
            //CustomAI.frozenSteveAI(e.getNPC());
        }
        ((LivingEntity) e.getNPC().getEntity()).setHealth(livingEntity.getHealth());
        ((LivingEntity) e.getNPC().getEntity()).setMaxHealth(livingEntity.getMaxHealth());
    }

    public void loadDamage(NPC npc, int divider, Entity damager, SkillType skillType, int xp) throws IOException {
        LivingEntity livingEntity = (LivingEntity) npc.getEntity();
        SkyblockPlayer skyblockPlayer = main.getPlayer(damager.getName());
        int i = new Random().nextInt(100);
        int r = new Random().nextInt(100);

        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);
        double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5F) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100F);
        if (!npc.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                if (Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)) >= 1000000){
                    ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Main.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                }else{
                    ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                }
                if (((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider) >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - ((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider));
                }
            } else {
                if (Math.round(damage) >= 1000000){
                    ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Main.format(Math.round(damage)));
                }else{
                    ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage)));
                }
                if (damage / divider >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - damage / divider);
                }
            }
            if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                if (!skyblockPlayer.ferocityCooldown) {
                    for (int f = 1; f < Integer.valueOf(skyblockPlayer.getStat(SkyblockStats.FEROCITY)/100 + 1); ++f) {
                        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                        skyblockPlayer.ferocityCooldown = true;
                        Bukkit.getPluginManager().callEvent(new NPCDamageByEntityEvent(npc, new EntityDamageByEntityEvent(damager, livingEntity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage)));
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            skyblockPlayer.ferocityCooldown = false;
                        }
                    }.runTaskLater(main, 5);
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player) {
            if (!(e.getEntity() instanceof Player) && !(e.getEntity().hasMetadata("NPC"))) {
                if (!e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")) {

                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getDamager().getName());
                    int i = new Random().nextInt(100);
                    int r = new Random().nextInt(100);
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    formatter.setGroupingUsed(true);
                    double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5F) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100F);

                    if (e.getEntity().hasMetadata("identifier")){
                        SEntity sEntity = Main.getMain().handler.getEntity(e.getEntity());
                        if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                            sEntity.addDespawnDelay(15*20);
                            sEntity.setLastDamager(skyblockPlayer.getBukkitPlayer());
                            if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                                e.setDamage((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)/sEntity.getMaximumHealth());
                                if (Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)) >= 1000000){
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Main.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                                }else{
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                                }
                                sEntity.subtractHealth((int) Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)));
                            } else {
                                e.setDamage(damage/sEntity.getMaximumHealth());
                                if (Math.round(damage) >= 1000000){
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Main.format(Math.round(damage)));
                                }else{
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage)));
                                }
                                sEntity.subtractHealth((int) Math.round(damage));
                            }
                            if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                                if (!skyblockPlayer.ferocityCooldown) {
                                    skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                                    skyblockPlayer.ferocityCooldown = true;
                                    Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            skyblockPlayer.ferocityCooldown = false;
                                        }
                                    }.runTaskLater(main, 5);
                                }
                            }
                        }
                    }else{
                        if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                            if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                                e.setDamage(damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100);
                                if (Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)) >= 1000000){
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Main.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                                }else{
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                                }
                            } else {
                                e.setDamage(damage);
                                if (Math.round(damage) >= 1000000){
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Main.format(Math.round(damage)));
                                }else{
                                    ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage)));
                                }
                            }
                            if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                                if (!skyblockPlayer.ferocityCooldown) {
                                    skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                                    skyblockPlayer.ferocityCooldown = true;
                                    Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            skyblockPlayer.ferocityCooldown = false;
                                        }
                                    }.runTaskLater(main, 5);
                                }
                            }
                        }
                    }
                } else if (e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")) {
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                    if (npc.getName().contains(ChatColor.RED + "Yeti")) {
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, (int) (skyblockPlayer.getStat(SkyblockStats.HEALTH) - (300 - (300 * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                        e.setDamage((((300 - (300 * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                    } else if (npc.getName().contains(ChatColor.RED + "Frozen Steve")) {
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, (int) (skyblockPlayer.getStat(SkyblockStats.HEALTH) - (50 - (50 * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                        e.setDamage((((50 - (50 * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                    }
                }

            }else{
                if (!e.getDamager().hasMetadata("NPC")){
                    e.setCancelled(true);
                }

            }
        }else if (e.getDamager() instanceof Projectile){
            Projectile projectile = (Projectile) e.getDamager();
            if (projectile.getShooter() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                    return;
                } else {
                    SkyblockPlayer skyblockPlayer = main.getPlayer(((Player) projectile.getShooter()).getName());
                    int i = new Random().nextInt(100);
                    int r = new Random().nextInt(100);
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    formatter.setGroupingUsed(true);
                    double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5F) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100F);
                    if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                        if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                            e.setDamage(damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100);
                            if (Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)) >= 1000000){
                                ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Main.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                            }else{
                                ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                            }
                        } else {
                            e.setDamage(damage);
                            if (Math.round(damage) >= 1000000){
                                ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Main.format(Math.round(damage)));
                            }else{
                                ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage)));
                            }
                        }
                        if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                            if (!skyblockPlayer.ferocityCooldown) {
                                skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                                skyblockPlayer.ferocityCooldown = true;
                                Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        skyblockPlayer.ferocityCooldown = false;
                                    }
                                }.runTaskLater(main, 5);
                            }
                        }
                    }
                }
            }
        }else{
            if (e.getDamager().hasMetadata("identifier")) {
                SEntity sEntity = Main.getMain().handler.getEntity(e.getDamager());
                if (e.getEntity() instanceof Player){
                    sEntity.setDespawnDelay(15*20);
                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                    skyblockPlayer.setStat(SkyblockStats.HEALTH, (int) (skyblockPlayer.getStat(SkyblockStats.HEALTH) - (sEntity.getAttackDamage() - (sEntity.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F)))))));
                    e.setDamage(0);
                    skyblockPlayer.getBukkitPlayer().setHealth(skyblockPlayer.getBukkitPlayer().getHealth() - (((sEntity.getAttackDamage() - (sEntity.getAttackDamage() * ((skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100F))))) * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / (skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))))));
                }
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e){
        Entity entity = e.getEntity();
        if (e.getEntity().hasMetadata("identifier")) {
            SEntity sEntity = Main.getMain().handler.getEntity(entity);
            e.setDroppedExp(0);
            e.getDrops().clear();
            if (sEntity.getSkillType() != null){
                try {
                    Bukkit.getPluginManager().callEvent(new SkyblockEntitySkillGainEvent(Main.getMain().getPlayer(sEntity.getLastDamager().getName()), sEntity.getSkillType(), sEntity.getSkillExpDropped(), sEntity.getVanillaEntity()));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                if (sEntity.getCoins() > 0) {
                    entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), ItemHandler.createCoin(sEntity.getCoins()));
                }
            }
        }
    }

    @EventHandler
    public void onCustomMobDamage(EntityDamageEvent e){
        Entity entity = e.getEntity();
        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;
        if (e.getEntity().hasMetadata("identifier")) {
            SEntity sEntity = Main.getMain().handler.getEntity(e.getEntity());
            e.setDamage(e.getFinalDamage()/sEntity.getMaximumHealth());
        }
    }

    @EventHandler
    public void onNpcDamage(NPCDamageEvent e){
        LivingEntity livingEntity = (LivingEntity) e.getNPC().getEntity();

        NPC npc = e.getNPC();

        if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")){
            if (e.getDamage() > livingEntity.getHealth()*2000000){
                livingEntity.setHealth(0);
                livingEntity.remove();
                npc.destroy();
            }else {
                livingEntity.setHealth(livingEntity.getHealth() - e.getDamage() / 2000000);
            }
        }else if (e.getNPC().getName().contains(ChatColor.RED + "Frozen Steve")){
            if (e.getDamage() > livingEntity.getHealth()*700){
                livingEntity.setHealth(0);
                livingEntity.remove();
                npc.destroy();
            }else {
                livingEntity.setHealth(livingEntity.getHealth() - e.getDamage() / 700);
            }
        }
        e.setDamage(0);
        ((LivingEntity) e.getNPC().getEntity()).setHealth(livingEntity.getHealth());
        ((LivingEntity) e.getNPC().getEntity()).setMaxHealth(livingEntity.getMaxHealth());
    }
}
