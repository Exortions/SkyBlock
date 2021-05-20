package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.RevenantBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerQuest;
import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerState;
import com.itech4kids.skyblock.CustomMobs.Slayer.SlayerType;
import com.itech4kids.skyblock.Enums.PlayerScoreboardState;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            switch (args[0].toLowerCase()){
                case "sven":
                    if (player.getOpenInventory() != null) {
                        switch (Integer.parseInt(args[1])){
                            case 1:
                                if (Config.getPurseCoins(player) >= 100) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 100));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.SVEN, 1);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 2:
                                if (Config.getPurseCoins(player) >= 2000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 2000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.SVEN, 2);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 3:
                                if (Config.getPurseCoins(player) >= 10000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 10000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.SVEN, 3);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 4:
                                if (Config.getPurseCoins(player) >= 50000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 50000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.SVEN, 4);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                        }
                    }
                    break;
                case "tarantula":
                    if (player.getOpenInventory() != null) {
                        switch (Integer.parseInt(args[1])){
                            case 1:
                                if (Config.getPurseCoins(player) >= 100) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 100));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.TARANTULA, 1);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 2:
                                if (Config.getPurseCoins(player) >= 2000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 2000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.TARANTULA, 2);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 3:
                                if (Config.getPurseCoins(player) >= 10000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 10000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.TARANTULA, 3);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 4:
                                if (Config.getPurseCoins(player) >= 50000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 50000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.TARANTULA, 4);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                        }
                    }
                    break;
                case "revenant":
                    if (player.getOpenInventory() != null) {
                        switch (Integer.parseInt(args[1])){
                            case 1:
                                if (Config.getPurseCoins(player) >= 100) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 100));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.REVENANT, 1);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 2:
                                if (Config.getPurseCoins(player) >= 2000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 2000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.REVENANT, 2);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 3:
                                if (Config.getPurseCoins(player) >= 10000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 10000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.REVENANT, 3);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                            case 4:
                                if (Config.getPurseCoins(player) >= 50000) {
                                    try {
                                        Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - 50000));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Main.getMain().slayerManger.createQuest(player, SlayerType.REVENANT, 4);
                                    Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.SLAYER_QUEST_1;
                                }else{
                                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                                }
                                break;
                        }
                    }
                    break;
                case "batphone":
                    if (player.isOp()) {
                        ItemStack maddox_batphone = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
                        SkullMeta maddox_meta = (SkullMeta) maddox_batphone.getItemMeta();
                        maddox_meta.setDisplayName(ChatColor.GREEN + "Maddox Batphone");
                        maddox_meta.setLore(Arrays.asList(ChatColor.GOLD + "Item Ability: Wassup? " + ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK", ChatColor.GRAY + "Lets you call Maddox, when", ChatColor.GRAY + "he's not too busy.", " ", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON"));
                        maddox_meta.setOwner("AntsuVentiLation");
                        maddox_batphone.setItemMeta(maddox_meta);
                        player.getInventory().addItem(maddox_batphone);
                    }
                    break;
                case "inventory":
                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                    skyblockPlayer.setInventory("Slayer", Bukkit.createInventory(null, 36, "Slayer"));
                    Inventory menu = skyblockPlayer.getInventory("Slayer");

                    for (int i = 0; i < menu.getSize(); ++i){
                        menu.setItem(i, ItemHandler.createEmptySpace());
                    }

                    menu.setItem(31, ItemHandler.createExitBarrier());

                    if (Main.getMain().slayerManger.getQuest(player) != null){
                        SlayerQuest quest = Main.getMain().slayerManger.getQuest(player);
                        if (quest.getState().equals(SlayerState.FINISHED)){
                            switch (quest.getType()) {
                                case SVEN:
                                    menu.setItem(13, completeQuest(Material.MUTTON, quest));
                                    break;
                                case TARANTULA:
                                    menu.setItem(13, completeQuest(Material.WEB, quest));
                                    break;
                                case REVENANT:
                                    menu.setItem(13, completeQuest(Material.ROTTEN_FLESH, quest));
                                    break;
                            }
                            player.openInventory(menu);
                        }
                    }else{

                        menu.setItem(10, createSlayerItem(Material.ROTTEN_FLESH, "Revenant Horror", "Abhorrent Zombie stuck between life and death for eternity.", "Zombie", player));
                        menu.setItem(11, createSlayerItem(Material.WEB, "Tarantula Broodfather", "Monstrous Spider who poisons and devours its victims.", "Spider", player));
                        menu.setItem(12, createSlayerItem(Material.MUTTON, "Sven Packmaster", "Rabid Wolf genetically modified by a famous mad scientist. Eats bones and flesh", "Wolf", player));

                        ItemStack notReleased = new ItemStack(Material.COAL_BLOCK);
                        ItemMeta notReleasedMeta = notReleased.getItemMeta();
                        notReleasedMeta.setDisplayName(ChatColor.RED + "Not released yet!");
                        notReleasedMeta.setLore(Arrays.asList(ChatColor.GRAY + "This boss is still in", ChatColor.GRAY + "development!"));
                        notReleased.setItemMeta(notReleasedMeta);

                        menu.setItem(13, notReleased);
                        menu.setItem(14, notReleased);
                        menu.setItem(15, notReleased);
                        menu.setItem(16, notReleased);

                        player.openInventory(menu);
                    }
                    break;
            }
        }
        return false;
    }

    private ItemStack createSlayerItem(Material material, String name, String desc, String name_2, Player player){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        ItemUtil.addLoreMessage(desc, lore);
        meta.setDisplayName(ChatColor.RED + "☠ " + ChatColor.YELLOW + name);
        lore.add(" ");
        lore.add(ChatColor.GRAY + name_2 + " Slayer: " + ChatColor.YELLOW + "LVL " + Config.getSlayerLvl(name_2.toLowerCase(), player));
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view boss!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack completeQuest(Material material, SlayerQuest quest){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        ItemUtil.addLoreMessage("You've slain the boss! Skyblock is now a little safer thanks to you!", lore);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Boss: " + ChatColor.DARK_RED + quest.getBossName());
        lore.add(" ");
        lore.add(ChatColor.DARK_GRAY + "Time to spawn: 00m00s");
        lore.add(ChatColor.DARK_GRAY + "Time to kill: 00m00s");
        lore.add(" ");
        switch (material){
            case ROTTEN_FLESH:
                lore.add(ChatColor.GRAY + "Reward: " + ChatColor.DARK_PURPLE + quest.getExpReward() + " Zombie Slayer XP");
                break;
            case MUTTON:
                lore.add(ChatColor.GRAY + "Reward: " + ChatColor.DARK_PURPLE + quest.getExpReward() + " Wolf Slayer XP");
                break;
            case WEB:
                lore.add(ChatColor.GRAY + "Reward: " + ChatColor.DARK_PURPLE + quest.getExpReward() + " Spider Slayer XP");
                break;
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to collect reward!");
        meta.setDisplayName(ChatColor.GREEN + "Slayer Quest Complete");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

}
