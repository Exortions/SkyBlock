package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MainConfig {

    public FileConfiguration config;
    public static Main main;

    public MainConfig(Main main){
        this.config = main.getConfig();
        this.main = main;
        main.getConfig().options().copyDefaults();
    }

    public static void createConfig() throws IOException {
        File configFile = new File(main.getDataFolder()+File.separator+"RemakeHypixelSkyblock"+"config.yml");
        if (!configFile.exists()) {
            configFile.createNewFile();
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set("todo-task-swords", false);
            config.set("todo-task-armor", false);
            config.set("todo-task-skills", false);
            config.set("todo-task-collections", false);
            config.save(configFile);
        }
    }
}