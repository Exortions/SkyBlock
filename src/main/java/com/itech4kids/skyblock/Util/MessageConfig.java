package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {

    public static Main main;

    public MessageConfig(Main main) throws IOException {
        this.main = main;
        createConfig();
    }

    public static String noPermission(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.no_permission"));
    }

    public static String notOnline(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.not_online"));
    }

    public static String notPlayed(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.not_played"));
    }

    public static String onlyPlayers(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.only_players"));
    }

    public static String specifyReason(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.specify_reason"));
    }

    public static String specifyPlayer(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.specify_player"));
    }

    public static String cmdError(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.cmd_error"));
    }

    public static String wipeMsg(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.wipe_msg"));
    }

    public static String invalidArgs(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.invalid_args"));
    }

    public static String directMessageCmdSpecifyMsg(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("errors.dm_cmd_specify_msg"));
    }

    public static String success(){
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        return ChatColor.translateAlternateColorCodes('&', config.getString("info.success"));
    }

    public static void createConfig() throws IOException {
        File file = new File(main.getDataFolder()+File.separator+"messages.yml");
        if (!file.exists()){
            file.createNewFile();
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            // Error-y stuff
            config.set("errors.no_permission", "&cYou do not have permission to use this command!");
            config.set("errors.not_online", "&cThat player isn't online!");
            config.set("errors.not_played", "&cThat player hasn't played before!");
            config.set("errors.only_players", "&cOnly players can use this command!");
            config.set("errors.specify_reason", "&cPlease specify a reason!");
            config.set("errors.specify_player", "&cPlease specify a player!");
            config.set("errors.cmd_error", "&cAn error occured while attempting to perform this command!");
            config.set("errors.wipe_msg", "&cCheating has been detected on one or more of your Skyblock Profiles and your profiles have been wiped.");
            config.set("errors.invalid_args", "&cInvalid arguments!");
            config.set("errors.dm_cmd_specify_msg", "&cPlease specify a message to send!");

            // Good stuff
            config.set("info.success", "&aSuccess!");

            config.save(file);
        }
    }
}
