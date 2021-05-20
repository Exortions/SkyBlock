package com.itech4kids.skyblock.Events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.SkillsManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.IOException;

public class SkyblockEntitySkillGainEvent extends Event implements Cancellable {

    private final SkyblockPlayer skyblockPlayer;
    private SkillType skillType;
    private double expAmount;
    private boolean isCancelled;
    private static final HandlerList HANDLERS = new HandlerList();
    private Entity entity;

    public SkyblockEntitySkillGainEvent(SkyblockPlayer skyblockPlayer, SkillType skillType, double amount, Entity entity) throws IOException {
        this.skyblockPlayer = skyblockPlayer;
        this.skillType = skillType;
        this.expAmount = amount;
        this.entity = entity;
        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.ORB_PICKUP, 10, 2);
        Config.setStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase(), (int) (Config.getStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + expAmount));
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(
                "{\"text\":\"" + "§c" + skyblockPlayer.getStat(SkyblockStats.HEALTH) + "/" + skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) + "❤   §3" +
                        "+" + amount + " " + StringUtils.capitalize(skillType.name().toLowerCase()) + " (" + Config.getStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + "/" + SkillsManager.getNextLvl(Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase())) + ")    §b" + skyblockPlayer.getStat(SkyblockStats.MANA) + "/" +
                        skyblockPlayer.getStat(SkyblockStats.MAX_MANA) + "✎ Mana" + "\"}");
        ActionBarAPI.sendActionBar(skyblockPlayer.getBukkitPlayer(), icbc.getText());

        if (Config.getStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) >= SkillsManager.getNextLvl(Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()))){
            Bukkit.getPluginManager().callEvent(new SkyblockSkillLevelUpEvent(skyblockPlayer, skillType));
        }
    }

    public Entity getSEntity(){
        return entity;
    }

    public double getExpAmount(){return expAmount;}

    public SkyblockPlayer getPlayer(){return skyblockPlayer;}

    public SkillType getSkillType(){return skillType;}

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
