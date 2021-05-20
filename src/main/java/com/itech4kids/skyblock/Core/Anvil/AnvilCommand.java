package com.itech4kids.skyblock.Core.Anvil;

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

        ItemHandler.fill(inv, 1);
        ItemHandler.fill(inv, 2, 45, 53);
        inv.setItem(49, ItemHandler.createExitBarrier());

        player.openInventory(skyblockPlayer.getInventory("Anvil"));
        return false;
    }
}