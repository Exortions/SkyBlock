package com.itech4kids.skyblock.Commands.Items;

import com.itech4kids.skyblock.Enums.ReforgeTypes;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class ReforgeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "Please specify a reforge!");
        }else{
            if (sender instanceof Player){
                Player player = (Player) sender;

                if (player.getItemInHand() != null) {
                    ItemMeta itemMeta = player.getItemInHand().getItemMeta();
                    if (itemMeta.getLore() != null) {
                        for (String string : itemMeta.getLore()) {
                            if (string.equalsIgnoreCase(ChatColor.DARK_GRAY + "This item can be reforged!")) {
                                for (ReforgeTypes reforgeTypes : ReforgeTypes.values()){
                                    if (itemMeta.getDisplayName().contains(StringUtils.capitalize(reforgeTypes.name().toLowerCase().replaceAll("_", " ")))){
                                        itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll(itemMeta.getDisplayName().split(" ")[0] + " ", ""));
                                        break;
                                    }
                                }
                                itemMeta.setDisplayName(ChatColor.getLastColors(itemMeta.getDisplayName()) + StringUtils.capitalize(ReforgeTypes.valueOf(args[0].toUpperCase().replaceAll(" ", "_")).name().toLowerCase().replaceAll("_", " ")) + " " + itemMeta.getDisplayName());
                                player.getItemInHand().setItemMeta(itemMeta);
                                break;
                            }
                        }
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "You are holding air!");
                }
            }
        }

        return false;
    }
}
