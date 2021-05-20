package com.itech4kids.skyblock.Dugeons;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Chest;
import org.bukkit.block.Skull;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonRoom {

    private File schematic;
    private Location entrance;
    private HashMap<String, Location> secrets;

    public DungeonRoom(File schematic, Location entrance, HashMap<String, Location> secrets){
        this.schematic = schematic;
        this.entrance = entrance;
        this.secrets = secrets;
    }

    public boolean spawnSchematic(){
        Vector loc = new Vector(entrance.getX(), entrance.getY(), entrance.getZ());

        WorldEditPlugin we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        EditSession session = we.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(entrance.getWorld()), 1000000);
        try {
            MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session, loc, false);
            return true;
        } catch (MaxChangedBlocksException
                | com.sk89q.worldedit.data.DataException | IOException e2) {
            e2.printStackTrace();
        }

        return false;
    }

    public void generateRoom() {
        spawnSchematic();
        for (Map.Entry<String, Location> entry : secrets.entrySet()){
            switch (entry.getKey().toLowerCase()){
                case "chest":
                    entry.getValue().getBlock().setType(Material.CHEST);
                    Chest chest = (Chest) entry.getValue().getBlock();
                    break;
                case "bat":
                    Bat bat = entrance.getWorld().spawn(entry.getValue(), Bat.class);
                    bat.setAwake(false);
                    break;
                case "item":
                    Item item = entrance.getWorld().spawn(entry.getValue(), Item.class);
                    item.setItemStack(new ItemStack(Material.ENDER_PEARL, 3));
                    break;
                case "essence":
                    entry.getValue().getBlock().setType(Material.SKULL_ITEM);
                    Skull skull = (Skull) entry.getValue().getBlock();
                    skull.setSkullType(SkullType.PLAYER);
                    skull.setOwner("Exortions");
                    break;
            }
        }
    }

}
