package com.itech4kids.skyblock.CustomMobs.Enderman;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class SkyblockEnderman extends SEntity {

    public SkyblockEnderman(SkyblockEndermanType endermanType, Location spawnLocation) {
        super(EntityType.ENDERMAN);

        switch (endermanType){
            case FOUR_K:
                loadStats(4500, 500, false, new HashMap<>(), "Enderman", 42);
                break;
            case SIX_K:
                loadStats(6000, 600, false, new HashMap<>(), "Enderman", 45);
                break;
            case NINE_K:
                loadStats(9000, 700, false, new HashMap<>(), "Enderman", 50);
                break;
            case ZEALOT:
                loadStats(13000, 1250, false, new HashMap<>(), "Zealot", 55);
                break;
            case SPECIAL_ZEALOT:
                HashMap<String, ItemStack> equipment = new HashMap<>();
                equipment.put("iteminhand", new ItemStack(Material.ENDER_PORTAL_FRAME));
                loadStats(2000, 1250, false, equipment, "Zealot", 55);
                break;
        }
        spawn(spawnLocation);
        registerEntity();
    }
}
