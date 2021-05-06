package com.itech4kids.skyblock.Commands;

import com.itech4kids.skyblock.Enums.MerchantType;
import com.itech4kids.skyblock.Main;
import com.mojang.authlib.GameProfile;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.ArmorStandTrait;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.LazyMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class CreateSkyblockNPCCommand  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            World world = ((CraftWorld) player.getWorld()).getHandle();
            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Please enter a mob type!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else if (args.length == 1){
                player.sendMessage(ChatColor.RED + "Please enter a mob name!");
                player.sendMessage(ChatColor.RED + "/spawncm <mob type> <mob name>");
            }else{
                switch (args[0].toLowerCase()) {
                    case "merchant_miner":
                        for (int i = 0; i < Integer.valueOf(args[1]); ++i) {
                            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
                            NPC npcName = CitizensAPI.getNPCRegistry().createNPC(EntityType.ARMOR_STAND, ChatColor.WHITE + "Mine Merchant");
                            npc.spawn(player.getLocation());
                            npcName.spawn(new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 0.2, player.getLocation().getZ()));
                            npc.setProtected(true);
                            npcName.setProtected(true);
                            EntityPlayer entityPlayer = ((CraftPlayer) npc.getEntity()).getHandle();
                            ArmorStand npcNameStand = (ArmorStand) npcName.getEntity();
                            npcNameStand.setGravity(false);
                            npcNameStand.setVisible(false);

                            SkinTrait skinTrait = npc.getTrait(SkinTrait.class);
                            System.out.println(skinTrait.getSkinName());
                            skinTrait.setSkinName("akhunta");
                            LookClose lookClose = npc.getTrait(LookClose.class);
                            lookClose.lookClose(true);
                            ArmorStandTrait standTrait = npcName.getTrait(ArmorStandTrait.class);
                            standTrait.setGravity(false);
                            standTrait.setVisible(false);

                            Main.getMain().minerSkin = skinTrait;

//                            setMetadata(npc.getEntity(), "merchantid", "1", Main.getMain());
//                            System.out.println(npc.getEntity().getMetadata("merchantid"));

                            // 1 = Miner
                            // 2 = Weaponsmith
                            // 3 = Armorsmith

                            npc.addTrait(skinTrait);
                            npc.addTrait(lookClose);
                            npcName.addTrait(standTrait);

                            ((CraftPlayer) npc.getEntity()).setHealth(10);
                            ((CraftPlayer) npc.getEntity()).setMaxHealth(10);
                            break;
                        }
                }
            }
        }
        return false;
    }
}