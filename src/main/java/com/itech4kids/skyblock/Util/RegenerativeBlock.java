package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenerativeBlock {

    public static void regenerateBlock(int id, byte data, int newId, byte newData, Block block, int seconds){
        block.setTypeIdAndData(id, data, true);
        new BukkitRunnable() {
            @Override
            public void run() {
                block.setTypeIdAndData(newId, newData, true);
            }
        }.runTaskLater(Main.getMain(), 20*seconds);
    }
}