package com.itech4kids.skyblock.Objects.Pets.PetEvent;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.SkyblockUsableItem;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SkyblockPetEquipEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;

    public SkyblockPetEquipEvent(Player player, ArmorStand pet) {
        super(player);
        ItemStack petItem = pet.getHelmet();
        SkyblockUsableItem sui = new SkyblockUsableItem(petItem);
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        for (Map.Entry<SkyblockStats, Integer> entry : sui.getProperties().entrySet()){
            skyblockPlayer.setStat(entry.getKey(), skyblockPlayer.getStat(entry.getKey()) + entry.getValue());
        }
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
