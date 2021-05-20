package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.Objects.Potions.SPot;
import com.itech4kids.skyblock.Objects.Potions.SPotType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PotionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        SPot sPot = new SPot(SPotType.valueOf(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Player player = (Player) sender;
        player.getInventory().addItem(sPot.getItem());
        return false;
    }
}
