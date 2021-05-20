package com.itech4kids.skyblock.CustomMobs.Slayer.Bosses;

import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerAI;
import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerBoss;
import com.itech4kids.skyblock.Main;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

public class RevenantBoss extends SlayerBoss {

    private Player spawner;
    private int bossLevel;

    public RevenantBoss(Player spawner, int bossLevel) {
        super(EntityType.ZOMBIE, bossLevel);
        this.spawner = spawner;
        this.bossLevel = bossLevel;
    }

    public void summon(Location spawnLocation){
        spawnSlayerBoss(spawnLocation, 0.2);
        registerEntity();
        SlayerAI.runRevenantAI(this, bossLevel, spawner);
        Main.getMain().slayerManger.registerBoss(this);

        Zombie zombie = (Zombie) getVanillaEntity();
        zombie.setBaby(false);
        zombie.setVillager(false);
    }

}
