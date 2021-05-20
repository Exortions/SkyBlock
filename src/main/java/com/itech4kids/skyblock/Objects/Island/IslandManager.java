package com.itech4kids.skyblock.Objects.Island;

import com.itech4kids.skyblock.CustomMobs.PlayerEntities.JERRY;
import com.itech4kids.skyblock.Main;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.extent.clipboard.io.SchematicWriter;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class IslandManager {

    public static World getIsland(Player player){
        return Bukkit.getWorld("island_" + player.getUniqueId());
    }

    public static boolean deleteWorld(Player player){
        Bukkit.getWorlds().remove(Bukkit.getWorld("island_" + player.getUniqueId()));
        return Bukkit.getWorld("island_" + player.getUniqueId()).getWorldFolder().delete();
    }

    public static boolean createIsland(Player player){
        if (!Bukkit.getWorlds().contains(Bukkit.getWorld("island_" + player.getUniqueId()))){
            WorldCreator creator = new WorldCreator("island_" + player.getUniqueId());
            creator.type(WorldType.FLAT);
            creator.generator(new ChunkGenerator() {
                @Override
                public byte[] generate(World world, Random random, int x, int z) {
                    return new byte[32768];
                }
            });
            World world = creator.createWorld();
            player.teleport(new Location(world, 0, 100, 0));

            Vector loc = new Vector(player.getLocation().getX(), 100, player.getLocation().getZ());

            WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
            File schematic = new File("plugins/RemakeHypixelSkyblock/private_island.schematic");
            EditSession session = we.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(world), 1000000);
            try {
                MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session, loc, false);
                return true;
            } catch (MaxChangedBlocksException
                    | com.sk89q.worldedit.data.DataException | IOException e2) {
                e2.printStackTrace();
            }

            world.setGameRuleValue("keepInventory", "true");
            world.setGameRuleValue("naturalRegeneration", "false");
            world.setGameRuleValue("doMobSpawning", "false");
            world.setGameRuleValue("fireSpread", "false");

            new BukkitRunnable() {
                @Override
                public void run() {
                    world.getBlockAt(new Location(world, 0, 100, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, 0, 101, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, 0, 102, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, 0, 103, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, 0, 104, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -1, 100, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -1, 101, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -1, 102, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -1, 103, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -1, 104, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -2, 100, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -2, 101, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -2, 102, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world,-2, 103, 36)).setType(Material.PORTAL);
                    world.getBlockAt(new Location(world, -2, 104, 36)).setType(Material.PORTAL);
                }
            }.runTaskLater(Main.getMain(), 5);

        }
        return false;
    }
}
