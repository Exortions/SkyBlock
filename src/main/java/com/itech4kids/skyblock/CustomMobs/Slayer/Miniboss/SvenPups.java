package com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.SEntityAI;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.SvenBoss;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Wolf;

import java.util.HashMap;

public class SvenPups extends SEntity {

    public SvenPups(Location spawnLocation, SvenBoss owner) {
        super(EntityType.WOLF);
        loadStats(Math.round(owner.getMaximumHealth()/10), Math.round(owner.getAttackDamage()/10), false, new HashMap<>(), "Sven Pups", Math.round(owner.getLevel()/10));
        spawn(spawnLocation);
        registerEntity();
        SEntityAI.runWolfAI(this);
        Wolf wolf = (Wolf) getVanillaEntity();
        wolf.setBaby();
    }
}
