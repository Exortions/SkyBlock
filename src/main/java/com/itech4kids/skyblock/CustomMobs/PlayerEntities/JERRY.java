package com.itech4kids.skyblock.CustomMobs.PlayerEntities;

import com.itech4kids.skyblock.Main;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftVillager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JERRY extends EntityVillager implements Listener {

    public ArmorStand jerry;
    public ArmorStand new_update;

    public JERRY(World world) {
        super(world);

        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        this.setCustomNameVisible(true);
        this.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");

        jerry = this.getWorld().getWorld().spawn(new Location(this.getWorld().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.25, this.getBukkitEntity().getLocation().getZ()), ArmorStand.class);
        new_update = this.getWorld().getWorld().spawn(new Location(this.getWorld().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.5, this.getBukkitEntity().getLocation().getZ()), ArmorStand.class);

        jerry.teleport(new Location(this.getWorld().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.25, this.getBukkitEntity().getLocation().getZ()));
        new_update.teleport(new Location(this.getWorld().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY() + 0.5, this.getBukkitEntity().getLocation().getZ()));

        jerry.setCustomNameVisible(true);
        jerry.setCustomName(ChatColor.WHITE + "Jerry");
        jerry.setVisible(false);
        jerry.setGravity(false);

        new_update.setCustomNameVisible(true);
        new_update.setCustomName(ChatColor.GOLD + "" + ChatColor.BOLD + "NEW UPDATE");
        new_update.setVisible(false);
        new_update.setGravity(false);

        Villager villager = (Villager) this.getBukkitEntity();
        villager.setProfession(Villager.Profession.FARMER);
        villager.setAdult();

        this.setYawPitch(157, 0);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (villager.isDead()){
                    cancel();
                    villager.remove();
                }else{
                    for (Entity entity : villager.getNearbyEntities(3, 3, 3)) {
                        if (entity instanceof Player) {
                            ((CraftVillager) villager).getHandle().setGoalTarget(((CraftLivingEntity) entity).getHandle());
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 5L);


        NBTTagCompound tag = this.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        this.c(tag);
        tag.setInt("NoAI", 1);
        this.f(tag);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity().equals(this.getBukkitEntity()) || e.getEntity().equals(jerry) || e.getEntity().equals(new_update)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onArmorStand(PlayerArmorStandManipulateEvent e){
        if (e.getRightClicked().equals(jerry) || e.getRightClicked().equals(new_update)){
            e.setCancelled(true);
        }
    }
}
