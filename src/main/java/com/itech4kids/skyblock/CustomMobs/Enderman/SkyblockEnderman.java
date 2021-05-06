package com.itech4kids.skyblock.CustomMobs.Enderman;

import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.ItemUtil;
import net.minecraft.server.v1_8_R3.EntityEnderman;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class SkyblockEnderman extends EntityEnderman implements Listener {

    public SkyblockEndermanType endermanType;
    public int level;
    public String mobName;
    public int health;
    public int maxHealth;
    public String name;
    public ArmorStand healthDisplay;
    public Damageable enderman;
    public double xp;

    public SkyblockEnderman(SkyblockEndermanType endermanType, World world) {
        super(world);
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        healthDisplay = this.getBukkitEntity().getWorld().spawn(new Location(this.getBukkitEntity().getLocation().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.75, this.getBukkitEntity().getLocation().getZ()), ArmorStand.class);
        this.endermanType = endermanType;
        enderman = (Damageable) this.getBukkitEntity();
        if (endermanType == SkyblockEndermanType.FOUR_K){
            this.level = 42;
            this.health = 4000;
            this.maxHealth = 4000;
            this.xp = 63;
            this.name = "Enderman";
            this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
        }else if (endermanType == SkyblockEndermanType.SIX_K){
            this.level = 45;
            this.health = 6000;
            this.maxHealth = 6000;
            this.xp = 72;
            this.name = "Enderman";
            this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
        }else if (endermanType == SkyblockEndermanType.NINE_K){
            this.level = 50;
            this.health = 9000;
            this.maxHealth = 9000;
            this.xp = 81;
            this.name = "Enderman";
            this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
        }else if (endermanType == SkyblockEndermanType.ZEALOT){
            this.level = 55;
            this.health = 13000;
            this.maxHealth = 13000;
            this.name = "Zealot";
            this.xp = 90;
            this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
        }else if (endermanType == SkyblockEndermanType.SPECIAL_ZEALOT){
            this.level = 55;
            this.health = 13000;
            this.maxHealth = 13000;
            this.name = "Zealot";
            this.xp = 90;
            this.mobName = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
            ItemUtil.setCarriedItem((org.bukkit.entity.Enderman) this.getBukkitEntity(), new ItemStack(org.bukkit.Material.ENDER_PORTAL_FRAME));
        }

        this.setCustomNameVisible(false);
        this.healthDisplay.setCustomNameVisible(true);
        this.healthDisplay.setGravity(false);
        this.healthDisplay.setVisible(false);
        this.healthDisplay.setSmall(true);
        this.healthDisplay.setCustomName(mobName);
        updateHealth(this.getBukkitEntity(), healthDisplay);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity().equals(this.getBukkitEntity())){
            if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)){
                health = (int) (health - e.getFinalDamage());
                e.setDamage(0);
                if (health <= 0){
                    enderman.setHealth(0);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) throws IOException {
        if (e.getEntity().equals(this.getBukkitEntity())){
            health = (int) (health - e.getFinalDamage());
            e.setDamage(0);
            if (health <= 0){
                enderman.setHealth(0);
                if (e.getDamager() instanceof Player){
                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(e.getDamager().getName());
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.COMBAT, xp));
                }
            }
        }
    }

    public void updateHealth(CraftEntity entity, ArmorStand armorStand) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (entity.isDead()){
                    cancel();
                    armorStand.remove();
                }else{
                    armorStand.teleport(new Location(entity.getLocation().getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + 1.75, entity.getLocation().getZ()));
                    String s = ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + level + ChatColor.DARK_GRAY + "] " + ChatColor.RED + name + " " + ChatColor.GREEN + health + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + maxHealth;
                    armorStand.setCustomName(s);
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 5L);
    }

}
