package com.itech4kids.skyblock.Events;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEvent;

import java.text.DecimalFormat;

public class SkyblockMagicDamageEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    public SkyblockMagicDamageEvent(Player damager, LivingEntity entity, double scaling, int nearbyX, int nearbyY, int nearbyZ, String name, int abilityDamage) {
        super(damager);

        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(damager.getName());

        for (Entity entity1 : player.getNearbyEntities(nearbyX, nearbyY, nearbyZ)) {
            if (entity1 instanceof LivingEntity) {
                entity = (LivingEntity) entity1;
                if (entity1 instanceof Player && !entity1.hasMetadata("NPC")){
                    return;
                }else {
                    if (!entity.hasMetadata("NPC")) {
                        Bukkit.getPluginManager().callEvent(new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, (skyblockPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + abilityDamage) * (1 + (skyblockPlayer.getStat(SkyblockStats.MANA) / 100F) * scaling)));
                        if (entity.hasMetadata("identifier")){
                            SEntity sEntity = Main.getMain().handler.getEntity(entity);
                            sEntity.subtractHealth((int) ((skyblockPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + abilityDamage) * (1 + (skyblockPlayer.getStat(SkyblockStats.MANA) / 100F) * scaling)));
                        }
                    } else {
                        Bukkit.getPluginManager().callEvent(new NPCDamageEvent(CitizensAPI.getNPCRegistry().getNPC(entity), new EntityDamageEvent(entity, EntityDamageEvent.DamageCause.CUSTOM, (skyblockPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + abilityDamage) * (1 + (skyblockPlayer.getStat(SkyblockStats.MANA) / 100F) * scaling))));
                    }
                }
            }
        }
        player.sendMessage(ChatColor.GRAY + "Your " + name + " hit " + ChatColor.RED + player.getNearbyEntities(nearbyX, nearbyY, nearbyZ).size() + ChatColor.GRAY + " enemies for " + ChatColor.RED + formatter.format(player.getNearbyEntities(nearbyX, nearbyY, nearbyZ).size() * Math.round((skyblockPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + abilityDamage) * (1 + (skyblockPlayer.getStat(SkyblockStats.MANA) / 100F) * scaling))) + ChatColor.GRAY + " damage.");

    }

    public static HandlerList getHandlersList(){
        return HANDLERS;
    }

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    public static HandlerList getHandler(){
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
