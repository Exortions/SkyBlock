package com.itech4kids.skyblock.CustomMobs;

import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Events.SkyblockEntitySkillGainEvent;
import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class SEntity {

    private EntityType entityType;

    private int health;
    private int maximumHealth;
    private int level;

    private boolean isUndead;
    private HashMap<String, ItemStack> equipment;
    private String name;

    private int damage;
    public Entity vanillaEntity;

    private int lifespan;

    private Player lastDamager;
    private int skillExpDropped;
    private SkillType skillType;

    private int coins;

    public SEntity(EntityType entityType){
        this.entityType = entityType;
        this.lifespan = 20*15;
        this.coins = 0;
        this.skillType = null;
        lastDamager = null;
    }

    public boolean loadStats(int health, int damage, boolean isUndead, HashMap<String, ItemStack> equipment, String name, int level){
        this.health = health;
        this.maximumHealth = health;
        this.level = level;
        this.isUndead = isUndead;
        this.equipment = equipment;
        this.name = name;
        this.damage = damage;
        return true;
    }

    public Entity spawn(Location location){
        Entity entity = location.getWorld().spawn(location, entityType.getEntityClass());

        if (entity instanceof LivingEntity){
            LivingEntity lentity = (LivingEntity) entity;

            if (equipment.size() > 0 && isUndead){
                lentity.getEquipment().setItemInHand(equipment.get("iteminhand"));
                lentity.getEquipment().setHelmet(equipment.get("helmet"));
                lentity.getEquipment().setChestplate(equipment.get("chestplate"));
                lentity.getEquipment().setLeggings(equipment.get("leggings"));
                lentity.getEquipment().setBoots(equipment.get("boots"));
            }

            if (lentity.getType().equals(EntityType.ENDERMAN)){
                if (equipment.get("iteminhand") != null) {
                    ItemUtil.setCarriedItem((Enderman) entity, equipment.get("iteminhand"));
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (entity.isDead()){
                        Main.getMain().handler.unRegisterEntity(entity);
                        cancel();
                    }else{
                        entity.setCustomNameVisible(true);
                        entity.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + (health) + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + (maximumHealth) + ChatColor.RED + "❤");
                        lifespan = lifespan - 1;
                        if (lifespan <= 0){
                            entity.remove();
                            Main.getMain().handler.unRegisterEntity(entity);
                            cancel();

                        }
                        if (health <= 0){
                            try {
                                if (skillType != null && lastDamager != null) {
                                    Bukkit.getPluginManager().callEvent(new SkyblockEntitySkillGainEvent(Main.getMain().getPlayer(lastDamager.getName()), getSkillType(), skillExpDropped, getVanillaEntity()));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (coins > 0) {
                                entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), ItemHandler.createCoin(coins));
                            }
                            entity.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + 0 + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maximumHealth + ChatColor.RED + "❤");
                            Main.getMain().handler.unRegisterEntity(entity);
                            lentity.setHealth(0);
                            cancel();

                        }
                    }
                }
            }.runTaskTimer(Main.getMain(), 5L, 1);
        }

        vanillaEntity = entity;
        return entity;
    }

    public void setCoins(int i){
        coins = i;
    }

    public int getCoins(){
        return coins;
    }

    public int getSkillExpDropped(){
        return skillExpDropped;
    }

    public SkillType getSkillType(){
        return skillType;
    }

    public void setSkillType(SkillType type){
        this.skillType = type;
    }

    public void setLastDamager(Player player){
        lastDamager = player;
    }

    public void setSkillExpDropped(int i){
        skillExpDropped = i;
    }

    public boolean registerEntity(){
        boolean b;
        try {
            Main.getMain().handler.registerEntity(this);
            b = true;
        }catch (Exception e){
            b = false;
            e.printStackTrace();
        }
        return b;
    }

    public Entity getVanillaEntity(){
        return vanillaEntity;
    }

    public int getMaximumHealth(){
        return maximumHealth;
    }

    public int getHealth(){
        return health;
    }

    public int subtractHealth(int subtract){
        health = health - subtract;
        return health;
    }

    public int getAttackDamage(){
        return damage;
    }

    public int setDespawnDelay(int i){
        lifespan = i;
        return lifespan;
    }

    public int addDespawnDelay(int i){
        lifespan = lifespan + i;
        return lifespan;
    }

    public Player getLastDamager(){
        return lastDamager;
    }

    public int getDespawnDelay(){
        return lifespan;
    }

    public int getLevel(){
        return level;
    }

    public String getName(){
        return name;
    }

    public void setAttackDamage(int i){
        damage = i;
    }

    public void addAttackDamage(int i){
        damage = damage + i;
    }
}
