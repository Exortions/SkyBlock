package com.itech4kids.skyblock.Events;

import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.SkillsManager;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.io.IOException;

public class SkyblockSkillLevelUpEvent extends Event implements Cancellable {

    private final SkyblockPlayer skyblockPlayer;
    private SkillType skillType;
    private boolean isCancelled;
    private int oldlvl;
    private int newlvl;
    private static final HandlerList HANDLERS = new HandlerList();

    public SkyblockSkillLevelUpEvent(SkyblockPlayer skyblockPlayer, SkillType skillType) throws IOException {
        this.skyblockPlayer = skyblockPlayer;
        this.skillType = skillType;
        if (Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) < 50) {
            skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.LEVEL_UP, 10, 1);
            oldlvl = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase());
            newlvl = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + 1;
            Config.setStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase(), newlvl);
            Player player = skyblockPlayer.getBukkitPlayer();

            player.sendMessage(ChatColor.DARK_AQUA + "--------------------------------------------------");
            player.sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "  SKILL LEVEL UP " + ChatColor.DARK_AQUA + WordUtils.capitalize(skillType.name().toLowerCase()) + " " + ChatColor.DARK_GRAY + (Config.getStatLvl(player, skillType.name().toLowerCase()) - 1) + "➜" + ChatColor.DARK_AQUA + (Config.getStatLvl(player, skillType.name().toLowerCase())));
            player.sendMessage(" ");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "  REWARDS");
            for (String s : SkillsManager.getRewards(skillType, Config.getStatLvl(player, skillType.name().toLowerCase()))) {
                player.sendMessage("   " + s);
            }
            player.sendMessage(ChatColor.DARK_AQUA + "--------------------------------------------------");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
            Config.setStatExp(player, skillType.name().toLowerCase(), 0);
        }
    }

    public int getNewlvl(){return newlvl;}

    public int getOldlvl(){return oldlvl;}

    public SkyblockPlayer getPlayer(){return skyblockPlayer;}

    public SkillType getSkillType(){return skillType;}

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
