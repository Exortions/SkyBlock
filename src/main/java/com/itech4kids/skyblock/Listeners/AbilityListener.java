package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Events.SkyblockAbilityUseEvent;
import com.itech4kids.skyblock.Events.SkyblockMagicDamageEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import org.bukkit.*;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerFishEvent;
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
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        Action action = e.getAction();
        String no_mana = ChatColor.RED + "You don't have enough mana!";
        String cooldown = ChatColor.RED + "This ability is still on cooldown!";
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
            if (player.getItemInHand() != null) {
                if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).contains("Ember Rod")) {
                    if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 150) {
                        if (skyblockPlayer.getCooldown("ember_rod") == 0 || player.hasPermission("skyblock.cooldown.bypass")) {
                            skyblockPlayer.setCooldown("ember_rod", 1);
                            Bukkit.getPluginManager().callEvent(new SkyblockAbilityUseEvent(player, "Fire Blast", 150));
                            Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                                    player.getLocation().getYaw(),
                                    player.getLocation().getPitch());
                            Fireball fireball = player.getWorld().spawn(loc, Fireball.class);
                            fireball.setShooter(player);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    skyblockPlayer.setCooldown("ember_rod", 0);
                                }
                            }.runTaskLater(Main.getMain(), 20 * 30);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Location loc2 = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                                            player.getLocation().getYaw(),
                                            player.getLocation().getPitch());
                                    Fireball fireball2 = player.getWorld().spawn(loc2, Fireball.class);
                                    fireball2.setShooter(player);
                                }
                            }.runTaskLater(Main.getMain(), 20);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    Location loc3 = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(),
                                            player.getLocation().getYaw(),
                                            player.getLocation().getPitch());
                                    Fireball fireball3 = player.getWorld().spawn(loc3, Fireball.class);
                                    fireball3.setShooter(player);
                                }
                            }.runTaskLater(Main.getMain(), 40);
                            player.playSound(player.getLocation(), Sound.BLAZE_BREATH, 10, 10);
                        } else {
                            player.sendMessage(cooldown);
                        }
                    } else {
                        player.sendMessage(no_mana);
                    }
                } else if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).contains("Aspect of the End")) {
                    if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 50) {
                        Location loc = player.getLocation();
                        Vector dir = loc.getDirection();
                        dir.normalize();
                        dir.multiply(8);
                        loc.add(dir);
                        Bukkit.getPluginManager().callEvent(new SkyblockAbilityUseEvent(player, "Instant Transmission", 50));
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 1);
                        if (!skyblockPlayer.aoteSpeed) {
                            skyblockPlayer.aoteSpeed = true;
                            skyblockPlayer.setStat(SkyblockStats.SPEED, skyblockPlayer.getStat(SkyblockStats.SPEED) + 50);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    skyblockPlayer.setStat(SkyblockStats.SPEED, skyblockPlayer.getStat(SkyblockStats.SPEED) - 50);
                                    skyblockPlayer.aoteSpeed = false;
                                }
                            }.runTaskLater(Main.getMain(), 60);
                        }
                        if (loc.getBlock().getType().equals(Material.AIR)) {
                            player.teleport(loc);
                        } else {
                            player.sendMessage(ChatColor.RED + "There are blocks in the way!");
                        }
                    } else {
                        player.sendMessage(no_mana);
                    }
                } else if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).contains("Golem Sword")) {
                    if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 70) {
                        if (skyblockPlayer.getCooldown("iron_punch") == 0 || player.hasPermission("skyblock.cooldown.bypass")) {
                            Location loc = player.getLocation();
                            Vector dir = loc.getDirection();
                            dir.normalize();
                            dir.multiply(1);
                            loc.add(dir);

                            Location l = new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());

                            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 100, 2);

                            Bukkit.getPluginManager().callEvent(new SkyblockAbilityUseEvent(player, "Iron Punch", 70));

                            skyblockPlayer.setCooldown("iron_punch", 1);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    skyblockPlayer.setCooldown("iron_punch", 0);
                                }
                            }.runTaskLater(Main.getMain(), 60);

                            for (Player player1 : Bukkit.getOnlinePlayers()) {
                                player1.playEffect(loc, Effect.STEP_SOUND, l.getBlock().getTypeId());
                                player1.playEffect(player.getLocation(), Effect.STEP_SOUND, l.getBlock().getTypeId());

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        Location l = player.getLocation();
                                        player1.playEffect(l, Effect.EXPLOSION_HUGE, 10);
                                        player1.playEffect(l, Effect.EXPLOSION_HUGE, 10);
                                        player1.playEffect(l, Effect.EXPLOSION_HUGE, 10);
                                        player1.playSound(player.getLocation(), Sound.EXPLODE, 10, 1);
                                    }
                                }.runTaskLater(Main.getMain(), 10);
                            }


                            Bukkit.getPluginManager().callEvent(new SkyblockMagicDamageEvent(player, null, 1, 3, "Iron Punch", 250));


                        } else {
                            player.sendMessage(cooldown);
                        }
                    } else {
                        player.sendMessage(no_mana);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent e){
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT) || e.getState().equals(PlayerFishEvent.State.IN_GROUND)){
            if (e.getPlayer().getItemInHand() != null) {
                if (ChatColor.stripColor(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().toLowerCase()).contains("grappling hook")) {
                    Player player = e.getPlayer();
                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                    if (skyblockPlayer.getCooldown("grappling_hook") == 0) {
                        skyblockPlayer.setCooldown("grappling_hook", 1);
                        Vector vector = player.getLocation().getDirection().multiply(6);
                        vector.setY(1);
                        player.setVelocity(vector);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                skyblockPlayer.setCooldown("grappling_hook", 0);
                            }
                        }.runTaskLater(Main.getMain(), 40);
                    }else{
                        player.sendMessage(ChatColor.RED + "Whow! Slow down there!");
                    }
                }
            }
        }
    }


}
