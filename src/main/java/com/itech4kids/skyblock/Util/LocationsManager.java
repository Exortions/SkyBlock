package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockLocation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationsManager {

    public static Main main;

    public LocationsManager(){
        main = Main.getMain();
    }

    public static SkyblockLocation getLocation(String name){
        File file = new File(Main.getMain().getDataFolder()+File.separator+"locations.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return new SkyblockLocation((Location) config.get(name + ".pos1"), (Location) config.get(name + ".pos2"),
                config.getString(name + ".name"), ChatColor.valueOf(config.getString(name + ".color")), config.getInt(name + ".weight"));
    }

    public static SkyblockLocation getLocation(Location location){
        File file = new File(Main.getMain().getDataFolder()+File.separator+"locations.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        SkyblockLocation loc = null;
        for (String string : getStoredLocations()){
            loc = new SkyblockLocation((Location) config.get(string + ".pos1"), (Location) config.get(string + ".pos2"),
                    config.getString(string + ".name"), ChatColor.valueOf(config.getString(string + ".color")), config.getInt(string + ".weight"));
            for (Block block : blocksFromTwoPoints(loc.pos1, loc.pos2)){
                if (block.getLocation().distance(location) < 2){
                    loc = new SkyblockLocation((Location) config.get(string + ".pos1"), (Location) config.get(string + ".pos2"),
                            config.getString(string + ".name"), ChatColor.valueOf(config.getString(string + ".color")), config.getInt(string + ".weight"));
                    break;
                }else{
                    loc = null;
                }
            }
        }
        return loc;
    }

    public static List<Block> blocksFromTwoPoints(Location loc1, Location loc2){
       List<Block> blocks = new ArrayList<Block>();

        World w = loc1.getWorld();
        int x1 = loc1.getBlockX();
        int y1 = loc1.getBlockY();
        int z1 = loc1.getBlockZ();

        int x2 = loc2.getBlockX();
        int y2 = loc2.getBlockY();
        int z2 = loc2.getBlockZ();

        int xMin, yMin, zMin;
        int xMax, yMax, zMax;
        int x, y, z;

        if(x1 > x2){
            xMin = x2;
            xMax = x1;
        }else{
            xMin = x1;
            xMax = x2;
        }

        if(y1 > y2){
            yMin = y2;
            yMax = y1;
        }else{
            yMin = y1;
            yMax = y2;
        }

        if(z1 > z2){
            zMin = z2;
            zMax = z1;
        }else{
            zMin = z1;
            zMax = z2;
        }

        for(x = xMin; x <= xMax; x ++){
            for(y = yMin; y <= yMax; y ++){
                for(z = zMin; z <= zMax; z ++){
                    Block b = new Location(w, x, y, z).getBlock();
                    blocks.add(b);
                }
            }
        }

        return blocks;
    }

    public static ArrayList<String> getStoredLocations(){
        File file = new File(Main.getMain().getDataFolder()+File.separator+"locations.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> strings = new ArrayList<>();
        for(String key : config.getKeys(false)){
            strings.add(key);
        }
        return strings;
    }

    public static void createNewLocation(Location pos1, Location pos2, String name, ChatColor color, int weight) throws IOException {
        File file = new File(Main.getMain().getDataFolder()+File.separator+"locations.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(name + ".pos1", pos1);
        config.set(name + ".pos2", pos2);
        config.set(name + ".name", name);
        config.set(name + ".color", color.name());
        config.set(name + ".weight", weight);
        config.save(file);
    }

    public static void createFile() throws IOException {
        File file = new File(Main.getMain().getDataFolder()+File.separator+"locations.yml");
        if (!file.exists()){
            file.createNewFile();
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.save(file);
        }
    }
}
