package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemCommand implements CommandExecutor {

    List<ItemStack> items = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String s, java.lang.String[] args) {
        for (Map.Entry<String, ItemStack> entry : ItemHandler.swordMap.entrySet()){
            items.add(entry.getValue());
        }

        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length > 1) {
            player.sendMessage(ChatColor.RED + "Incorrect usage!\n /item <item>");
            return true;
        } else if(args.length == 0){
            player.sendMessage(ChatColor.RED + "Incorrect usage!\n /item <item>");
            return true;
        } else if(args.length == 1){
            player.sendMessage(ChatColor.GREEN + args[0].toUpperCase());
            for(int i = 0; i < items.size(); i++){
                String old_item_name = ChatColor.stripColor(items.get(i).getItemMeta().getDisplayName().toUpperCase());
                String new_item_name = ChatColor.stripColor(old_item_name.replace(' ', '_'));
                if(args[0].toUpperCase().equals(new_item_name)){
                    player.getInventory().addItem(items.get(i));
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}