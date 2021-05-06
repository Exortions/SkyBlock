package com.itech4kids.skyblock.Events;

import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import org.bukkit.Sound;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.IOException;

public class SkyblockSkillLevelUpEvent extends Event implements Cancellable {

    private final SkyblockPlayer skyblockPlayer;
    private SkillType skillType;
    private boolean isCancelled;
    private int oldlvl;
    private int newlvl;
    private static final HandlerList HANDLERS = new HandlerList();

    public SkyblockSkillLevelUpEvent(SkyblockPlayer skyblockPlayer, SkillType skillType) throws IOException {
        this.skyblockPlayer = skyblockPlayer;
        this.skillType = skillType;
        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.LEVEL_UP, 10, 1);
        oldlvl = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase());
        newlvl = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + 1;
        Config.setStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase(), newlvl);
    }

    public int getNewlvl(){return newlvl;}

    public int getOldlvl(){return oldlvl;}

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
