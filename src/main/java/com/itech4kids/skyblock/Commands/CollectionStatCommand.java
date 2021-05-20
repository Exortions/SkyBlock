package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CollectionStatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.isOp()){
            if(args.length == 5){
                Player target = Bukkit.getPlayer(args[0]);
                if(target.hasPlayedBefore()){
                    String collectionType = args[1];
                    String collection = args[2];
                    if(!checkCollectionTypeValid(collectionType).equals("invalid")){
                        if(!checkCollectionValid(collection).equals("invalid")){
                            String clu = args[3];
                            String value = args[4];
                            if(clu.equalsIgnoreCase("collected")){
                                try {
                                    sender.sendMessage(ChatColor.YELLOW + "Attempting to set " + collection + " collected amount for player " + target.getName() + " to: " + value + "...");
                                    Config.setCollectionCollected(target, collectionType, collection, Integer.parseInt(value));
                                } catch (IOException exception) {
                                    exception.printStackTrace();
                                    sender.sendMessage(ChatColor.RED + "An error occured while trying to set " + collection + " collected amount for player " + target.getName() + " to: " + value + "! (CHECK CONSOLE)");
                                }
                                sender.sendMessage(ChatColor.GREEN + "Sucessfully set " + collection + " collected amount for player " + target.getName() + " to: " + value);
                            } else if(clu.equalsIgnoreCase("level")){
                                try {
                                    sender.sendMessage(ChatColor.YELLOW + "Attempting to set " + collection + " collection level for player " + target.getName() + " to: " + value + "...");
                                    Config.setCollectionLevel(target, collectionType, collection, Integer.parseInt(value));
                                } catch (IOException exception) {
                                    exception.printStackTrace();
                                    sender.sendMessage(ChatColor.RED + "An error occured while trying to set " + collection + " collection level for player " + target.getName() + " to: " + value + "! (CHECK CONSOLE)");
                                }
                                sender.sendMessage(ChatColor.GREEN + "Sucessfully set " + collection + " collection level for player " + target.getName() + " to: " + value);
                            } else if(clu.equalsIgnoreCase("unlocked")){
                                try {
                                    sender.sendMessage(ChatColor.YELLOW + "Attempting to set " + collection + " collection unlocked for player " + target.getName() + " to: " + value + "...");
                                    Config.setCollectionUnlocked(target, collectionType, collection, Boolean.parseBoolean(value));
                                } catch (IOException exception) {
                                    exception.printStackTrace();
                                    sender.sendMessage(ChatColor.RED + "An error occured while trying to set " + collection + " unlocked for player " + target.getName() + " to: " + value + "! (CHECK CONSOLE)");
                                }
                                sender.sendMessage(ChatColor.GREEN + "Sucessfully set " + collection + " unlocked for player " + target.getName() + " to: " + value);
                            } else{
                                sender.sendMessage(ChatColor.RED + "That isn't a valid collection modifier!\nThe collection modifier can only be collected, level, or unlocked.");
                                return true;
                            }
                        } else{
                            sender.sendMessage(ChatColor.RED + "That isn't a valid collection!");
                            return true;
                        }
                    } else{
                        sender.sendMessage(ChatColor.RED + "That isn't a valid collection type!");
                        return true;
                    }
                } else {
                    sender.sendMessage(MessageConfig.notPlayed());
                    return true;
                }
            } else{
                sender.sendMessage(ChatColor.RED + "Not enough arguments!\n" + ChatColor.GREEN + "Usage: " + ChatColor.YELLOW + "/collectionstat <username> <collection type> <collection> <collected/level/unlocked> <value>");
                return true;
            }
        } else{
            sender.sendMessage(MessageConfig.noPermission());
        }
        return false;
    }

    public String checkCollectionTypeValid(String str){
        String new_str = null;
        if(str.equalsIgnoreCase("farming")){
            new_str = "farming";
        } else if(str.equalsIgnoreCase("mining")){
            new_str = "mining";
        } else if(str.equalsIgnoreCase("combat")){
            new_str = "combat";
        } else if(str.equalsIgnoreCase("foraging")){
            new_str = "foraging";
        } else if(str.equalsIgnoreCase("fishing")){
            new_str = "fishing";
        } else if(str.equalsIgnoreCase("boss")){
            new_str = "boss";
        } else{
            new_str = "invalid";
        }
        return new_str;
    }

    public String checkCollectionValid(String str){
        String new_str = null;
        if(str.equalsIgnoreCase("wheat")){
            new_str = "wheat";
        } else if(str.equalsIgnoreCase("carrot")){
            new_str = "carrot";
        } else if(str.equalsIgnoreCase("potato")){
            new_str = "potato";
        } else{
            new_str = "invalid";
        }
        return new_str;
    }
}
