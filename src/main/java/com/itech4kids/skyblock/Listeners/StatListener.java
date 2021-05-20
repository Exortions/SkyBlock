package com.itech4kids.skyblock.Listeners;

import com.inkzzz.spigot.armorevent.PlayerArmorEquipEvent;
import com.inkzzz.spigot.armorevent.PlayerArmorUnequipEvent;
import com.itech4kids.skyblock.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class StatListener implements Listener {

    @EventHandler
    public void onArmorEquip(PlayerArmorEquipEvent e){
        Main.getMain().updateStats(Main.getMain().getPlayer(e.getPlayer().getName()), e.getItemStack(), null);
        Main.getMain().updateActionbar(Main.getMain().getPlayer(e.getPlayer().getName()));
    }

    @EventHandler
    public void onArmorUnEquip(PlayerArmorUnequipEvent e){
        if (!Arrays.asList(e.getPlayer().getInventory().getArmorContents()).contains(e.getItemStack())) {
            Main.getMain().updateStats(Main.getMain().getPlayer(e.getPlayer().getName()), null, e.getItemStack());
            Main.getMain().updateActionbar(Main.getMain().getPlayer(e.getPlayer().getName()));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        new BukkitRunnable() {
            @Override
            public void run() {
                for (ItemStack itemStack : e.getPlayer().getInventory().getArmorContents()){
                    Main.getMain().updateStats(Main.getMain().getPlayer(e.getPlayer().getName()), null, itemStack);
                    Main.getMain().updateActionbar(Main.getMain().getPlayer(e.getPlayer().getName()));
                }
                Main.getMain().updateStats(Main.getMain().getPlayer(e.getPlayer().getName()), null, e.getPlayer().getItemInHand());
            }
        }.runTaskLater(Main.getMain(), 2);
    }

}
