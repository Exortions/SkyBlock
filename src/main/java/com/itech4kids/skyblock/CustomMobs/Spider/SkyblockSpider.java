package com.itech4kids.skyblock.CustomMobs.Spider;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.SEntityAI;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;

import java.util.HashMap;

public class SkyblockSpider extends SEntity {

    public SkyblockSpider(SkyblockSpiderType spiderType, Location spawnLocation) {
        super(EntityType.SPIDER);

        switch (spiderType){
            case SPLITTER_SPIDER_MEDIUM:
                loadStats(180, 30, false, new HashMap<>(), "Splitter Spider", 2);
                SEntityAI.runSpiderAI(this);
                break;
            case SPLITTER_SPIDER_INTERMEDIATE:
                loadStats(220, 40, false, new HashMap<>(), "Splitter Spider", 4);
                SEntityAI.runSpiderAI(this);
                break;
            case DASHER_SPIDER_EASY:
                loadStats(170, 55, false, new HashMap<>(), "Dasher Spider", 4);
                SEntityAI.runDasherSpiderAI(this);
                break;
            case DASHER_SPIDER_INTERMEDIATE:
                loadStats(210, 70, false, new HashMap<>(), "Dasher Spider", 6);
                SEntityAI.runDasherSpiderAI(this);
                break;
            case WEAVER_SPIDER:
                loadStats(160, 35, false, new HashMap<>(), "Weaver Spider", 3);
                SEntityAI.runSpiderAI(this);
                break;
            case SPIDER_JOCKEY:
                loadStats(220, 55, false, new HashMap<>(), "Spider Jockey", 3);
                spawn(spawnLocation).setPassenger(spawnLocation.getWorld().spawn(spawnLocation, Skeleton.class));
                SEntityAI.runSpiderAI(this);
                break;
            case VORACIOUS_SPIDER:
                loadStats(1000, 100, false, new HashMap<>(), "Voracious Spider", 10);
                SEntityAI.runSpiderAI(this);
                break;
            case TARANTULA_BROOD_MOTHER:
                loadStats(6000, 160, false, new HashMap<>(), "Tarantula Brood Mother", 12);
                SEntityAI.runSpiderAI(this);
                break;
        }
        if (!spiderType.equals(SkyblockSpiderType.SPIDER_JOCKEY)) {
            spawn(spawnLocation);
        }

        registerEntity();
    }
}
