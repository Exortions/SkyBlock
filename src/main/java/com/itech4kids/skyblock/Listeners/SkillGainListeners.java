package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
            Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(main.getPlayer(e.getPlayer().getName()), SkillType.FISHING, r));
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) throws IOException {
        e.setCancelled(true);
        Block block = e.getBlock();
        Block oldBlock;
        byte id = 0;
        int type = 0;
        SkyblockPlayer skyblockPlayer = main.getPlayer(e.getPlayer().getName());
        switch (block.getType()){
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
                    }.runTaskLater(main, 160);
                }
                break;
            case COBBLESTONE:
                Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.MINING, 1));
                for (ItemStack itemStack : block.getDrops()){
                    block.getWorld().dropItemNaturally(block.getLocation(), itemStack);
                }
                block.setType(Material.BEDROCK);
                new BukkitRunnable() {
                    @Override
                    public void run () {
                        block.setType(Material.STONE);
                    }
                }.runTaskLater(main, 160);
                break;
        }
    }
}
