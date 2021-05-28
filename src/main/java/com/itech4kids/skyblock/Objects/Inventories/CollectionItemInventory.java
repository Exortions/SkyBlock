package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Commands.PlayerCommands.CollectionsCommand;
import com.itech4kids.skyblock.Commands.PlayerCommands.CollectionsCommand;
import com.itech4kids.skyblock.Enums.CollectionItemTypes;
import com.itech4kids.skyblock.Enums.CollectionTypes;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionItemInventory {

    private String collectionTypeString = "";
    private CollectionsCommand collectionsCommand;

    public CollectionItemInventory(Player player, CollectionItemTypes itemTypes, CollectionTypes collectionTypes){
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Item Collection", Bukkit.createInventory(null, 54, "Item Collection"));
        Inventory inv = skyblockPlayer.getInventory("Item Collection");
        if(collectionTypes.equals(CollectionTypes.FARMING)){
            this.collectionTypeString = "Farming";
        } else if(collectionTypes.equals(CollectionTypes.MINING)){
            this.collectionTypeString = "Mining";
        } else if(collectionTypes.equals(CollectionTypes.COMBAT)){
            this.collectionTypeString = "Combat";
        } else if(collectionTypes.equals(CollectionTypes.FORAGING)){
            this.collectionTypeString = "Foraging";
        } else if(collectionTypes.equals(CollectionTypes.FISHING)){
            this.collectionTypeString = "Fishing";
        } else if(collectionTypes.equals(CollectionTypes.BOSS)){
            this.collectionTypeString = "Boss";
        }
        player.sendMessage(collectionTypeString);
        ItemStack goback = ItemHandler.createPageBackArrow(this.collectionTypeString);
        ItemStack close = ItemHandler.createExitBarrier();
        ItemStack space = ItemHandler.createEmptySpace();
        List<ItemStack> utilItems = new ArrayList<>();
        utilItems.add(goback);
        utilItems.add(close);
        utilItems.add(space);
        switch (itemTypes){
            case WHEAT:
                createInventory(inv, player, CollectionItemTypes.WHEAT, CollectionTypes.FARMING, utilItems);
                break;
            case POTATO:
                break;
            case CARROT:
                break;
        }
    }

    public void createInventory(Inventory inv, Player player, CollectionItemTypes itemTypes, CollectionTypes collectionTypes, List<ItemStack> utilItems){
        ItemStack goback = utilItems.get(0);
        ItemStack close = utilItems.get(1);
        ItemStack space = utilItems.get(2);
        for(int i = 0; i < 54; i++){
            inv.setItem(inv.firstEmpty(), space);
        }

        switch (itemTypes) {
            case WHEAT:
                collectionsCommand.checkUnlocked(player, inv, "farming", "wheat", 4, new ItemStack(Material.BARRIER), Config.getCollectionLevel(player, "farming", "wheat"), Config.getCollectionCollected(player, "farming", "wheat"), 296,  ChatColor.BLUE + " Wheat Minion" + ChatColor.GRAY + " Recipes", ChatColor.GREEN + "Enchanted Book (Harvesting V)" + ChatColor.GRAY + "  Recipe", " ", " ", "", "", "", "", "", "", "", "", "", "", "", "", "", (short) 0);
                System.out.println(player);
                System.out.println(inv);
                System.out.println(new ItemStack(Material.BARRIER));
                checkCollectionProgress(player, inv, "farming", "wheat", Config.getCollectionCollected(player, "farming", "wheat"));
                break;
            case CARROT:
                break;
        }

        player.openInventory(inv);
    }

    public void checkCollectionProgress(Player player, Inventory inv, String collectionType, String collection, int collected){
        if (collected >= 100) {
            inv.setItem(18, ItemHandler.createCollectionProgressItem(3, collection, "I", Config.getCollectionCollected(player, collectionType, collection), "100", 100, Collections.singletonList(ChatColor.BLUE + " Wheat Minion " + ChatColor.GRAY + "Recipes"), true, 1));
        } else if(collected <= 100){
            inv.setItem(18, ItemHandler.createCollectionProgressItem(1, collection, "I", Config.getCollectionCollected(player, collectionType, collection), "100", 100, Collections.singletonList(ChatColor.BLUE + " Wheat Minion " + ChatColor.GRAY + "Recipes"), true, 1));
        }

        if (collected >= 250) {

        }  if (collected == 750) {

        }  if (collected == 1500) {

        }  if (collected == 3000) {

        }  if (collected == 5000) {

        }  if (collected == 10000) {

        }  if (collected == 25000) {

        }  if (collected == 50000) {

        }  if (collected == 200000) {

        }  if (collected == 400000) {

        }  if (collected == 600000) {

        }  if (collected == 800000) {

        }  if (collected == 1000000) {

        }  if (collected == 1200000) {

        }  if (collected == 1400000) {

        }
    }

}