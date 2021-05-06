package com.itech4kids.skyblock.Commands.Items;

import com.itech4kids.skyblock.Objects.Pets.SkyblockPetsItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PetsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("tiger")) {
                player.getInventory().addItem(new SkyblockPetsItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmM0MjYzODc0NDkyMmI1ZmNmNjJjZDliZjI3ZWVhYjkxYjJlNzJkNmM3MGU4NmNjNWFhMzg4Mzk5M2U5ZDg0In19fQ==", Integer.parseInt(args[1]), "Tiger", ChatColor.GOLD, ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", "Merciless Swipe", "§7Gain §c+"+ Math.round((0 + (0.15 * (Integer.parseInt(args[1]) -1)))) +" ⫽Ferocity", "Hemorrhage", "§7Melee Attacks reduce healing §7by §6" + Math.round((0 + (0.55 * (Integer.parseInt(args[1]) -1)))) +"% §7for §a10s", "Apex Predator", "§7Deal §c+"+ Math.round((0 + (0.2 * (Integer.parseInt(args[1]))))) +"% §7damage against §7targets with no other mobs §7within §a15 §7blocks", 5, 0, 0, null, null, null, null, 0, 0.1, 0.05, 0.5, 0.0, 0.0, 0.0, 0.0, 0.25));
            }else if (args[0].equalsIgnoreCase("dragon")){
                player.getInventory().addItem(new SkyblockPetsItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWVjM2ZmNTYzMjkwYjEzZmYzYmNjMzY4OThhZjdlYWE5ODhiNmNjMThkYzI1NDE0N2Y1ODM3NGFmZTliMjFiOSJ9fX0=", Integer.parseInt(args[1]),"Ender Dragon", ChatColor.GOLD, ChatColor.GOLD + "" + ChatColor.BOLD + "LEGENDARY", "End Strike", "§7Deal +§a" + Math.round((0 + (0.25 * (Integer.parseInt(args[1]))))) +"% §7more damage §7to end mobs.", "One with the Dragons", "§7Buffs the §6Aspect of the §6Dragons §7sword by §a" + Math.round((0 + (0.3 * (Integer.parseInt(args[1]))))) + " §7damage §7and §a" + Math.round((0 + (0.3 * (Integer.parseInt(args[1]))))) +" §cstrength.", "Superior", "§7Increases all stats by §a" + Math.round((0 + (0.1 * (Integer.parseInt(args[1]))))) + "%", 0, 0, 0, null, null, null, null, null, 0.5, 0.1, 0.5, 0.0 ,0.0 , 0.0, 0.0, 0.0));
            }
        }

        return false;
    }
}
