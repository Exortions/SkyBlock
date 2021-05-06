package com.itech4kids.skyblock.Events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Objects.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import java.io.IOException;

public class SkyblockSkillExpGainEvent extends Event implements Cancellable {

    private final SkyblockPlayer skyblockPlayer;
    private SkillType skillType;
    private boolean isCancelled;
    private double expAmount;
    private static final HandlerList HANDLERS = new HandlerList();

    public SkyblockSkillExpGainEvent(SkyblockPlayer skyblockPlayer, SkillType skillType, double amount) throws IOException {
        this.skyblockPlayer = skyblockPlayer;
        this.skillType = skillType;
        this.expAmount = amount;
        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.ORB_PICKUP, 10, 1);
        Config.setStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase(), (int) (Config.getStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + expAmount));
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(
                "{\"text\":\"" + "§c" + skyblockPlayer.getStat(SkyblockStats.HEALTH) + "/" + skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) + "❤   §3" +
                        "+" + amount + " " + StringUtils.capitalize(skillType.name().toLowerCase()) + " (" + Config.getStatExp(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + "/111,672,425)    §b" + skyblockPlayer.getStat(SkyblockStats.MANA) + "/" +
                        skyblockPlayer.getStat(SkyblockStats.MAX_MANA) + "✎ Mana" + "\"}");
        ActionBarAPI.sendActionBar(skyblockPlayer.getBukkitPlayer(), icbc.getText());
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
