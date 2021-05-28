package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.Item;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TodoListCommand implements CommandExecutor {

    private Item item;

    public boolean finishedSwords = false;
    public boolean finishedArmor = false;
    public boolean finishedSkills = false;
    public boolean finishedCollections = false;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args){
        item = item;
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "[Skyblock] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Todo List", Bukkit.createInventory(null, 54, "Todo List"));
        Inventory menu = skyblockPlayer.getInventory("Todo List");

        ItemStack emptySpace = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());

        for (int index = 0; index < 54; index++) {
            menu.setItem(index, emptySpace);
        }

        List<String> notFinishedLore = new ArrayList<>();
        notFinishedLore.add(ChatColor.GRAY + "This task is not ");
        notFinishedLore.add(ChatColor.GRAY + "finished yet!");
        notFinishedLore.add("");
        notFinishedLore.add(ChatColor.GRAY + "When you're finished with the");
        notFinishedLore.add(ChatColor.GRAY + "task, click on this item to complete it!");
        List<String> finishedLore = new ArrayList<>();
        finishedLore.add("This task is");
        finishedLore.add("complete!");

        ItemStack swordsNotDone = item.createBasicItem(Material.STAINED_CLAY, ChatColor.RED + "Swords - Not Finished", notFinishedLore, (short) 14, false);
        ItemStack swordsDone = item.createBasicItem(Material.STAINED_CLAY, ChatColor.GREEN + "Swords - Finished", finishedLore, (short) 13, false);

        if(finishedSwords == false){
            menu.setItem(21, swordsNotDone);
        } else if(finishedSwords){
            menu.setItem(21, swordsDone);
        }

        player.sendMessage(ChatColor.YELLOW + "[DEBUG] You opened the Todo List");
        player.openInventory(skyblockPlayer.getInventory("Todo List"));
        return false;
    }

    public boolean getSwordTask(){
        return this.finishedSwords;
    }

    public void setSwordtask(boolean swordTask){
        this.finishedSwords = swordTask;
    }
}