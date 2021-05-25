package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageConfig {

    public static Main main;
    
    private File file = new File(main.getDataFolder()+File.seperator+"messages.yml");
    private YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

    public MessageConfig(Main main) throws IOException {
        this.main = main;
        createConfig();
    }

    public static String noPermission(){
//         File file = new File(main.getDataFolder()+File.separator+"messages.yml");
//         YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String str = config.getString("no_permission");
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String notOnline(){
//         File file = new File(main.getDataFolder()+File.separator+"messages.yml");
//         YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String str = config.getString("not_online");
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String notPlayed(){
//         File file = new File(main.getDataFolder()+File.separator+"messages.yml");
//         YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String str = config.getString("not_played");
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String onlyPlayers(){
//         File file = new File(main.getDataFolder()+File.separator+"messages.yml");
//         YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String str = config.getString("only_players");
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static String specifyReason(){
//         File file = new File(main.getDataFolder()+File.separator+"messages.yml");
//         YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String str = config.getString("specify_reason");
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    
    public static String specifyPlayer(){
        return ChatColor.translateAlternateColorCodes('&', config.getString("specify_player"));
    }
    
    public static String cmdError(){
        return ChatColor.translateAlternateColorCodes('&', config.getString("cmd_error"));
    }
    
    public static String success(){
        return ChatColor.translateAlternateColorCodes('&', config.getString("success"));
    }

    public static void createConfig() throws IOException {
        if (!file.exists()){
            file.createNewFile();
            
            // Error-y stuff
            config.set("no_permission", "&cYou do not have permission to use this command!");
            config.set("not_online", "&cThat player isn't online!");
            config.set("not_played", "&cThat player hasn't played before!");
            config.set("only_players", "&cOnly players can use this command!");
            config.set("specify_reason", "&cPlease specify a reason!");
            config.set("specify_player", "&cPlease specify a player!");
            config.set("cmd_error", "&cAn error occured while attempting to perform this command!");

            // Good stuff
            config.set("success", "&aSuccess!");
            
            config.save(file);
        }
    }
}
