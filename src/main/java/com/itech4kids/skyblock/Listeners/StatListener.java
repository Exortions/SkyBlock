package com.itech4kids.skyblock.Listeners;

import com.inkzzz.spigot.armorevent.PlayerArmorEquipEvent;
import com.inkzzz.spigot.armorevent.PlayerArmorUnequipEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPet;
import com.itech4kids.skyblock.Util.Config;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.ItemArmor;
import net.minecraft.server.v1_8_R3.ItemSkull;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
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
