package com.itech4kids.skyblock.Mechanics.Crafting;

import com.itech4kids.skyblock.Objects.Inventories.CraftInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.openInventory(new CraftInventory());
        }
        return false;
    }
}