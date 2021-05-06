package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Objects.SkyblockStats;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AbilityListener implements Listener {

    @EventHandler
    public void onExplode(EntityExplodeEvent e){
        if (e.getEntity() instanceof Fireball){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        Action action = e.getAction();
        String no_mana = ChatColor.RED + "You don't have enough mana!";
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)){
            if (player.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Ember Rod")){
                if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 150){
                    skyblockPlayer.setStat(SkyblockStats.MANA, skyblockPlayer.getStat(SkyblockStats.MANA) - 150);
                    Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                            player.getLocation().getYaw(),
                            player.getLocation().getPitch());
                    Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
                    fireball.setShooter(player);
                    new BukkitRunnable() {
                        @Override
                        public void run () {
                            Location loc2 = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                                    player.getLocation().getYaw(),
                                    player.getLocation().getPitch());
                            Fireball fireball2 = player.getWorld().spawn(loc2, Fireball.class);
                            fireball2.setShooter(player);
                        }
                    }.runTaskLater(Main.getMain(), 20);
                    new BukkitRunnable() {
                        @Override
                        public void run () {
                            Location loc3 = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                                    player.getLocation().getYaw(),
                                    player.getLocation().getPitch());
                            Fireball fireball3 = player.getWorld().spawn(loc3, Fireball.class);
                            fireball3.setShooter(player);                        }
                    }.runTaskLater(Main.getMain(), 40);
                    player.sendMessage(ChatColor.GREEN + "Used" + ChatColor.GOLD + " Fire Blast" + ChatColor.GREEN + "! " + ChatColor.AQUA + "-150 Mana");
                    player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 10, 10);
                }else{
                    player.sendMessage(no_mana);
                }
            } else if (player.getItemInHand().getItemMeta().getDisplayName().contains(ChatColor.BLUE + "Aspect of the End")) {
                if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 50) {
                    Location loc = player.getLocation();
                    Vector dir = loc.getDirection();
                    dir.normalize();
                    dir.multiply(8);
                    loc.add(dir);
                    player.sendMessage(ChatColor.GREEN + "Used" + ChatColor.GOLD + " Instant Transmission" + ChatColor.GREEN + "! " + ChatColor.AQUA + "-50 Mana");
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
                    skyblockPlayer.setStat(SkyblockStats.MANA, skyblockPlayer.getStat(SkyblockStats.MANA) - 50);
                    skyblockPlayer.setStat(SkyblockStats.SPEED, skyblockPlayer.getStat(SkyblockStats.SPEED) + 50);
                    if (loc.getBlock().getType().equals(Material.AIR)){
                        player.teleport(loc);
                    }else{
                        player.sendMessage(ChatColor.RED + "There are blocks in the way!");
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            skyblockPlayer.setStat(SkyblockStats.SPEED, skyblockPlayer.getStat(SkyblockStats.SPEED) - 50);
                        }
                    }.runTaskLater(Main.getMain(), 60);
                }

            }
        }
    }
}