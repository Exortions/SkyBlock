package com.itech4kids.skyblock.Commands.PlayerCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.CraftInventory;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.minecraft.server.v1_8_R3.CraftingManager;
import net.minecraft.server.v1_8_R3.IRecipe;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WorkBenchCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.openInventory(new CraftInventory());
            for (IRecipe recipe : CraftingManager.getInstance().getRecipes()){
                
            }
        }
        return false;
    }
}
