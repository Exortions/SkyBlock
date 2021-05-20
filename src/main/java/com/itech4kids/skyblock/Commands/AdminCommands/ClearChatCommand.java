package com.itech4kids.skyblock.Commands.AdminCommands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClearChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        for (int i = 0; i < 100; ++i){
            Bukkit.broadcastMessage(" ");
        }
        return false;
    }
}
