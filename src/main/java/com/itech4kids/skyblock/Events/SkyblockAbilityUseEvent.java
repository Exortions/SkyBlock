package com.itech4kids.skyblock.Events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class
SkyblockAbilityUseEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    private int mana;
    private String abilityName;

    public SkyblockAbilityUseEvent(Player player, String abilityName, int mana) {
        super(player);
        this.mana = mana;
        this.abilityName = abilityName;

        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setStat(SkyblockStats.MANA, skyblockPlayer.getStat(SkyblockStats.MANA) - mana);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a(
                "{\"text\":\"" + "§c" + skyblockPlayer.getStat(SkyblockStats.HEALTH) + "/" + skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) + "❤   §b-" + mana + " Mana (§6" + abilityName + "§b) §b" + skyblockPlayer.getStat(SkyblockStats.MANA) + "/" +
                        skyblockPlayer.getStat(SkyblockStats.MAX_MANA) + "✎ Mana" + "\"}");
        ActionBarAPI.sendActionBar(skyblockPlayer.getBukkitPlayer(), icbc.getText());
    }

    public String getAbilityName(){return abilityName;}

    public int getManaCost(){return mana;}

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
