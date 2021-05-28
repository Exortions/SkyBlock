package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.CustomMobs.PlayerEntities.CustomAI;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombie;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombieType;
import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.RegenerativeBlock;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Random;

public class SkillGainListeners implements Listener {

    public Main main;

    public SkillGainListeners(Main main){
        this.main = main;
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) throws IOException {
        int i = new Random().nextInt(50);
        int r = i + 7;
        int c = new Random().nextInt(100);
        int r2 = new Random().nextInt(2);
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        double x = e.getHook().getLocation().getX();
        double y = e.getHook().getLocation().getY();
        double z = e.getHook().getLocation().getZ();
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            if (c <= skyblockPlayer.getStat(SkyblockStats.SEA_CREATURE_CHANCE)) {
                if (r2 == 0) {
                    SkyblockZombie skyblockZombie = new SkyblockZombie(SkyblockZombieType.SEA_WALKER, e.getHook().getLocation());

                } else if (r2 == 1) {
                    NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format(1 * 2000000) + ChatColor.RED + "❤");
                    npc.spawn(new Location(world.getWorld(), x, y, z));
                    npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                    ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                    healthDisplay.setCustomNameVisible(true);
                    healthDisplay.setGravity(false);
                    healthDisplay.setVisible(false);
                    healthDisplay.setSmall(true);
                    healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl" + 175 + ChatColor.DARK_GRAY + "] " + npc.getName());
                    npc.setProtected(false);
                    CustomAI.yetiAI(npc, healthDisplay);
                }
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) throws IOException {
        if (e.getPlayer().getWorld().equals(IslandManager.getIsland(e.getPlayer()))) {

        } else if (e.getPlayer().getWorld().getName().equals("world")) {
            e.setCancelled(true);
            Block block = e.getBlock();
            Block oldBlock = e.getBlock();
            SkyblockPlayer skyblockPlayer = main.getPlayer(e.getPlayer().getName());

            if (!e.getBlock().getType().equals(Material.COBBLESTONE)) {
                skyblockPlayer.brokenBlock = e.getBlock();
            }

            switch (block.getType()) {
                case STONE:
                    if (block.getData() == 0) {
                        Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 1));
                        for (ItemStack itemStack : block.getDrops()) {
                            block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                        }
                        block.setType(Material.COBBLESTONE);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                block.setType(Material.STONE);
                            }
                        }.runTaskLater(main, 20*7);
                    }
                    break;
                case COBBLESTONE:
                    if (skyblockPlayer.brokenBlock != null) {
                        if (skyblockPlayer.brokenBlock.getType().equals(Material.COBBLESTONE)) {
                            Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 1));
                            for (ItemStack itemStack : block.getDrops()) {
                                block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                            }
                            block.setType(Material.BEDROCK);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    block.setType(Material.STONE);
                                }
                            }.runTaskLater(main, 20 * 7);
                        }else{
                            Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 1));
                            for (ItemStack itemStack : block.getDrops()) {
                                block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                            }
                            block.setType(Material.BEDROCK);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    block.setType(Material.COBBLESTONE);
                                }
                            }.runTaskLater(main, 20 * 7);
                        }
                    }else{
                        Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 1));
                        for (ItemStack itemStack : block.getDrops()) {
                            block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                        }
                        block.setType(Material.BEDROCK);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                block.setType(Material.COBBLESTONE);
                            }
                        }.runTaskLater(main, 20 * 7);
                    }
                    break;
                case LOG:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FORAGING, 6));
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 17, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 20);
                    break;
                case LOG_2:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FORAGING, 6));
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 162, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 20);
                    break;
                case LEAVES:
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 18, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 20);
                    break;
                case LEAVES_2:
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 161, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 20);
                    break;
                case YELLOW_FLOWER:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FORAGING, 0.5));
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 37, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 8);
                    break;
                case RED_ROSE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FORAGING, 0.5));
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 38, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 8);
                    break;
                case DOUBLE_PLANT:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FORAGING, 0.5));
                    e.setCancelled(false);
                    RegenerativeBlock.regenerateBlock(block.getTypeId(), block.getData(), 175, skyblockPlayer.brokenBlock.getData(), e.getBlock(), 8);
                    break;
                case COAL_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 5));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.COAL_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case IRON_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 5));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.IRON_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case GOLD_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 6));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.GOLD_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case LAPIS_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 7));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.LAPIS_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case REDSTONE_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 7));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.REDSTONE_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case EMERALD_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 9));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.EMERALD_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case DIAMOND_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 10));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.DIAMOND_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case DIAMOND_BLOCK:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 15));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.DIAMOND_BLOCK);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case NETHERRACK:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 0.5));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.NETHERRACK);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case QUARTZ_ORE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 5));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.QUARTZ_ORE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case GLOWSTONE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 7));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.GLOWSTONE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case OBSIDIAN:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 24));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.OBSIDIAN);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
                case ENDER_STONE:
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 3));
                    for (ItemStack itemStack : block.getDrops()) {
                        block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                    }
                    block.setType(Material.BEDROCK);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            block.setType(Material.ENDER_STONE);
                        }
                    }.runTaskLater(main, 20*7);
                    break;
            }


        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCoinPickUp(PlayerPickupItemEvent e) throws IOException {
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        ItemMeta meta = e.getItem().getItemStack().getItemMeta();
        int amount = Integer.parseInt(meta.getDisplayName().split("_")[2]);

        if (meta.getDisplayName().contains(ChatColor.GOLD + "coin_iron_")){
            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) + Integer.parseInt(meta.getDisplayName().split("_")[2])));
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 2);
            e.setCancelled(true);
            e.getItem().remove();
            skyblockPlayer.lastPickedUpCoins = amount;
            new BukkitRunnable() {
                @Override
                public void run() {
                    skyblockPlayer.lastPickedUpCoins = 0;
                }
            }.runTaskLater(main, 20*3);
        }else if (meta.getDisplayName().contains(ChatColor.GOLD + "coin_gold_")){
            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) + Integer.parseInt(meta.getDisplayName().split("_")[2])));
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 2);
            e.setCancelled(true);
            e.getItem().remove();
            skyblockPlayer.lastPickedUpCoins = amount;
            new BukkitRunnable() {
                @Override
                public void run() {
                    skyblockPlayer.lastPickedUpCoins = 0;
                }
            }.runTaskLater(main, 20*3);
        }else if (meta.getDisplayName().contains(ChatColor.GOLD + "coin_diamond_")){
            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) + Integer.parseInt(meta.getDisplayName().split("_")[2])));
            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 2);
            e.setCancelled(true);
            e.getItem().remove();
            skyblockPlayer.lastPickedUpCoins = amount;
            new BukkitRunnable() {
                @Override
                public void run() {
                    skyblockPlayer.lastPickedUpCoins = 0;
                }
            }.runTaskLater(main, 20*3);
        }
    }
}
