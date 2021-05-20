package com.itech4kids.skyblock.Commands.AdminCommands;

import com.itech4kids.skyblock.CustomMobs.Dragon.SkyblockDragon;
import com.itech4kids.skyblock.CustomMobs.Dragon.SkyblockDragonType;
import com.itech4kids.skyblock.CustomMobs.Enderman.SkyblockEnderman;
import com.itech4kids.skyblock.CustomMobs.Enderman.SkyblockEndermanType;
import com.itech4kids.skyblock.CustomMobs.PlayerEntities.CustomAI;
import com.itech4kids.skyblock.CustomMobs.PlayerEntities.JERRY;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.RevenantBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.SvenBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.TarantulaBoss;
import com.itech4kids.skyblock.CustomMobs.Spider.SkyblockSpider;
import com.itech4kids.skyblock.CustomMobs.Spider.SkyblockSpiderType;
import com.itech4kids.skyblock.CustomMobs.Wolf.SkyblockWolf;
import com.itech4kids.skyblock.CustomMobs.Wolf.SkyblockWolfType;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombie;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombieType;
import com.itech4kids.skyblock.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnCustomMobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            World world = ((CraftWorld) player.getWorld()).getHandle();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            Main.registerEntity("Dragon", 63, SkyblockDragon.class);
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Please enter a mob type!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else if (args.length == 1){
                player.sendMessage(ChatColor.RED + "Please enter a mob name!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else{
                switch (args[0].toLowerCase()) {
                    case "zombie":
                        SkyblockZombie skyblockZombie = new SkyblockZombie(SkyblockZombieType.valueOf(args[1].toUpperCase()), player.getLocation());
                        break;
                    case "dragon":
                        SkyblockDragon skyblockDragon = new SkyblockDragon(SkyblockDragonType.valueOf(args[1].toUpperCase()), world);
                        skyblockDragon.enderTeleportTo(x, y, z);
                        world.addEntity(skyblockDragon);
                        break;
                    case "enderman":
                        SkyblockEnderman skyblockEnderman = new SkyblockEnderman(SkyblockEndermanType.valueOf(args[1].toUpperCase()), player.getLocation());
                        break;
                    case "spider":
                        SkyblockSpider skyblockSpider = new SkyblockSpider(SkyblockSpiderType.valueOf(args[1].toUpperCase()), player.getLocation());
                        skyblockSpider.getVanillaEntity().teleport(player.getLocation());
                        break;
                    case "wolf":
                        SkyblockWolf skyblockWolf = new SkyblockWolf(SkyblockWolfType.valueOf(args[1].toUpperCase()), player.getLocation());
                        skyblockWolf.getVanillaEntity().teleport(player.getLocation());
                        break;
                    case "slayer":
                        switch (args[1].toLowerCase()){
                            case "revenant":
                                RevenantBoss revenant = new RevenantBoss(player, Integer.parseInt(args[2]));
                                revenant.summon(player.getLocation());
                                break;
                            case "sven":
                                SvenBoss sven = new SvenBoss(player, Integer.parseInt(args[2]));
                                sven.summon(player.getLocation());
                                break;
                            case "tarantula":
                                TarantulaBoss tarantula = new TarantulaBoss(player, Integer.parseInt(args[2]));
                                tarantula.summon(player.getLocation());
                                break;
                        }
                        break;
                    case "yeti":
                        player.sendMessage(ChatColor.GREEN + "What is this creature!?");
                        for (int i = 0; i < Integer.parseInt(args[1]); ++i) {
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format(1 * 2000000) + ChatColor.RED + "❤");
                            npc.spawn(player.getLocation());
                            npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                            ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                            healthDisplay.setCustomNameVisible(true);
                            healthDisplay.setGravity(false);
                            healthDisplay.setVisible(false);
                            healthDisplay.setSmall(true);
                            healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl "  + 175 + ChatColor.DARK_GRAY + "] " + npc.getName());
                            npc.setProtected(false);
                            CustomAI.yetiAI(npc, healthDisplay);
                        }
                        break;
                    case "frozen_steve":
                        player.sendMessage(ChatColor.GREEN + "Frozen Steve fell into the pond long ago, never to resurface...until now!");
                        for (int i = 0; i < Integer.parseInt(args[1]); ++i) {
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Frozen Steve " + ChatColor.GREEN + Main.format(1 * 700) + ChatColor.RED + "❤");
                            npc.spawn(player.getLocation());
                            npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                            ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                            healthDisplay.setCustomNameVisible(true);
                            healthDisplay.setGravity(false);
                            healthDisplay.setVisible(false);
                            healthDisplay.setSmall(true);
                            healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl "  + 7 + ChatColor.DARK_GRAY + "] " + npc.getName());
                            npc.setProtected(false);
                            CustomAI.frozenSteveAI(npc, healthDisplay);
                        }
                        break;
                    case "jerry":
                        break;
                    }
                }
            }
        return false;
    }
}
