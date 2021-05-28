package com.itech4kids.skyblock.CustomMobs.Slayer;

import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.RevenantBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.SvenBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.TarantulaBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss.Revenant.DeformedRevenant;
import com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss.Revenant.RevenantChampion;
import com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss.Revenant.RevenantSycophant;
import com.itech4kids.skyblock.Enums.PlayerScoreboardState;
import com.itech4kids.skyblock.Events.SkyblockEntitySkillGainEvent;
import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Events.SlayerEvent.SkyblockSlayerKillEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.itech4kids.skyblock.Util.LaunchPadConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.ItemSaddle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SlayerListener implements Listener {

    @EventHandler
    public void onMaddoxClick(InventoryClickEvent e) throws IOException {
        Player player = (Player) e.getWhoClicked();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        if (e.getView().getTitle().equalsIgnoreCase("slayer")){
            if (e.getCurrentItem() != null){
                skyblockPlayer.lastInventory = e.getClickedInventory();
                if (e.getCurrentItem().getItemMeta().hasDisplayName()){
                    if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase().equals("slayer quest complete")){
                        int slayerExp = Main.getMain().slayerManger.getQuest(player).getExpReward();
                        SlayerType type = Main.getMain().slayerManger.getQuest(player).getType();
                        switch (type){
                            case REVENANT:
                                Config.setSlayerXP("zombie", player, Config.getSlayerXP("zombie", player) + slayerExp);
                                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " SLAYER QUEST COMPLETE");
                                if ((Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("zombie", player), SlayerType.REVENANT) - Config.getSlayerXP("zombie", player)) <= 0){
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " LVL UP! " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "➜" + ChatColor.YELLOW + " Zombie Slayer LVL " + (Config.getSlayerLvl("zombie", player) + 1));
                                    Config.setSlayerLvl("zombie", player, Config.getSlayerLvl("zombie", player) + 1);
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " REWARD AVAILABLE");
                                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  CLICK TO COLLECT");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
                                }else{
                                    if (Config.getSlayerLvl("zombie", player) == 9){
                                        player.sendMessage(ChatColor.YELLOW + "  Zombie Slayer LVL " + Config.getSlayerLvl("zombie", player) + "    " + ChatColor.GREEN + "" + ChatColor.BOLD + "LVL MAXED OUT!");
                                    }else {
                                        player.sendMessage(ChatColor.YELLOW + "  Zombie Slayer LVL " + Config.getSlayerLvl("zombie", player) + "    " + ChatColor.GRAY + "Next LVL in " + ChatColor.LIGHT_PURPLE + (Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("zombie", player), SlayerType.REVENANT) - Config.getSlayerXP("zombie", player)) + " XP");
                                    }
                                }
                                break;
                            case TARANTULA:
                                Config.setSlayerXP("spider", player, Config.getSlayerXP("spider", player) + slayerExp);
                                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " SLAYER QUEST COMPLETE");
                                if ((Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("spider", player), SlayerType.REVENANT) - Config.getSlayerXP("spider", player)) <= 0){
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " LVL UP! " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "➜" + ChatColor.YELLOW + " Spider Slayer LVL " + (Config.getSlayerLvl("spider", player) + 1));
                                    Config.setSlayerLvl("spider", player, Config.getSlayerLvl("spider", player) + 1);
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " REWARD AVAILABLE");
                                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  CLICK TO COLLECT");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
                                }else {
                                    if (Config.getSlayerLvl("spider", player) == 9){
                                        player.sendMessage(ChatColor.YELLOW + "  Spider Slayer LVL " + Config.getSlayerLvl("spider", player) + "    " + ChatColor.GREEN + "" + ChatColor.BOLD + "LVL MAXED OUT!");
                                    }else {
                                        player.sendMessage(ChatColor.YELLOW + "  Spider Slayer LVL " + Config.getSlayerLvl("spider", player) + "    " + ChatColor.GRAY + "Next LVL in " + ChatColor.LIGHT_PURPLE + (Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("spider", player), SlayerType.TARANTULA) - Config.getSlayerXP("spider", player)) + " XP");
                                    }
                                }
                                break;
                            case SVEN:
                                Config.setSlayerXP("wolf", player, Config.getSlayerXP("wolf", player) + slayerExp);
                                player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " SLAYER QUEST COMPLETE");
                                if ((Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("wolf", player), SlayerType.REVENANT) - Config.getSlayerXP("wolf", player)) <= 0){
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " LVL UP! " + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "➜" + ChatColor.YELLOW + " Wolf Slayer LVL " + (Config.getSlayerLvl("wolf", player) + 1));
                                    Config.setSlayerLvl("wolf", player, Config.getSlayerLvl("wolf", player) + 1);
                                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + " REWARD AVAILABLE");
                                    player.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "  CLICK TO COLLECT");
                                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
                                }else {
                                    if (Config.getSlayerLvl("wolf", player) == 9){
                                        player.sendMessage(ChatColor.YELLOW + "  Wolf Slayer LVL " + Config.getSlayerLvl("wolf", player) + "    " + ChatColor.GREEN + "" + ChatColor.BOLD + "LVL MAXED OUT!");
                                    }else {
                                        player.sendMessage(ChatColor.YELLOW + "  Wolf Slayer LVL " + Config.getSlayerLvl("wolf", player) + "    " + ChatColor.GRAY + "Next LVL in " + ChatColor.LIGHT_PURPLE + (Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("wolf", player), SlayerType.TARANTULA) - Config.getSlayerXP("wolf", player)) + " XP");
                                    }
                                }
                                break;
                        }
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 2);
                        Main.getMain().slayerManger.completeQuest(player);
                        Main.getMain().getPlayer(player.getName()).state = PlayerScoreboardState.DEFAULT;
                        player.performCommand("slayer inventory");
                    }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).endsWith("Horror")){
                        skyblockPlayer.lastInventory = e.getClickedInventory();
                        skyblockPlayer.setInventory("Revenant Horror", Bukkit.createInventory(null, 54, "Revenant Horror"));
                        Inventory menu = skyblockPlayer.getInventory("Revenant Horror");

                        ItemStack notReleased = new ItemStack(Material.COAL_BLOCK);
                        ItemMeta notReleasedMeta = notReleased.getItemMeta();
                        notReleasedMeta.setDisplayName(ChatColor.RED + "Not released yet!");
                        notReleasedMeta.setLore(Arrays.asList(ChatColor.GRAY + "This boss is still in", ChatColor.GRAY + "development!"));
                        notReleased.setItemMeta(notReleasedMeta);

                        for (int i = 0; i < menu.getSize(); ++i){
                            menu.setItem(i, ItemHandler.createEmptySpace());
                        }

                        menu.setItem(49, ItemHandler.createPageBackArrow("Slayer"));

                        menu.setItem(11, createSlayerStartItem(Material.ROTTEN_FLESH, 1, ChatColor.GREEN, ChatColor.RED + "Life Drain", "Drains health every few seconds.", null, null, null, null, 500, 15, 5, 100));
                        menu.setItem(12, createSlayerStartItem(Material.ROTTEN_FLESH, 2, ChatColor.YELLOW, ChatColor.RED + "Life Drain", "Drains health every few seconds.", ChatColor.GREEN + "Pestilence", "Deals AOE damage every second, shredding armor by 25%.", null, null, 20000, 25, 25, 2000));
                        menu.setItem(13, createSlayerStartItem(Material.ROTTEN_FLESH, 3, ChatColor.RED, ChatColor.RED + "Life Drain", "Drains health every few seconds.", ChatColor.GREEN + "Pestilence", "Deals AOE damage every second, shredding armor by 25%.", ChatColor.RED + "Enrage", "Gets real mad once in a while.", 400000, 120, 100, 10000));
                        menu.setItem(14, createSlayerStartItem(Material.ROTTEN_FLESH, 4, ChatColor.DARK_RED, ChatColor.RED + "Life Drain", "Drains health every few seconds.", ChatColor.GREEN + "Pestilence", "Deals AOE damage every second, shredding armor by 25%.", ChatColor.RED + "Enrage", "Gets real mad once in a while.", 1500000, 400, 500, 50000));
                        menu.setItem(15, notReleased);

                        menu.setItem(28, createLevelingRewards(SlayerType.REVENANT, player));
                        menu.setItem(30, createBossDropsItem(SlayerType.REVENANT));
                        menu.setItem(32, createBossCraftsItem(SlayerType.REVENANT));
                        menu.setItem(34, rngsusMeter(SlayerType.REVENANT));

                        player.openInventory(menu);

                    }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).endsWith("Broodfather")){
                        skyblockPlayer.lastInventory = e.getClickedInventory();
                        skyblockPlayer.setInventory("Tarantula Broodfather", Bukkit.createInventory(null, 54, "Tarantula Broodfather"));
                        Inventory menu = skyblockPlayer.getInventory("Tarantula Broodfather");

                        ItemStack notReleased = new ItemStack(Material.COAL_BLOCK);
                        ItemMeta notReleasedMeta = notReleased.getItemMeta();
                        notReleasedMeta.setDisplayName(ChatColor.RED + "Not released yet!");
                        notReleasedMeta.setLore(Arrays.asList(ChatColor.GRAY + "This boss is still in", ChatColor.GRAY + "development!"));
                        notReleased.setItemMeta(notReleasedMeta);

                        for (int i = 0; i < menu.getSize(); ++i){
                            menu.setItem(i, ItemHandler.createEmptySpace());
                        }

                        menu.setItem(49, ItemHandler.createPageBackArrow("Slayer"));

                        menu.setItem(11, createSlayerStartItem(Material.WEB, 1, ChatColor.GREEN, ChatColor.YELLOW + "Combat Jump", "The spider will often attempt to jump behind you.", null, null, null, null, 750, 35, 5, 100));
                        menu.setItem(12, createSlayerStartItem(Material.WEB, 2, ChatColor.YELLOW, ChatColor.YELLOW + "Combat Jump", "The spider will often attempt to jump behind you.", ChatColor.RED + "Noxious", "Deals AOE damage every second, reducing your healing by 50%.", null, null, 30000, 45, 25, 2000));
                        menu.setItem(13, createSlayerStartItem(Material.WEB, 3, ChatColor.RED, ChatColor.YELLOW + "Combat Jump", "The spider will often attempt to jump behind you.", ChatColor.RED + "Noxious", "Deals AOE damage every second, reducing your healing by 50%.", null, null, 900000, 210, 100, 10000));
                        menu.setItem(14, createSlayerStartItem(Material.WEB, 4, ChatColor.DARK_RED, ChatColor.YELLOW + "Combat Jump", "The spider will often attempt to jump behind you.", ChatColor.RED + "Noxious", "Deals AOE damage every second, reducing your healing by 50%.", null, null, 2400000, 530, 500, 50000));
                        menu.setItem(15, notReleased);

                        menu.setItem(28, createLevelingRewards(SlayerType.TARANTULA, player));
                        menu.setItem(30, createBossDropsItem(SlayerType.TARANTULA));
                        menu.setItem(32, createBossCraftsItem(SlayerType.TARANTULA));
                        menu.setItem(34, rngsusMeter(SlayerType.TARANTULA));

                        player.openInventory(menu);

                    }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).endsWith("Packmaster")){
                        skyblockPlayer.lastInventory = e.getClickedInventory();
                        skyblockPlayer.setInventory("Sven Packmaster", Bukkit.createInventory(null, 54, "Sven Packmaster"));
                        Inventory menu = skyblockPlayer.getInventory("Sven Packmaster");

                        ItemStack notReleased = new ItemStack(Material.COAL_BLOCK);
                        ItemMeta notReleasedMeta = notReleased.getItemMeta();
                        notReleasedMeta.setDisplayName(ChatColor.RED + "Not released yet!");
                        notReleasedMeta.setLore(Arrays.asList(ChatColor.GRAY + "This boss is still in", ChatColor.GRAY + "development!"));
                        notReleased.setItemMeta(notReleasedMeta);

                        for (int i = 0; i < menu.getSize(); ++i){
                            menu.setItem(i, ItemHandler.createEmptySpace());
                        }

                        menu.setItem(49, ItemHandler.createPageBackArrow("Slayer"));

                        menu.setItem(11, createSlayerStartItem(Material.MUTTON, 1, ChatColor.GREEN, ChatColor.GREEN + "Agile", "The wolf is small and fast, making it hard to hit.", null, null, null, null, 2000, 60, 5, 100));
                        menu.setItem(12, createSlayerStartItem(Material.MUTTON, 2, ChatColor.YELLOW, ChatColor.GREEN + "Agile", "The wolf is small and fast, making it hard to hit.", ChatColor.WHITE + "True Damage", "Ignores your defense. Very painful.", null, null, 40000, 80, 25, 2000));
                        menu.setItem(13, createSlayerStartItem(Material.MUTTON, 3, ChatColor.RED, ChatColor.GREEN + "Agile", "The wolf is small and fast, making it hard to hit.", ChatColor.WHITE + "True Damage", "Ignores your defense. Very painful.", ChatColor.AQUA + "Call the pups!", "At 50% health, calls its deadly pack of pups.", 750000, 180, 100, 10000));
                        menu.setItem(14, createSlayerStartItem(Material.MUTTON, 4, ChatColor.DARK_RED, ChatColor.GREEN + "Agile", "The wolf is small and fast, making it hard to hit.", ChatColor.WHITE + "True Damage", "Ignores your defense. Very painful.", ChatColor.AQUA + "Call the pups!", "At 50% health, calls its deadly pack of pups.", 2000000, 440, 500, 50000));
                        menu.setItem(15, notReleased);

                        menu.setItem(28, createLevelingRewards(SlayerType.SVEN, player));
                        menu.setItem(30, createBossDropsItem(SlayerType.SVEN));
                        menu.setItem(32, createBossCraftsItem(SlayerType.SVEN));
                        menu.setItem(34, rngsusMeter(SlayerType.SVEN));

                        player.openInventory(menu);

                    }
                }
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("revenant horror")){
            if (e.getCurrentItem().getType().equals(Material.ROTTEN_FLESH)){
                switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase()){
                    case "revenant horror 1":
                        player.performCommand("slayer revenant 1");
                        player.closeInventory();
                        break;
                    case "revenant horror 2":
                        player.performCommand("slayer revenant 2");
                        player.closeInventory();
                        break;
                    case "revenant horror 3":
                        player.performCommand("slayer revenant 3");
                        player.closeInventory();
                        break;
                    case "revenant horror 4":
                        player.performCommand("slayer revenant 4");
                        player.closeInventory();
                        break;
                }
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("tarantula broodfather")){
            if (e.getCurrentItem().getType().equals(Material.WEB)){
                switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase()){
                    case "tarantula broodfather 1":
                        player.performCommand("slayer tarantula 1");
                        player.closeInventory();
                        break;
                    case "tarantula broodfather 2":
                        player.performCommand("slayer tarantula 2");
                        player.closeInventory();
                        break;
                    case "tarantula broodfather 3":
                        player.performCommand("slayer tarantula 3");
                        player.closeInventory();
                        break;
                    case "tarantula broodfather 4":
                        player.performCommand("slayer tarantula 4");
                        player.closeInventory();
                        break;
                }
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("sven packmaster")){
            if (e.getCurrentItem().getType().equals(Material.MUTTON)){
                switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase()){
                    case "sven packmaster 1":
                        player.performCommand("slayer sven 1");
                        player.closeInventory();
                        break;
                    case "sven packmaster 2":
                        player.performCommand("slayer sven 2");
                        player.closeInventory();
                        break;
                    case "sven packmaster 3":
                        player.performCommand("slayer sven 3");
                        player.closeInventory();
                        break;
                    case "sven packmaster 4":
                        player.performCommand("slayer sven 4");
                        player.closeInventory();
                        break;
                }
            }
        }
    }

    public ItemStack rngsusMeter(SlayerType type) {
        ItemStack item = new ItemStack(Material.PAINTING, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);

        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "RNGesus Meter");
        switch (type) {
            case SVEN:
                lore.add(ChatColor.DARK_GRAY + "Sven Packmaster");
                break;
            case TARANTULA:
                lore.add(ChatColor.DARK_GRAY + "Tarantula Broodfather");
                break;
            case REVENANT:
                lore.add(ChatColor.DARK_GRAY + "Revenant Horror");
                break;
        }
        lore.add(" ");
        ItemUtil.addLoreMessage("Feeling unlucky? Slay bosses to fill the meter and guarantee an RNGesus drop.", lore);
        lore.add(" ");
        ItemUtil.addLoreMessage("Gaining an RNGesus drop will reset the meter.", lore);
        lore.add(" ");
        lore.add(ChatColor.LIGHT_PURPLE + "Progress:");
        lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.LIGHT_PURPLE + "0" + ChatColor.DARK_PURPLE + "%");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createBossCraftsItem(SlayerType type) {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);

        meta.setDisplayName(ChatColor.GREEN + "Slayer Recipes");
        switch (type){
            case SVEN:
                lore.add(ChatColor.DARK_GRAY + "Sven Packmaster");
                lore.add(" ");
                ItemUtil.addLoreMessage("There are 22 recipes related to the Wolf boss.", lore);
                break;
            case REVENANT:
                lore.add(ChatColor.DARK_GRAY + "Revenant Horror");
                lore.add(" ");
                ItemUtil.addLoreMessage("There are 26 recipes related to the Zombie boss.", lore);
                break;
            case TARANTULA:
                lore.add(ChatColor.DARK_GRAY + "Tarantula Broodfather");
                lore.add(" ");
                ItemUtil.addLoreMessage("There are 14 recipes related to the Spider boss.", lore);
                break;
        }
        lore.add(" ");
        ItemUtil.addLoreMessage("Unlock recipes and collect rare drops in order to craft powerful items.", lore);
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Unlocked: " + ChatColor.YELLOW + "0");
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view recipes!");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createBossDropsItem(SlayerType type){
        ItemStack item = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);

        meta.setDisplayName(ChatColor.GOLD + "Boss Drops");
        switch (type){
            case REVENANT:
                lore.add(ChatColor.DARK_GRAY + "Revenant Horror");
                lore.add(" ");
                ItemUtil.addLoreMessage("Usually, the boss will drop Revenant Flesh which are used for slayer crafts.", lore);
                lore.add(" ");
                ItemUtil.addLoreMessage("If you're lucky, you may get one out of 13 possible drops from this boss!", lore);
                break;
            case SVEN:
                lore.add(ChatColor.DARK_GRAY + "Sven Packmaster");
                lore.add(" ");
                ItemUtil.addLoreMessage("Usually, the boss will drop Wolf Tooth which are used for slayer crafts.", lore);
                lore.add(" ");
                ItemUtil.addLoreMessage("If you're lucky, you may get one out of 8 possible drops from this boss!", lore);
                break;
            case TARANTULA:
                lore.add(ChatColor.DARK_GRAY + "Tarantula Broodfather");
                lore.add(" ");
                ItemUtil.addLoreMessage("Usually, the boss will drop Tarantula Web which are used for slayer crafts.", lore);
                lore.add(" ");
                ItemUtil.addLoreMessage("If you're lucky, you may get one out of 8 possible drops from this boss!", lore);
                break;
        }
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to view drops!");

        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createLevelingRewards(SlayerType type, Player player){
        ItemStack item = new ItemStack(Material.GOLD_BLOCK, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);

        meta.setDisplayName(ChatColor.DARK_PURPLE + "Boss Leveling Rewards");

        switch (type){
            case SVEN:
                lore.add(ChatColor.DARK_GRAY + "Wolf Slayer LVL");
                lore.add(" ");
                lore.add(ChatColor.DARK_PURPLE + "1. " + ChatColor.GRAY + "Kill boss to get XP");
                lore.add(ChatColor.DARK_PURPLE + "2. " + ChatColor.GRAY + "Gain LVL from XP");
                lore.add(ChatColor.DARK_PURPLE + "3. " + ChatColor.GRAY + "Unlock rewards per LVL");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Current LVL: " + ChatColor.YELLOW + Config.getSlayerLvl("wolf", player));
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Wolf Slayer XP to LVL " + (Config.getSlayerLvl("wolf", player) + 1) + ":");
                lore.add(ChatColor.DARK_PURPLE + "--------------------" + ChatColor.LIGHT_PURPLE + format.format(Config.getSlayerXP("wolf", player)) + ChatColor.DARK_PURPLE + "/" + ChatColor.LIGHT_PURPLE + format.format(Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("wolf", player), SlayerType.SVEN)));
                lore.add(" ");
                lore.add(ChatColor.YELLOW + "Click to view levels!");
                break;
            case TARANTULA:
                lore.add(ChatColor.DARK_GRAY + "Spider Slayer LVL");
                lore.add(" ");
                lore.add(ChatColor.DARK_PURPLE + "1. " + ChatColor.GRAY + "Kill boss to get XP");
                lore.add(ChatColor.DARK_PURPLE + "2. " + ChatColor.GRAY + "Gain LVL from XP");
                lore.add(ChatColor.DARK_PURPLE + "3. " + ChatColor.GRAY + "Unlock rewards per LVL");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Current LVL: " + ChatColor.YELLOW + Config.getSlayerLvl("spider", player));
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Spider Slayer XP to LVL " + (Config.getSlayerLvl("spider", player) + 1) + ":");
                lore.add(ChatColor.DARK_PURPLE + "--------------------" + ChatColor.LIGHT_PURPLE + format.format(Config.getSlayerXP("spider", player)) + ChatColor.DARK_PURPLE + "/" + ChatColor.LIGHT_PURPLE + format.format(Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("spider", player), SlayerType.TARANTULA)));
                lore.add(" ");
                lore.add(ChatColor.YELLOW + "Click to view levels!");
                break;
            case REVENANT:
                lore.add(ChatColor.DARK_GRAY + "Zombie Slayer LVL");
                lore.add(" ");
                lore.add(ChatColor.DARK_PURPLE + "1. " + ChatColor.GRAY + "Kill boss to get XP");
                lore.add(ChatColor.DARK_PURPLE + "2. " + ChatColor.GRAY + "Gain LVL from XP");
                lore.add(ChatColor.DARK_PURPLE + "3. " + ChatColor.GRAY + "Unlock rewards per LVL");
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Current LVL: " + ChatColor.YELLOW + Config.getSlayerLvl("zombie", player));
                lore.add(" ");
                lore.add(ChatColor.GRAY + "Zombie Slayer XP to LVL " + (Config.getSlayerLvl("zombie", player) + 1) + ":");
                lore.add(ChatColor.DARK_PURPLE + "--------------------" + ChatColor.LIGHT_PURPLE + format.format(Config.getSlayerXP("zombie", player)) + ChatColor.DARK_PURPLE + "/" + ChatColor.LIGHT_PURPLE + format.format(Main.getMain().slayerManger.getNextExpAmount(Config.getSlayerLvl("zombie", player), SlayerType.REVENANT)));
                lore.add(" ");
                lore.add(ChatColor.YELLOW + "Click to view levels!");
                break;
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createSlayerStartItem(Material material, int lvl, ChatColor color, String abilityName1, String abilityLore1, String abilityName2, String abilityLore2, String abilityName3, String abilityLore3, int hp, int dps, int xp, int cost){
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        switch (material){
            case ROTTEN_FLESH:
                meta.setDisplayName(color + "Revenant Horror " + lvl);
                break;
            case WEB:
                meta.setDisplayName(color + "Tarantula Broodfather " + lvl);
                break;
            case MUTTON:
                meta.setDisplayName(color + "Sven Packmaster " + lvl);
                break;
        }

        switch (lvl){
            case 1:
                lore.add(ChatColor.DARK_GRAY + "Beginner");
                break;
            case 2:
                lore.add(ChatColor.DARK_GRAY + "Strong");
                break;
            case 3:
                lore.add(ChatColor.DARK_GRAY + "Challenging");
                break;
            case 4:
                lore.add(ChatColor.DARK_GRAY + "Deadly");
                break;
        }

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Health: " + ChatColor.RED + hp + " ❤");
        lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + dps + ChatColor.GRAY + " per second");
        lore.add(" ");
        lore.add(abilityName1);
        ItemUtil.addLoreMessage(abilityLore1, lore);
        if (abilityName2 != null){
            lore.add(" ");
            lore.add(abilityName2);
            ItemUtil.addLoreMessage(abilityLore2, lore);
        }
        if (abilityName3 != null){
            lore.add(" ");
            lore.add(abilityName3);
            ItemUtil.addLoreMessage(abilityLore3, lore);
        }

        lore.add(" ");
        lore.add(ChatColor.GRAY + "Reward: " + ChatColor.LIGHT_PURPLE + xp + " Zombie Slayer XP");
        lore.add(ChatColor.DARK_GRAY + "  + Boss Drops");
        lore.add(" ");

        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);

        lore.add(ChatColor.GRAY + "Cost to start: " + ChatColor.GOLD + format.format(cost));
        lore.add(" ");
        lore.add(ChatColor.YELLOW + "Click to slay!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onSlayerBossKill(SkyblockSlayerKillEvent e){
        SlayerBoss boss = e.getEntity();
        if (Main.getMain().slayerManger.getQuest(boss) != null){
            Main.getMain().slayerManger.getQuest(boss).getSummoner().sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "NICE! SLAYER BOSS SLAIN!");
            Main.getMain().slayerManger.getQuest(boss).getSummoner().sendMessage(ChatColor.GRAY + "  Talk to Maddox to claim your Slayer XP!");
            Main.getMain().slayerManger.getQuest(boss).getSummoner().playSound(boss.getVanillaEntity().getLocation(), Sound.LEVEL_UP, 10, 2);
            Main.getMain().getPlayer(e.getPlayer().getName()).state = PlayerScoreboardState.SLAYER_QUEST_3;
            Main.getMain().slayerManger.getQuest(boss).setState(SlayerState.FINISHED);
        }
    }

    @EventHandler
    public void onBatPhone(PlayerInteractEvent e){
        if (e.getPlayer().getItemInHand() != null){
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                Player player = e.getPlayer();
                if (ChatColor.stripColor(player.getItemInHand().getItemMeta().getDisplayName()).toLowerCase().contains("maddox batphone")) {
                    player.sendMessage(ChatColor.YELLOW + "⓪ Ringing...");
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 2);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 4);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 6);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 8);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 10);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendMessage(ChatColor.YELLOW + "⓪ Ringing... Ringing...");
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 2);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 4);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 6);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 8);
                        }
                    }.runTaskLater(Main.getMain(), 15);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendMessage(ChatColor.YELLOW + "⓪ Ringing... Ringing... Ringing...");
                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 2);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 4);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 6);
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                                }
                            }.runTaskLater(Main.getMain(), 8);
                        }
                    }.runTaskLater(Main.getMain(), 30);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            TextComponent message = new TextComponent("⓪ How does the lobster answer? Shello! ");
                            message.setColor(net.md_5.bungee.api.ChatColor.GREEN);

                            ComponentBuilder cb = new ComponentBuilder("Click!").color(net.md_5.bungee.api.ChatColor.YELLOW);

                            TextComponent click = new TextComponent("[OPEN MENU]");
                            click.setColor(net.md_5.bungee.api.ChatColor.DARK_GREEN);
                            click.setBold(true);
                            click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, cb.create()));
                            click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/slayer inventory"));

                            message.addExtra(click);

                            player.spigot().sendMessage(message);

                            //player.sendMessage(ChatColor.GREEN + "⓪ How does the lobster answer? Shello! " + ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "[OPEN MENU]");
                            player.playSound(player.getLocation(), Sound.WOOD_CLICK, 10, 2);
                        }
                    }.runTaskLater(Main.getMain(), 45);
                }
            }
        }
    }

    public void summonParticles(SkyblockEntitySkillGainEvent e){
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FIREWORKS_SPARK, true, (float)e.getSEntity().getLocation().getBlockX(), (float)e.getSEntity().getLocation().getBlockY() + 2, (float)e.getSEntity().getLocation().getBlockZ(), 0.3F, 0.3F, 0.3F, 0.0F, 1, 1000);
        final PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.SPELL_WITCH, true, (float)e.getSEntity().getLocation().getBlockX(), (float)e.getSEntity().getLocation().getBlockY() + 1, (float)e.getSEntity().getLocation().getBlockZ(), 0.3F, 0.3F, 0.3F, 0.0F, 1, 1000);
        final PacketPlayOutWorldParticles packet3 = new PacketPlayOutWorldParticles(EnumParticle.SPELL, true, (float)e.getSEntity().getLocation().getBlockX(), (float)e.getSEntity().getLocation().getBlockY() + 1, (float)e.getSEntity().getLocation().getBlockZ(), 0.3F, 0.3F, 0.3F, 0.0F, 1, 1000);
        Player player = e.getPlayer().getBukkitPlayer();
        Random rand = new Random();

        player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 2);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 4);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 6);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 8);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 10);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 12);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
                for (int i = 0; i < 300; ++i) {
                    for (Player player1 : Bukkit.getOnlinePlayers()) {
                        if (rand.nextInt(5) == 1) {
                            ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet);
                            ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet3);
                        }
                        ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet2);
                        ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet2);
                    }
                }
            }
        }.runTaskLater(Main.getMain(), 14);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 16);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 18);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 22);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 24);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 26);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 28);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 30);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 32);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 34);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 36);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.FIZZ, 10, 2);
            }
        }.runTaskLater(Main.getMain(), 38);

        for (int i = 0; i < 300; ++i) {
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                if (rand.nextInt(5) == 1) {
                    ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet);
                    ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet3);
                }
                ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet2);
                ((CraftPlayer) player1).getHandle().playerConnection.sendPacket(packet2);
            }
        }
    }

    @EventHandler
    public void onSlayerKill(SkyblockEntitySkillGainEvent e){
        Player player = e.getPlayer().getBukkitPlayer();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        if (Main.getMain().slayerManger.getQuest(player) != null){
            SlayerQuest quest = Main.getMain().slayerManger.getQuest(player);
            switch (quest.getType()){
                case SVEN:
                    if (e.getSEntity().getType().equals(EntityType.WOLF)) {
                        if (quest.getState().equals(SlayerState.SUMMONING)) {
                            quest.addExp((int) e.getExpAmount());
                            if (quest.getExp() >= quest.getNeededExp()) {
                                quest.setState(SlayerState.FIGHTING);
                                skyblockPlayer.state = PlayerScoreboardState.SLAYER_QUEST_2;
                                SvenBoss sven = (SvenBoss) quest.getBoss();

                                summonParticles(e);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        sven.summon(e.getSEntity().getLocation());
                                        player.playEffect(e.getSEntity().getLocation(), Effect.EXPLOSION_HUGE, 20);
                                        player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 10, 2);
                                    }
                                }.runTaskLater(Main.getMain(), 40);
                            }
                        }
                    }
                    break;
                case REVENANT:
                    if (e.getSEntity().getType().equals(EntityType.ZOMBIE)) {
                        if (quest.getState().equals(SlayerState.SUMMONING)) {
                            quest.addExp((int) e.getExpAmount());

                            switch (quest.getBoss().getBossLevel()){
                                case 3:
                                    if (new Random().nextInt(8) == 1){
                                        RevenantSycophant deformedRevenant = new RevenantSycophant();
                                        deformedRevenant.summon(e.getSEntity().getLocation());
                                    }
                                    break;
                                case 4:
                                    if (new Random().nextInt(11) == 2){
                                        RevenantChampion deformedRevenant = new RevenantChampion();
                                        deformedRevenant.summon(e.getSEntity().getLocation());
                                    }else if (new Random().nextInt(21) == 3){
                                        DeformedRevenant deformedRevenant = new DeformedRevenant();
                                        deformedRevenant.summon(e.getSEntity().getLocation());
                                    }
                                    break;
                            }

                            if (quest.getExp() >= quest.getNeededExp()) {
                                quest.setState(SlayerState.FIGHTING);
                                skyblockPlayer.state = PlayerScoreboardState.SLAYER_QUEST_2;
                                RevenantBoss revenant = (RevenantBoss) quest.getBoss();

                                summonParticles(e);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        revenant.summon(e.getSEntity().getLocation());
                                        player.playEffect(e.getSEntity().getLocation(), Effect.EXPLOSION_HUGE, 20);
                                        player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 10, 2);
                                    }
                                }.runTaskLater(Main.getMain(), 40);
                            }
                        }
                    }
                    break;
                case TARANTULA:
                    if (e.getSEntity().getType().equals(EntityType.SPIDER)) {
                        if (quest.getState().equals(SlayerState.SUMMONING)) {
                            quest.addExp((int) e.getExpAmount());
                            if (quest.getExp() >= quest.getNeededExp()) {
                                quest.setState(SlayerState.FIGHTING);
                                skyblockPlayer.state = PlayerScoreboardState.SLAYER_QUEST_2;
                                TarantulaBoss tarantula = (TarantulaBoss) quest.getBoss();
                                player.playSound(player.getLocation(), Sound.FIZZ, 10, 1);

                                summonParticles(e);

                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        tarantula.summon(e.getSEntity().getLocation());
                                        player.playEffect(e.getSEntity().getLocation(), Effect.EXPLOSION_HUGE, 20);
                                        player.playSound(player.getLocation(), Sound.WITHER_SPAWN, 10, 2);
                                    }
                                }.runTaskLater(Main.getMain(), 40);
                            }
                        }
                    }
                    break;
            }


            if (Main.getMain().slayerManger.getQuest(player).getBoss().getVanillaEntity().getType().equals(e.getSEntity().getType())){
                quest.addExp((int) e.getExpAmount());
                if (quest.getExp() >= quest.getNeededExp()){
                    skyblockPlayer.state = PlayerScoreboardState.SLAYER_QUEST_2;

                }
            }
        }
    }
}
