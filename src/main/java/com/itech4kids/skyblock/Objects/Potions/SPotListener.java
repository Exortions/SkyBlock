package com.itech4kids.skyblock.Objects.Potions;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SPotListener implements Listener {

    @EventHandler
    public void onPotion(PlayerItemConsumeEvent e){
        if (e.getItem().getType().equals(Material.POTION)){

        }
    }

}
