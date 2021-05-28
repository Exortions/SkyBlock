package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.Events.SkyblockAbilityUseEvent;
import com.itech4kids.skyblock.Events.SkyblockMagicDamageEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.itech4kids.skyblock.Util.Util;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.Random;

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

                        for (int i = 0; i < 64; i++){
                            Location loc = player.getLocation();
                            Vector dir = loc.getDirection();
                            dir.normalize();
                            dir.multiply(0.125);
                            loc.add(dir);
                            loc.setY(loc.getY() + 0.01);
                            if (loc.getBlock().getType().equals(Material.AIR)) {
                                player.teleport(loc);
                            } else {
                                Vector vec = loc.getDirection();
                                vec.normalize();
                                vec.multiply(-0.75);
                                loc.add(vec);
                                player.teleport(loc);
                                player.sendMessage(ChatColor.RED + "There are blocks in the way!");
                                break;
                            }
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


                            Bukkit.getPluginManager().callEvent(new SkyblockMagicDamageEvent(player, null, 1, 3, 3, 3, "Iron Punch", 250));


                        } else {
                            player.sendMessage(cooldown);
                        }
                    } else {
                        player.sendMessage(no_mana);
                    }
                }else if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).contains("Midas Staff")){
                    if (skyblockPlayer.getStat(SkyblockStats.MANA) >= 500) {
                        if (skyblockPlayer.getCooldown("midas_staff") == 0 || player.hasPermission("skyblock.cooldown.bypass")) {
                            Location loc = player.getLocation();
                            Vector dir = loc.getDirection();
                            dir.normalize();
                            dir.multiply(2);
                            loc.add(dir);
                            loc.setY(loc.getY() + 1);
                            loc.getWorld().spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte ) 0);

                            dir.normalize();
                            dir.multiply(1);
                            loc.add(dir);
                            loc.getWorld().spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte ) 0);

                            dir.normalize();
                            dir.multiply(1);
                            loc.add(dir);
                            loc.getWorld().spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte ) 0);

                            dir.normalize();
                            dir.multiply(1);
                            loc.add(dir);
                            loc.getWorld().spawnFallingBlock(loc, Material.GOLD_BLOCK, (byte ) 0);

                            Bukkit.broadcastMessage(rpGetPlayerDirection(player));

                            switch (rpGetPlayerDirection(player)){
                                case "west":
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ()), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ()), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 2), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 2), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 3), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 3), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 4), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 4), Material.GOLD_BLOCK, (byte) 0);
                                    break;
                                case "north":
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 3, loc.getY(), loc.getZ() + 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 3, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 2, loc.getY(), loc.getZ() + 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 2, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() + 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() + 1, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() + 1), Material.GOLD_BLOCK, (byte) 0);
                                    loc.getWorld().spawnFallingBlock(new Location(loc.getWorld(), loc.getX() - 1, loc.getY(), loc.getZ() - 1), Material.GOLD_BLOCK, (byte) 0);
                                    break;
                                case "east":
                                    break;
                                case "south":
                                    break;
                            }

                            player.playSound(player.getLocation(), Sound.DIG_GRASS, 10, 0);
                            Bukkit.getPluginManager().callEvent(new SkyblockAbilityUseEvent(player, "Molten Wave", 500));

                        } else {
                            player.sendMessage(cooldown);
                        }
                    } else {
                        player.sendMessage(no_mana);
                    }
                }else if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).contains("Axe of the Shredded")) {
                    Location loc = player.getLocation();
                    Vector dir = loc.getDirection();
                    dir.normalize();
                    dir.multiply(2);
                    ArmorStand armorStand = Util.throwItem(player, new ItemStack(Material.DIAMOND_AXE), player.getLocation(), dir);
                    final int[] lifespan = {0};
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (armorStand.isDead()){
                                cancel();
                            }else{
                                lifespan[0] = lifespan[0] + 1;
                                if (armorStand.getNearbyEntities(0.5, 0.5, 0.5).size() > 0){
                                    for (Entity e : armorStand.getNearbyEntities(0.5, 0.5, 0.5)){
                                        if (e instanceof Player){
                                        }else {
                                            int i = new Random().nextInt(100);
                                            int r = new Random().nextInt(100);
                                            DecimalFormat formatter = new DecimalFormat("#,###");
                                            formatter.setGroupingUsed(true);
                                            double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5F) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100F);

                                            if (e.hasMetadata("identifier")) {
                                                SEntity sEntity = Main.getMain().handler.getEntity(e);
                                                if (!e.getType().equals(EntityType.ARMOR_STAND)) {
                                                    sEntity.addDespawnDelay(15 * 20);
                                                    sEntity.setLastDamager(skyblockPlayer.getBukkitPlayer());
                                                    if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                                                        if (Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) * 1/10) >= 1000000) {
                                                            ItemUtil.setDamageIndicator(e.getLocation(), ItemUtil.addCritTexture(String.valueOf(Main.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) * 1/10)))));
                                                        } else {
                                                            ItemUtil.setDamageIndicator(e.getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) * 1/10)))));
                                                        }
                                                        sEntity.subtractHealth((int) Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)));
                                                    } else {
                                                        if (Math.round(damage) >= 1000000) {
                                                            ItemUtil.setDamageIndicator(e.getLocation(), org.bukkit.ChatColor.GRAY + "" + Main.format(Math.round(damage * 1/10)));
                                                        } else {
                                                            ItemUtil.setDamageIndicator(e.getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage * 1/10)));
                                                        }
                                                        sEntity.subtractHealth((int) Math.round(damage));
                                                    }
                                                    if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                                                        if (!skyblockPlayer.ferocityCooldown) {
                                                            skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                                                            skyblockPlayer.ferocityCooldown = true;
                                                            Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                                                            new BukkitRunnable() {
                                                                @Override
                                                                public void run() {
                                                                    skyblockPlayer.ferocityCooldown = false;
                                                                }
                                                            }.runTaskLater(Main.getMain(), 5);
                                                        }
                                                    }
                                                }
                                            }
                                            armorStand.remove();
                                            cancel();
                                        }
                                    }
                                }

                                if (lifespan[0] > 4 * 20){
                                    armorStand.remove();
                                    cancel();
                                }

                            }
                        }
                    }.runTaskTimer(Main.getMain(), 5L, 5L);
                }
            }
        }
    }

    public String rpGetPlayerDirection(Player playerSelf){
        String dir = "";
        float y = playerSelf.getLocation().getYaw();
        if( y < 0 ){y += 360;}
        y %= 360;
        int i = (int)((y+8) / 22.5);
        if(i == 0){dir = "west";}
        else if(i == 1){dir = "west northwest";}
        else if(i == 2){dir = "northwest";}
        else if(i == 3){dir = "north northwest";}
        else if(i == 4){dir = "north";}
        else if(i == 5){dir = "north northeast";}
        else if(i == 6){dir = "northeast";}
        else if(i == 7){dir = "east northeast";}
        else if(i == 8){dir = "east";}
        else if(i == 9){dir = "east southeast";}
        else if(i == 10){dir = "southeast";}
        else if(i == 11){dir = "south southeast";}
        else if(i == 12){dir = "south";}
        else if(i == 13){dir = "south southwest";}
        else if(i == 14){dir = "southwest";}
        else if(i == 15){dir = "west southwest";}
        else {dir = "west";}
        return dir;
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
