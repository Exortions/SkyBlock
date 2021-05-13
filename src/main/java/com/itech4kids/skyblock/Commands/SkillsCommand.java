package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockGuiItem;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockSkillGuiItem;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class SkillsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Your Skills", Bukkit.createInventory(null, 54, "Your Skills"));
        Inventory menu = skyblockPlayer.getInventory("Your Skills");

        for (int i = 0; i < 54; ++i){
            menu.setItem(i, ItemHandler.createEmptySpace());
        }

        SkyblockSkillGuiItem farming = new SkyblockSkillGuiItem(SkillType.FARMING, skyblockPlayer, "Harvest crops and shear sheep to earn Farming XP!", "Farmhand", null,294, 0);
        SkyblockSkillGuiItem mining = new SkyblockSkillGuiItem(SkillType.MINING, skyblockPlayer, "Spelunk islands for ores and valuable materials to earn Mining XP!", "Spelunker", null, 274, 0);
        SkyblockSkillGuiItem combat = new SkyblockSkillGuiItem(SkillType.COMBAT, skyblockPlayer, "Fight mobs and players to earn Combat XP!", "Warrior", null, 272, 0);
        SkyblockSkillGuiItem foraging = new SkyblockSkillGuiItem(SkillType.FORAGING, skyblockPlayer, "Cut trees and forage for other plants to earn Foraging XP!", "Logger", null, 6, 3);
        SkyblockSkillGuiItem fishing = new SkyblockSkillGuiItem(SkillType.FISHING, skyblockPlayer, "Visit your local pond to fish and earn Fishing XP!", "Treasure Hunter", null, 346, 0);
        SkyblockSkillGuiItem enchanting = new SkyblockSkillGuiItem(SkillType.ENCHANTING, skyblockPlayer, "Enchant items to earn Enchanting XP!", "Conjourer", null, 116, 0);
        SkyblockSkillGuiItem alchemy = new SkyblockSkillGuiItem(SkillType.ALCHEMY, skyblockPlayer, "Brew potions to earn Alchemy XP!", "Brewer", null, 379, 0);
        SkyblockSkillGuiItem carpentry = new SkyblockSkillGuiItem(SkillType.CARPENTRY, skyblockPlayer, "Craft items to earn Carpentry XP!", "Carpentry", null, 58, 0);
        List<String> runecraftingRewards = new ArrayList<>();
        runecraftingRewards.add(ChatColor.GRAY + "Access to runes with the same level");
        SkyblockSkillGuiItem runecrafting = new SkyblockSkillGuiItem(SkillType.RUNECRAFTING, skyblockPlayer, "Slay bosses, runic mobs & fuse runes to earn Runecrafting XP!", "Runecrafter", runecraftingRewards, 378, 0);
        SkyblockSkillGuiItem social = new SkyblockSkillGuiItem(SkillType.SOCIAL, skyblockPlayer, "Gain Social XP for every new unique guest, hosting guests and visiting islands!", "Social", null, 388, 0);
        SkyblockSkillGuiItem taming = new SkyblockSkillGuiItem(SkillType.TAMING, skyblockPlayer, "Level up pets to gain Taming XP!", "Zoologist", null, 383, 0);
        SkyblockSkillGuiItem dungeoneering = new SkyblockSkillGuiItem(SkillType.CATACOMBS, skyblockPlayer, "Coming Soon!", "Catacombs", null, 397, 3);
        SkyblockGuiItem skillsItem = new SkyblockGuiItem(ChatColor.GREEN + "Your Skills", "View your skill progression and rewards.", null, Material.DIAMOND_SWORD);

        menu.setItem(4, skillsItem);
        menu.setItem(19, farming);
        menu.setItem(20, mining);
        menu.setItem(21, combat);
        menu.setItem(22, foraging);
        menu.setItem(23, fishing);
        menu.setItem(24, enchanting);
        menu.setItem(25, alchemy);
        menu.setItem(29, carpentry);
        menu.setItem(30, runecrafting);
        menu.setItem(31, social);
        menu.setItem(32, taming);
        menu.setItem(33, dungeoneering);

        menu.setItem(48, ItemHandler.createPageBackArrow("Skyblock Menu"));
        menu.setItem(49, ItemHandler.createExitBarrier());
        player.openInventory(menu);
        return false;
    }
}
