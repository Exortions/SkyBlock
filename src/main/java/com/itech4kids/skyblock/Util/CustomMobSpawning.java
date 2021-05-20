package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomMobSpawning {

    public static Main main;

    public CustomMobSpawning(Main main) throws IOException {
        this.main = main;
        createConfig();
    }

    public static void addMobSpawnLocation(Location location, String mobType) throws IOException {
        File file = new File(main.getDataFolder()+File.separator+"mobspawns.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getList(mobType) == null){
            ArrayList<Location> locations = new ArrayList<>();
            locations.add(location);
            config.set(mobType, locations);
            config.save(file);
        }else{
            ArrayList<Location> locations = (ArrayList<Location>) config.getList(mobType);
            locations.add(location);
            config.set(mobType, locations);
            config.save(file);
        }
    }

    public static ArrayList<Location> getMobSpawnLocations(String mobType){
        File file = new File(main.getDataFolder()+File.separator+"mobspawns.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if (config.getList(mobType) == null){
            return null;
        }else{
            return (ArrayList<Location>) config.getList(mobType);
        }
    }

    public static void createConfig() throws IOException {
        File file = new File(main.getDataFolder()+File.separator+"mobspawns.yml");
        if (!file.exists()){
            file.createNewFile();
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.save(file);
        }
    }
}
