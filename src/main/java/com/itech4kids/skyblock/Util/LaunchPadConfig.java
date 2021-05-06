package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class LaunchPadConfig {

       public static Main main;

    public LaunchPadConfig(Main main) throws IOException {
        this.main = main;
        createConfig();
    }

    public static HashMap<String, Location> getLaunchPadLocations(String name){
        File file = new File(main.getDataFolder()+File.separator+"launchpad.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        HashMap<String, Location> locationsMap = new HashMap<>();
        locationsMap.put("from", (Location) config.get("launchpadlocations." + name + ".from"));
        locationsMap.put("to", (Location) config.get("launchpadlocations." + name + ".to"));
        locationsMap.put("infront", (Location) config.get("launchpadlocations." + name + ".infront"));
        locationsMap.put("teleport", (Location) config.get("launchpadlocations." + name + ".teleport"));
        return locationsMap;
    }

    public static ArrayList<String> getLaunchPadStrings(){
        File file = new File(main.getDataFolder()+File.separator+"launchpad.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ArrayList<String> strings = new ArrayList<>();
        for(String key : config.getConfigurationSection("launchpadlocations").getKeys(false)){
            strings.add(key);
        }
        return strings;
    }

    public static void addLaunchPad(String name, Location from, Location to, Location infront, Location teleport) throws IOException {
        File file = new File(main.getDataFolder()+File.separator+"launchpad.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("launchpadlocations." + name + ".from", from);
        config.set("launchpadlocations." + name + ".to", to);
        config.set("launchpadlocations." + name + ".infront", infront);
        config.set("launchpadlocations." + name + ".teleport", teleport);
        config.save(file);
    }

    public static void createConfig() throws IOException {
        File file = new File(main.getDataFolder()+File.separator+"launchpad.yml");
        if (!file.exists()){
            file.createNewFile();
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.save(file);
        }
    }
}
