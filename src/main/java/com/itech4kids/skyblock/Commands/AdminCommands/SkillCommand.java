package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SkillCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatColor.RED + "Please specify a skill name!");
        }else if (args.length == 1){
            sender.sendMessage(ChatColor.RED + "Please specify the amount!");
        }else{
            if (sender instanceof Player){
                Player player = (Player) sender;
                SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());

                try {
                    Config.setStatLvl(player, args[0], Integer.parseInt(args[1]));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendMessage(ChatColor.GREEN + "Success!");
            }
        }
        return false;
    }
}
