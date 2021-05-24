package com.itech4kids.skyblock.Mechanics.Crafting;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.CraftInventory;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
        skyblockPlayer.setInventory("Craft Item", Bukkit.createInventory(null, 54, "Craft Item"));
        Inventory inv = skyblockPlayer.getInventory("Craft Item");
        // Crafting grid slots: 10, 11, 12, 19, 20, 21, 28, 29, 30 Result Slot: 23

        ItemStack air = ItemHandler.createAir();
        ItemStack recipeRequired = createRecipeRequired();

        ItemHandler.fill(inv, 1);
        ItemHandler.fill(inv, 2, 45, 54);

        inv.setItem(10, air);
        inv.setItem(11, air);
        inv.setItem(12, air);
        inv.setItem(19, air);
        inv.setItem(20, air);
        inv.setItem(21, air);
        inv.setItem(28, air);
        inv.setItem(29, air);
        inv.setItem(30, air);

        player.openInventory(skyblockPlayer.getInventory("Craft Item"));
        return false;
    }

    public ItemStack createRecipeRequired(){
        ItemStack tmp = new ItemStack(Material.BARRIER);
        ItemMeta meta = tmp.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.RED + "Recipe Required");
        tmp.setItemMeta(meta);
        return tmp;
    }
}