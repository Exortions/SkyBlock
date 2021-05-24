package com.itech4kids.skyblock.Commands.AdminCommands.Gamemode;

import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageConfig.onlyPlayers());
            return true;
        }
        Player player = (Player) sender;
        player.performCommand("gamemode 1");
        return false;
    }
}