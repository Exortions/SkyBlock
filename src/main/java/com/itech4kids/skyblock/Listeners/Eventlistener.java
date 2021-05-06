package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.CustomMobs.PlayerEntities.CustomAI;
import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Objects.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

public class Eventlistener implements Listener {

    public Main main;

    public Eventlistener(){
        main = Main.getMain();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        final Player player = e.getPlayer();

        try {
            Config.createPlayer(player.getName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ItemStack item1 = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
        item1.setItemMeta(itemMeta);
        player.getInventory().setItem(8, item1);

        SkyblockPlayer skyblockPlayer = new SkyblockPlayer(player);
        main.players.put(player.getName(), skyblockPlayer);

        new BukkitRunnable() {
            @Override
            public void run () {
                main.onJoin(player);
                main.updateBoard(player);
                IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "\n" +
                        "§bYou are playing on: "  + "§e§lHYPIXEL.NET" + "\n" + " " + "\"}");
                IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "\n" + "§aDiscord:" + "\n" + "§7Discord not set up yet!" + "\n " + "\n" + "§aRanks, Booster & More!" + "§l§c STORE.HYPIXEL.NET" + "\"}");
                PacketPlayOutPlayerListHeaderFooter info = new PacketPlayOutPlayerListHeaderFooter(header);

                try {
                    Field field = info.getClass().getDeclaredField("b");
                    field.setAccessible(true);
                    field.set(info, footer);
                } catch (Exception x) {
                    x.printStackTrace();
                }

                player.getWorld().setGameRuleValue("mobGriefing", "false");
                player.getWorld().setGameRuleValue("randomTickSpeed", "0");
                main.getMain().updateMaxHealth(skyblockPlayer);
                player.setHealth(player.getMaxHealth());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(info);

            }
        }.runTaskLater(main, 5);
    }

    @EventHandler
    public void onTeleport(EntityTeleportEvent e){
        if (e.getEntity().getType().equals(EntityType.ENDERMAN)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCombust(EntityCombustEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws IOException {
        SkyblockPlayer skyblockPlayer = main.getPlayer(e.getPlayer().getName());
        skyblockPlayer.saveStats();
        main.players.remove(skyblockPlayer);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) throws IOException {
        Player player = e.getEntity();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) / 2));
        player.sendMessage(ChatColor.RED + "You died and lost " + Config.getPurseCoins(player) + " coins!");
        player.playSound(player.getLocation(), Sound.ITEM_BREAK, 100, 1);
        ItemStack item1 = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
        item1.setItemMeta(itemMeta);
        player.getInventory().setItem(8, item1);
    }

    @EventHandler
    public void onNpcDamage(NPCDamageByEntityEvent e) throws IOException {
        int divider = 0;
        LivingEntity livingEntity = (LivingEntity) e.getNPC().getEntity();

        NPC npc = e.getNPC();

        e.setDamage(0);
        if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")){
            divider = 2000000;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 3000);

        }else if (e.getNPC().getName().contains(ChatColor.RED + "Frozen Steve")){
            divider = 700;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 0);
        }else if(e.getNPC().getName().contains(ChatColor.RED + "Crypt Undead")){
            divider = 45000;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.COMBAT, 0);
        }
        ((LivingEntity) e.getNPC().getEntity()).setHealth(livingEntity.getHealth());
        ((LivingEntity) e.getNPC().getEntity()).setMaxHealth(livingEntity.getMaxHealth());
    }

    @EventHandler
    public void onNPCDeath(NPCDeathEvent e){
        e.getNPC().destroy();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        double NPCdamage;
        if (e.getDamager() instanceof Player) {
            if (!e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")) {
                SkyblockPlayer skyblockPlayer = main.getPlayer(e.getDamager().getName());
                int i = new Random().nextInt(100);
                int r = new Random().nextInt(100);
                double damage = 5 + skyblockPlayer.getStat((SkyblockStats.ATTACK_DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100);
                if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                    if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                        e.setDamage(damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100);
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Math.round(e.getDamage()))));
                    } else {
                        e.setDamage(damage);
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Math.round(e.getDamage()));
                    }
                    if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                        if (!skyblockPlayer.ferocityCooldown) {
                            skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                            skyblockPlayer.ferocityCooldown = true;
                            Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    skyblockPlayer.ferocityCooldown = false;
                                }
                            }.runTaskLater(main, 5);
                        }
                    }
                }
            }else if (e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")){
                NPC npc = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                if (npc.getName().contains(ChatColor.RED + "Yeti")) {
                    e.setDamage((300 * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))));
                    skyblockPlayer.setStat(SkyblockStats.HEALTH, (skyblockPlayer.getStat(SkyblockStats.HEALTH) - 300));
                }else if (npc.getName().contains(ChatColor.RED + "Frozen Steve")){
                    e.setDamage((20 * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))));
                    skyblockPlayer.setStat(SkyblockStats.HEALTH, (skyblockPlayer.getStat(SkyblockStats.HEALTH) - 50));
                }
            }

        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent e){
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH) || e.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT) || e.getState().equals(PlayerFishEvent.State.IN_GROUND)){
            Player player = e.getPlayer();
            Vector vector = player.getLocation().getDirection().multiply(6);
            vector.setY(1);
            player.setVelocity(vector);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        Vector vector = player.getVelocity();
        double blocksPerSecond = vector.length();
        //player.sendMessage(blocksPerSecond + " b/s");
        player.setWalkSpeed(skyblockPlayer.getStat(SkyblockStats.SPEED)/1000F + 0.1F);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getPlayer().getItemInHand() != null) {
            if (e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Skyblock Menu (Right Click)")) {
                player.performCommand("sbmenu");
            }
        }
    }

    public void loadDamage(NPC npc, int divider, Entity damager, SkillType skillType, int xp) throws IOException {
        LivingEntity livingEntity = (LivingEntity) npc.getEntity();
        SkyblockPlayer skyblockPlayer = main.getPlayer(damager.getName());
        int i = new Random().nextInt(100);
        int r = new Random().nextInt(100);
        double damage = 5 + skyblockPlayer.getStat((SkyblockStats.ATTACK_DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100);
        if (!npc.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100)))));
                damager.sendMessage(damage + " dmg");
                damager.sendMessage(livingEntity.getHealth() * divider + " health");
                if (((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider) >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - ((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider));
                }
            } else {
                ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + Math.round(damage));
                damager.sendMessage(damage + "");
                damager.sendMessage(livingEntity.getHealth() * divider + " health");
                if (damage / divider >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - damage / divider);
                }
            }
            if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                if (!skyblockPlayer.ferocityCooldown) {
                    for (int f = 0; f < Integer.valueOf(skyblockPlayer.getStat(SkyblockStats.FEROCITY)/100 + 1); ++f) {
                        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                        skyblockPlayer.ferocityCooldown = true;
                        Bukkit.getPluginManager().callEvent(new NPCDamageByEntityEvent(npc, new EntityDamageByEntityEvent(damager, livingEntity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage)));
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            skyblockPlayer.ferocityCooldown = false;
                        }
                    }.runTaskLater(main, 5);
                }
            }
        }
    }
}