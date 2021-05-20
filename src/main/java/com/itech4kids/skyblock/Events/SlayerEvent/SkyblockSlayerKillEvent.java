package com.itech4kids.skyblock.Events.SlayerEvent;

import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerBoss;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

public class SkyblockSlayerKillEvent extends PlayerEvent implements Cancellable {

    private boolean isCancelled;
    private static final HandlerList HANDLERS = new HandlerList();

    private SlayerBoss boss;

    public SkyblockSlayerKillEvent(Player who, SlayerBoss slayerBoss) {
        super(who);

        boss = slayerBoss;
    }

    public SlayerBoss getEntity(){
        return boss;
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
