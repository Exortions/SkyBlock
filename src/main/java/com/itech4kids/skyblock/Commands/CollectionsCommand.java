package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Enums.CollectionItemTypes;
import com.itech4kids.skyblock.Enums.CollectionTypes;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.CollectionItemInventory;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CollectionsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can run this command!");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());

        ItemStack emptySpace = ItemHandler.createEmptySpace();

        List<String> rankingsLore = new ArrayList<>();
        rankingsLore.add(ChatColor.GRAY + "View all of the items avaliable");
        rankingsLore.add(ChatColor.GRAY + "in SkyBlock. collect more of an");
        rankingsLore.add(ChatColor.GRAY + "item to unlock rewards on your");
        rankingsLore.add(ChatColor.GRAY + "way to becoming a master of");
        rankingsLore.add(ChatColor.GRAY + "SkyBlock!");
        rankingsLore.add("");
        rankingsLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        rankingsLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        rankingsLore.add("");
        rankingsLore.add(ChatColor.YELLOW + "Click to show rankings!");
        List<String> farmingCollectionLore = new ArrayList<>();
        farmingCollectionLore.add(ChatColor.GRAY + "View your Farming Collection!");
        farmingCollectionLore.add("");
        farmingCollectionLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        farmingCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        List<String> miningCollectionLore = new ArrayList<>();
        miningCollectionLore.add(ChatColor.GRAY + "View your Mining Collection!");
        miningCollectionLore.add("");
        miningCollectionLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        miningCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        List<String> combatCollectionLore = new ArrayList<>();
        combatCollectionLore.add(ChatColor.GRAY + "View your Combat Collection!");
        combatCollectionLore.add("");
        combatCollectionLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        combatCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        List<String> foragingCollectionLore = new ArrayList<>();
        foragingCollectionLore.add(ChatColor.GRAY + "View your Foraging Collection!");
        foragingCollectionLore.add("");
        foragingCollectionLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        foragingCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        List<String> fishingCollectionLore = new ArrayList<>();
        fishingCollectionLore.add(ChatColor.GRAY + "View your Fishing Collection!");
        fishingCollectionLore.add("");
        fishingCollectionLore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        fishingCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");
        List<String> bossCollectionLore = new ArrayList<>();
        bossCollectionLore.add(ChatColor.GRAY + "View your progress and claim");
        bossCollectionLore.add(ChatColor.GRAY + "rewards you have obtained from");
        bossCollectionLore.add(ChatColor.GRAY + "defeating SkyBlock bosses!");
        bossCollectionLore.add("");
        bossCollectionLore.add(ChatColor.GRAY + "Boss Collection Unlocked: " + ChatColor.YELLOW + "0.0" + ChatColor.GOLD + "%");
        bossCollectionLore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + "0" + ChatColor.GOLD + "/" + ChatColor.YELLOW + "0");

        List<String> notUnlockedLore = new ArrayList<>();
        notUnlockedLore.add(ChatColor.GRAY + "Find this item to add it to your");
        notUnlockedLore.add(ChatColor.GRAY + "collection and unlock collection");
        notUnlockedLore.add(ChatColor.GRAY + "rewards!");

        ItemStack farming_collection = ItemHandler.createBasicItem(Material.GOLD_HOE, ChatColor.GREEN + "Farming Collection", farmingCollectionLore, (short) 0, false, 1);
        ItemStack mining_collection = ItemHandler.createBasicItem(Material.STONE_PICKAXE, ChatColor.GREEN + "Mining Collection", farmingCollectionLore, (short) 0, false, 1);
        ItemStack combat_collection = ItemHandler.createBasicItem(Material.STONE_SWORD, ChatColor.GREEN + "Combat Collection", farmingCollectionLore, (short) 0, false, 1);
        ItemStack foraging_collection = ItemHandler.createBasicItem(Material.SAPLING, ChatColor.GREEN + "Foraging Collection", farmingCollectionLore, (short) 3, false, 1);
        ItemStack fishing_collection = ItemHandler.createBasicItem(Material.FISHING_ROD, ChatColor.GREEN + "Fishing Collection", farmingCollectionLore, (short) 0, false, 1);
        ItemStack boss_collection = ItemHandler.createBasicHead(ChatColor.DARK_PURPLE + "Boss Collection", bossCollectionLore, 1, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk1M2I2YzY4NDQ4ZTdlNmI2YmY4ZmIyNzNkNzIwM2FjZDhlMWJlMTllODE0ODFlYWQ1MWY0NWRlNTlhOCJ9fX0=", 1);

        if(args.length == 1){
            if(args[0].equalsIgnoreCase("main")){

                skyblockPlayer.setInventory("Collection", Bukkit.createInventory(null, 54, "Collection"));
                Inventory menu = skyblockPlayer.getInventory("Collection");

                ItemStack rankings = ItemHandler.createBasicItem(Material.PAINTING, ChatColor.GREEN + "Collection", rankingsLore, (short) 0,false, 1);

                for(int i = 0; i < 54; i++) {
                    menu.setItem(menu.firstEmpty(), emptySpace);
                }

                menu.setItem(4, rankings);
                menu.setItem(20, farming_collection);
                menu.setItem(21, mining_collection);
                menu.setItem(22, combat_collection);
                menu.setItem(23, foraging_collection);
                menu.setItem(24, fishing_collection);
                menu.setItem(31, boss_collection);
                menu.setItem(48, ItemHandler.createPageBackArrow("SkyBlock Menu"));
                menu.setItem(49, ItemHandler.createExitBarrier());

                player.openInventory(skyblockPlayer.getInventory("Collection"));
                return false;
            } else if(args[0].equalsIgnoreCase("farming")){
                skyblockPlayer.setInventory("Farming Collection", Bukkit.createInventory(null, 54, "Farming Collection"));
                Inventory menu = skyblockPlayer.getInventory("Farming Collection");

                ItemStack notUnlocked = ItemHandler.createBasicItem(Material.INK_SACK, ChatColor.RED + "Not Unlocked", notUnlockedLore, (short) 8, false, 1);

                for(int i = 0; i < 54; i++){
                    menu.setItem(menu.firstEmpty(), emptySpace);
                }

                checkUnlocked(player, menu, "farming", "wheat", 10, notUnlocked, Config.getCollectionLevel(player, "farming", "wheat"), Config.getCollectionCollected(player, "farming", "wheat"), 296,  ChatColor.BLUE + " Wheat Minion" + ChatColor.GRAY + " Recipes", ChatColor.GREEN + "Enchanted Book (Harvesting V)" + ChatColor.GRAY + "  Recipe", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
                checkUnlocked(player, menu, "farming", "carrot", 11, notUnlocked, Config.getCollectionLevel(player, "farming", "carrot"), Config.getCollectionCollected(player, "farming", "carrot"), 391, ChatColor.BLUE + " Carrot Minion" + ChatColor.GRAY + " Recipes", ChatColor.GREEN + "Simple Carrot Candy " + ChatColor.GRAY + "Recipe", ChatColor.DARK_PURPLE + "Catching Egg " + ChatColor.DARK_GRAY + "(" + ChatColor.DARK_RED + "COMING SOON" + ChatColor.DARK_GRAY + ")", ChatColor.GREEN + "Enchanted Carrot " + ChatColor.GRAY + "Recipe", ChatColor.GREEN + "Enchanted Carrot on a Stick " + ChatColor.GRAY + "Recipe", "", "", "", "", "", "" ,"", "", "", "", "", "", (short) 0);
                checkUnlocked(player, menu, "farming", "potato", 12, notUnlocked, Config.getCollectionLevel(player, "farming", "potato"), Config.getCollectionCollected(player, "farming", "potato"), 391, ChatColor.BLUE + " Potato Minion" + ChatColor.GRAY + " Recipes", "", "", "", "", "", "", "", "", "", "" ,"", "", "", "", "", "", (short) 0);

                for(int i = 31; i < 35; i++){
                    menu.setItem(i, new ItemStack(Material.AIR));
                }
                for(int i = 37; i < 44; i++){
                    menu.setItem(i, new ItemStack(Material.AIR));
                }

                menu.setItem(4, farming_collection);

                menu.setItem(48, ItemHandler.createPageBackArrow("Collection"));
                menu.setItem(49, ItemHandler.createExitBarrier());

                player.openInventory(skyblockPlayer.getInventory("Farming Collection"));
            } else if(args[0].equalsIgnoreCase("mining")){
                player.sendMessage("mining");
            } else if(args[0].equalsIgnoreCase("combat")){
                player.sendMessage("combat");
            } else if(args[0].equalsIgnoreCase("foraging")){
                player.sendMessage("foraging");
            } else if(args[0].equalsIgnoreCase("fishing")){
                player.sendMessage("fishing");
            } else if(args[0].equalsIgnoreCase("boss")){
                player.sendMessage("boss");
            } else if(args[0].equalsIgnoreCase("help")){
                player.sendMessage(ChatColor.GREEN + "All avaliable menu names:\n" + ChatColor.AQUA + " - Farming\n - Mining\n - Combat\n - Foraging\n - Fishing\n - Boss");
                return true;
            } else{
                player.sendMessage(ChatColor.RED + "Incorrect Usage!\n/collections <menu_name | set to help for list of menus> OR /collections <menu_name> <item_name>");
                return true;
            }
        } else if(args.length == 2){
            if(args[0].equalsIgnoreCase("farming")){
                if(args[1].equalsIgnoreCase("wheat")){
                    player.sendMessage(ChatColor.YELLOW + "You opened wheat collection menu XDD");
                    new CollectionItemInventory(player, CollectionItemTypes.WHEAT, CollectionTypes.FARMING);
                }
            } else if(args[0].equalsIgnoreCase("mining")){

            } else if(args[0].equalsIgnoreCase("combat")){

            } else if(args[0].equalsIgnoreCase("foraging")){

            } else if(args[0].equalsIgnoreCase("fishing")){

            } else if(args[0].equalsIgnoreCase("boss")){

            } else{
                player.sendMessage(ChatColor.RED + "Incorrect Usage!\n/collections <menu_name | set to help for list of menus> OR /collections <menu_name> <item_name>");
                return true;
            }
        } else{
            player.sendMessage(ChatColor.RED + "Incorrect Usage!\n/collections <menu_name | set to help for list of menus> OR /collections <menu_name> <item_name>");
            return true;
        }
        return false;
    }

    // I = 100
    // II = 250
    // III = 750
    // IV = 1.5k
    // V = 3k
    // VI = 5k
    // VII = 10k
    // VIII = 25k
    // IX = 50k
    // X = 200k
    // XI = 400k
    // XII = 600k
    // XIII = 800k
    // XIV = 1M
    // XV = 1.2M
    // XVI = 1.4M

    public void checkUnlocked(Player player, Inventory menu, String collectionType, String collection, int slot, ItemStack notUnlocked, int collectionLevel, int amountCollected, int typeID, String rewards, String rewards1, String rewards2, String rewards3, String rewards4, String rewards5, String rewards6, String rewards7, String rewards8, String rewards9, String rewards10, String rewards11, String rewards12, String rewards13, String rewards14, String rewards15, String rewards16, Short data){
        NumberFormat numFormat = NumberFormat.getInstance();
        numFormat.setGroupingUsed(true);
        String amountCollectedFormatted = numFormat.format(amountCollected);

        List<String> coopPlayers = new ArrayList<>();
        coopPlayers.add("Exortions");
        coopPlayers.add("OptimusChen");
        if(collectionLevel == 1) {
            int percentageUnlocked = amountCollected*100/100;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "I", percentageUnlocked, amountCollectedFormatted, Integer.toString(100), coopPlayers, rewards, data));
        } else if(collectionLevel == 2){
            int percentageUnlocked = amountCollected*100/250;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "II", percentageUnlocked, amountCollectedFormatted, Integer.toString(250), coopPlayers, rewards2, data));
        } else if(collectionLevel == 3){
            int percentageUnlocked = amountCollected*100/750;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "III", percentageUnlocked, amountCollectedFormatted, Integer.toString(750), coopPlayers, rewards3, data));
        } else if(collectionLevel == 4){
            int percentageUnlocked = amountCollected*100/1500;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "IV", percentageUnlocked, amountCollectedFormatted, "1.5k", coopPlayers, rewards4, data));
        } else if(collectionLevel == 5){
            int percentageUnlocked = amountCollected*100/3000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "V", percentageUnlocked, amountCollectedFormatted, "3k", coopPlayers, rewards5, data));
        } else if(collectionLevel == 6){
            int percentageUnlocked = amountCollected*100/5000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "VI", percentageUnlocked, amountCollectedFormatted, "5k", coopPlayers, rewards6, data));
        } else if(collectionLevel == 7){
            int percentageUnlocked = amountCollected*100/10000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "VII", percentageUnlocked, amountCollectedFormatted, "10k", coopPlayers, rewards7, data));
        } else if(collectionLevel == 8){
            int percentageUnlocked = amountCollected*100/25000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "VIII", percentageUnlocked, amountCollectedFormatted, "25k", coopPlayers, rewards8, data));
        } else if(collectionLevel == 9){
            int percentageUnlocked = amountCollected*100/50000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "IX", percentageUnlocked, amountCollectedFormatted, "50k", coopPlayers, rewards9, data));
        } else if(collectionLevel == 10){
            int percentageUnlocked = amountCollected*100/200000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "X", percentageUnlocked, amountCollectedFormatted, "200k", coopPlayers, rewards10, data));
        } else if(collectionLevel == 11){
            int percentageUnlocked = amountCollected*100/400000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XI", percentageUnlocked, amountCollectedFormatted, "400k", coopPlayers, rewards11, data));
        } else if(collectionLevel == 12){
            int percentageUnlocked = amountCollected*100/600000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XII", percentageUnlocked, amountCollectedFormatted, "600k", coopPlayers, rewards12, data));
        } else if(collectionLevel == 13){
            int percentageUnlocked = amountCollected*100/800000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XIII", percentageUnlocked, amountCollectedFormatted, "800k", coopPlayers, rewards13, data));
        } else if(collectionLevel == 14){
            int percentageUnlocked = amountCollected*100/1000000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XIV", percentageUnlocked, amountCollectedFormatted, "1M", coopPlayers, rewards14, data));
        } else if(collectionLevel == 15){
            int percentageUnlocked = amountCollected*100/1200000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XV", percentageUnlocked, amountCollectedFormatted, "1.2M", coopPlayers, rewards15, data));
        } else if(collectionLevel == 16){
            int percentageUnlocked = amountCollected*100/1400000;
            menu.setItem(slot, ItemHandler.createCollectionItem(typeID, StringUtils.capitalize(collection), "XVI", percentageUnlocked, amountCollectedFormatted, "1.4M", coopPlayers, rewards16, data));
        }

        else if(!Config.getCollectionUnlocked(player, collectionType, collection)){
            menu.setItem(slot, notUnlocked);
        }
    }
}