package com.itech4kids.skyblock.Events;

import com.itech4kids.skyblock.Util.Config;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.IOException;

public class SkyblockCollectionLevelUpEvent extends Event implements Cancellable {

    private boolean isCancelled;
    private String oldlvl;
    private String newlvl;
    private static final HandlerList HANDLERS = new HandlerList();

    public SkyblockCollectionLevelUpEvent(Player player, String collectionType, String collection, String oldLevel, String newLevel, int newLevelInt, String reward) {
        try{
            Config.setCollectionLevel(player, collectionType, collection, newLevelInt);
        } catch(IOException exception){
            exception.printStackTrace();
        }

        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "================================");
        player.sendMessage(ChatColor.GOLD + "  " + ChatColor.BOLD + "COLLECTION LEVEL UP " + ChatColor.YELLOW + StringUtils.capitalize(collection) + " " + ChatColor.DARK_GRAY + oldLevel + " ➜ " + ChatColor.YELLOW + newLevel);
        player.sendMessage("");
        player.sendMessage(ChatColor.GREEN + "  " + ChatColor.BOLD + "REWARDS");
        getReward(reward);
        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "" + ChatColor.STRIKETHROUGH + "================================");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 100, 2);
    }

    public String getReward(String s){
        if(s != ""){
            return s;
        } else{
            return ChatColor.GREEN + "None";
        }
    }

    public String getNewlvl(){ return newlvl; }
    public String getOldlvl(){ return oldlvl; }

    public static HandlerList getHandlersList(){
        return HANDLERS;
    }

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    public static HandlerList getHandler(){
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}