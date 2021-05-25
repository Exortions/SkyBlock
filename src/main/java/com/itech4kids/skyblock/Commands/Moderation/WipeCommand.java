package com.itech4kids.skyblock.Commands.Moderation;

import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Util.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WipeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.isOp()) {
            if (args.length == 0) {
                sender.sendMessage(MessageConfig.specifyPlayer());
            } else {
                try {
                    Player cheater = Bukkit.getPlayer(args[0]);
                    cheater.sendMessage(MessageConfig.wipeMsg());
                    cheater.performCommand("warp hub");
                    IslandManager.deleteWorld(cheater);
                    if (sender instanceof Player) {
                        ((Player) sender).performCommand("ban " + cheater.getName() + " Boosting_detected_on_multiple_Skyblock_profiles_and_your_profiles_have_been_wiped.");
                    } else {
                        cheater.kickPlayer(ChatColor.RED + "Boosting detected on multiple Skyblock profiles");
                    }
                } catch (Exception e) {
                    sender.sendMessage(MessageConfig.notOnline());
                }
            }
        }

        return false;
    }
}
