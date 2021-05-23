package com.itech4kids.skyblock.Mechanics.Anvil;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Anvil", Bukkit.createInventory(null, 54, "Anvil"));
        Inventory inv = skyblockPlayer.getInventory("Anvil");

        // Registering items
        ItemStack grayEmpty = AnvilInventory.createAnvilItems(0);
        ItemStack redEmpty = AnvilInventory.createAnvilItems(1);
        ItemStack greenEmpty = AnvilInventory.createAnvilItems(2);
        ItemStack itemToUpgrade = AnvilInventory.createAnvilItems(3);
        ItemStack itemToSacrifice = AnvilInventory.createAnvilItems(4);
        ItemStack anvilBarrier = AnvilInventory.createAnvilItems(5);
        ItemStack anvilItem = AnvilInventory.createAnvilItems(6);
        ItemStack error = AnvilInventory.createAnvilItems(7);
        ItemStack greenItemToUpgrade = AnvilInventory.createAnvilItems(8);
        ItemStack greenItemToSacrifice = AnvilInventory.createAnvilItems(9);
        ItemStack exit = AnvilInventory.createAnvilItems(10);
        ItemStack air = ItemHandler.createAir();

        // Background of menu
        ItemHandler.fill(inv, 1, 0, 45);
        ItemHandler.fill(inv, 2, 45, 54);

        // Adding items
        inv.setItem(11, itemToUpgrade);
        inv.setItem(12, itemToUpgrade);
        inv.setItem(20, itemToUpgrade);
        inv.setItem(29, air);
        inv.setItem(13, anvilBarrier);
        inv.setItem(14, itemToSacrifice);
        inv.setItem(15, itemToSacrifice);
        inv.setItem(24, itemToSacrifice);
        inv.setItem(33, air);
        inv.setItem(22, anvilItem);
        inv.setItem(49, exit);

        player.openInventory(skyblockPlayer.getInventory("Anvil"));
        return false;
    }
}