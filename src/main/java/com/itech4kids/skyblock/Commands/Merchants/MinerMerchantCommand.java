package com.itech4kids.skyblock.Commands.Merchants;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
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

public class MinerMerchantCommand implements CommandExecutor {

    private ItemHandler item;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        this.item = item;
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Mine Merchant", Bukkit.createInventory(null, 54, "Mine Merchant"));
        Inventory menu = skyblockPlayer.getInventory("Mine Merchant");

        ItemStack emptySpace = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());

        List<String> coalDescription = new ArrayList<>();
        coalDescription.add(ChatColor.DARK_GRAY + "Brewing Ingredient");
        coalDescription.add(ChatColor.GRAY + "increases the speed of");
        coalDescription.add(ChatColor.GRAY + "your minion by " + ChatColor.GREEN + "5%");
        coalDescription.add(ChatColor.GRAY + "for 30 minutes!");
        coalDescription.add("");
        coalDescription.add("" + ChatColor.WHITE + ChatColor.BOLD + "COMMON");
        coalDescription.add("");
        coalDescription.add(ChatColor.GRAY + "Cost");
        coalDescription.add(ChatColor.GOLD + "8 Coins");
        coalDescription.add("");
        coalDescription.add(ChatColor.YELLOW + "Click to trade!");
        coalDescription.add(ChatColor.YELLOW + "Right-Click for more trading options!");
        List<String> ironDescription = new ArrayList<>();
        ironDescription.add("" + ChatColor.WHITE + ChatColor.BOLD + "COMMON");
        ironDescription.add("");
        ironDescription.add(ChatColor.GRAY + "Cost");
        ironDescription.add(ChatColor.GOLD + "22 Coins");
        ironDescription.add("");
        ironDescription.add(ChatColor.YELLOW + "Click to trade!");
        ironDescription.add(ChatColor.YELLOW + "Right-Click for more trading options!");
        List<String> goldDescription = new ArrayList<>();
        goldDescription.add(ChatColor.DARK_GRAY + "Brewing Ingredient");
        goldDescription.add("" + ChatColor.WHITE + ChatColor.BOLD + "COMMON");
        goldDescription.add("");
        goldDescription.add(ChatColor.GRAY + "Cost");
        goldDescription.add(ChatColor.GOLD + "12 Coins");
        goldDescription.add("");
        goldDescription.add(ChatColor.YELLOW + "Click to trade!");
        goldDescription.add(ChatColor.YELLOW + "Right-Click for more trading options!");
        List<String> rookiePickDesc = new ArrayList<>();
        rookiePickDesc.add(ChatColor.DARK_GRAY + "Breaking Power 2");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+15");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.GRAY + "Mining Speed: " + ChatColor.GREEN + "+180");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.BLUE + "Efficiency I");
        rookiePickDesc.add(ChatColor.GRAY + "Grants " + ChatColor.GREEN + "+30 " +ChatColor.GOLD + "⸕ Mining Speed" + ChatColor.GRAY + ".");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        rookiePickDesc.add("" + ChatColor.WHITE + ChatColor.BOLD + "COMMON PICKAXE");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.GRAY + "Cost");
        rookiePickDesc.add(ChatColor.GOLD + "12 Coins");
        rookiePickDesc.add("");
        rookiePickDesc.add(ChatColor.YELLOW + "Click to trade!");

        ItemStack coal = item.createBasicItem(Material.COAL, ChatColor.WHITE + "Coal " + ChatColor.DARK_GRAY + "x2", coalDescription, (short) 0, false, 2);
        ItemStack iron = item.createBasicItem(Material.IRON_INGOT, ChatColor.WHITE + "Iron Ingot " + ChatColor.DARK_GRAY + "x4", ironDescription, (short) 0, false, 4);
        ItemStack gold = item.createBasicItem(Material.GOLD_INGOT, ChatColor.WHITE + "Gold Ingot" + ChatColor.DARK_GRAY + " x2", goldDescription, (short) 0, false, 2);
        ItemStack rookie_pickaxe = item.createBasicItem(Material.STONE_PICKAXE, ChatColor.WHITE + "Rookie Pickaxe", rookiePickDesc, (short) 0, true, 1);

        for(int i = 0; i < 9; i++){
            menu.setItem(menu.firstEmpty(), emptySpace);
        }

        menu.setItem(9, emptySpace);
        menu.setItem(10, coal);
        menu.setItem(11, iron);
        menu.setItem(12, gold);
        menu.setItem(13, rookie_pickaxe);

        player.openInventory(skyblockPlayer.getInventory("Mine Merchant"));
        return false;
    }
}
