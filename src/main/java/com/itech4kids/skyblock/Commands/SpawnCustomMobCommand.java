package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.CustomMobs.Dragon.SkyblockDragon;
import com.itech4kids.skyblock.CustomMobs.Dragon.SkyblockDragonType;
import com.itech4kids.skyblock.CustomMobs.Enderman.SkyblockEnderman;
import com.itech4kids.skyblock.CustomMobs.Enderman.SkyblockEndermanType;
import com.itech4kids.skyblock.CustomMobs.PlayerEntities.CustomAI;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombie;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombieType;
import com.itech4kids.skyblock.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpawnCustomMobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            World world = ((CraftWorld) player.getWorld()).getHandle();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            Main.registerEntity("Enderman", 58, SkyblockEnderman.class);
            Main.registerEntity("Dragon", 63, SkyblockDragon.class);
            Main.registerEntity("Zombie", 54, SkyblockZombie.class);
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Please enter a mob type!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else if (args.length == 1){
                player.sendMessage(ChatColor.RED + "Please enter a mob name!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else{
                switch (args[0].toLowerCase()) {
                    case "zombie":
                        SkyblockZombie skyblockZombie = new SkyblockZombie(SkyblockZombieType.valueOf(args[1].toUpperCase()), world);
                        skyblockZombie.enderTeleportTo(x, y, z);
                        world.addEntity(skyblockZombie);
                        break;
                    case "dragon":
                        SkyblockDragon skyblockDragon = new SkyblockDragon(SkyblockDragonType.valueOf(args[1].toUpperCase()), world);
                        skyblockDragon.enderTeleportTo(x, y, z);
                        world.addEntity(skyblockDragon);
                        break;
                    case "enderman":
                        SkyblockEnderman skyblockEnderman = new SkyblockEnderman(SkyblockEndermanType.valueOf(args[1].toUpperCase()), world);
                        skyblockEnderman.enderTeleportTo(x, y, z);
                        world.addEntity(skyblockEnderman);
                        break;
                    case "yeti":
                        player.sendMessage(ChatColor.GREEN + "What is this creature!?");
                        for (int i = 0; i < Integer.valueOf(args[1]); ++i) {
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format(1 * 2000000) + ChatColor.RED + "❤");
                            npc.spawn(player.getLocation());
                            npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                            ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                            healthDisplay.setCustomNameVisible(true);
                            healthDisplay.setGravity(false);
                            healthDisplay.setVisible(false);
                            healthDisplay.setSmall(true);
                            healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl"  + 175 + ChatColor.DARK_GRAY + "] " + npc.getName());
                            npc.setProtected(false);
                            CustomAI.yetiAI(npc, healthDisplay);
                        }
                        break;
                    case "frozen_steve":
                        player.sendMessage(ChatColor.GREEN + "Frozen Steve fell into the pond long ago, never to resurface...until now!");
                        for (int i = 0; i < Integer.valueOf(args[1]); ++i) {
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Frozen Steve " + ChatColor.GREEN + Main.format(1 * 700) + ChatColor.RED + "❤");
                            npc.spawn(player.getLocation());
                            npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                            ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                            healthDisplay.setCustomNameVisible(true);
                            healthDisplay.setGravity(false);
                            healthDisplay.setVisible(false);
                            healthDisplay.setSmall(true);
                            healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl"  + 7 + ChatColor.DARK_GRAY + "] " + npc.getName());
                            npc.setProtected(false);
                            CustomAI.frozenSteveAI(npc, healthDisplay);
                        }
                        break;
                    case "crypt_undead":
                        for(int i = 0; i < Integer.valueOf(args[1]); i++){
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.RED + "Crypt Undead " + ChatColor.GREEN + Main.format(1 * 45000) + ChatColor.RED + "❤");
                            npc.spawn(player.getLocation());
                            npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);
                            ArmorStand healthDisplay = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                            healthDisplay.setCustomNameVisible(true);
                            healthDisplay.setGravity(false);
                            healthDisplay.setVisible(false);
                            healthDisplay.setSmall(true);
                            healthDisplay.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl"  + 7 + ChatColor.DARK_GRAY + "] " + npc.getName());
                            Equipment equipment = npc.getTrait(Equipment.class);
                            equipment.set(Equipment.EquipmentSlot.HAND, new ItemStack(Material.BONE));
                            npc.setProtected(false);
                            npc.addTrait(equipment);
                            CustomAI.cryptUndeadAI(npc, healthDisplay);
                        }
                }
            }
        }
        return false;
    }
}
