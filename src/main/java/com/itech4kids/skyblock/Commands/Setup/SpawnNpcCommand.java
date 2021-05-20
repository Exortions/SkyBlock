package com.itech4kids.skyblock.Commands.Setup;

import com.itech4kids.skyblock.Main;
import de.tr7zw.nbtapi.NBTEntity;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SpawnNpcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage(ChatColor.RED + "Invalid arguments!");
            }else{
                NPC npc;
                ArmorStand armorStand;
                NBTEntity nbtas;
                ArmorStand click;
                NBTEntity nbtEntity;
                LookClose lookClose;
                SkinTrait skinTrait;
                switch (args[0].toLowerCase()){
                    case "miner_merchant":
                        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
                        npc.spawn(player.getLocation());
                        npc.getEntity().setMetadata("minerMerchant", new FixedMetadataValue(Main.getMain(), true));

                        armorStand = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.15, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                        armorStand.setGravity(false);
                        armorStand.setVisible(false);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName("Miner Merchant");
                        nbtas = new NBTEntity(armorStand);
                        nbtas.setBoolean("Invisible", Boolean.valueOf(true));
                        nbtas.setBoolean("Gravity", Boolean.valueOf(false));
                        nbtas.setBoolean("CustomNameVisible", Boolean.valueOf(true));
                        nbtas.setBoolean("Marker", Boolean.valueOf(true));
                        nbtas.setBoolean("Invulnerable", Boolean.valueOf(true));

                        armorStand.teleport(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.15, npc.getEntity().getLocation().getZ()));
                        armorStand.setMetadata("minerMerchant", new FixedMetadataValue(Main.getMain(), true));
                        armorStand.setMetadata("NPC", new FixedMetadataValue(Main.getMain(), true));

                        click = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.10, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                        click.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
                        nbtEntity = new NBTEntity(click);
                        nbtEntity.setBoolean("Invisible", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Gravity", Boolean.valueOf(false));
                        nbtEntity.setBoolean("CustomNameVisible", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Marker", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Invulnerable", Boolean.valueOf(true));
                        click.teleport(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.10, npc.getEntity().getLocation().getZ()));

                        lookClose = npc.getTrait(LookClose.class);
                        lookClose.lookClose(true);

                        skinTrait = npc.getTrait(SkinTrait.class);
                        skinTrait.setSkinName("akhunta");

                        npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);

                        npc.addTrait(lookClose);
                        npc.addTrait(skinTrait);
                        break;
                    case "farmer_merchant":
                        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
                        npc.spawn(player.getLocation());
                        npc.getEntity().setMetadata("farmerMerchant", new FixedMetadataValue(Main.getMain(), true));

                        armorStand = npc.getEntity().getWorld().spawn(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.15, npc.getEntity().getLocation().getZ()), ArmorStand.class);
                        armorStand.setGravity(false);
                        armorStand.setVisible(false);
                        armorStand.setCustomNameVisible(true);
                        armorStand.setCustomName("Farmer Merchant");
                        nbtas = new NBTEntity(armorStand);
                        nbtas.setBoolean("Invisible", Boolean.valueOf(true));
                        nbtas.setBoolean("Gravity", Boolean.valueOf(false));
                        nbtas.setBoolean("CustomNameVisible", Boolean.valueOf(true));
                        nbtas.setBoolean("Marker", Boolean.valueOf(true));
                        nbtas.setBoolean("Invulnerable", Boolean.valueOf(true));

                        armorStand.teleport(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 2.15, npc.getEntity().getLocation().getZ()));
                        armorStand.setMetadata("farmerMerchant", new FixedMetadataValue(Main.getMain(), true));
                        armorStand.setMetadata("NPC", new FixedMetadataValue(Main.getMain(), true));

                        click = npc.getEntity().getWorld().spawn(npc.getEntity().getLocation(), ArmorStand.class);
                        click.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "CLICK");
                        nbtEntity = new NBTEntity(click);
                        nbtEntity.setBoolean("Invisible", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Gravity", Boolean.valueOf(false));
                        nbtEntity.setBoolean("CustomNameVisible", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Marker", Boolean.valueOf(true));
                        nbtEntity.setBoolean("Invulnerable", Boolean.valueOf(true));

                        lookClose = npc.getTrait(LookClose.class);
                        lookClose.lookClose(true);

                        skinTrait = npc.getTrait(SkinTrait.class);
                        skinTrait.setSkinName("OptimusChen");

                        npc.data().set(NPC.NAMEPLATE_VISIBLE_METADATA, false);

                        npc.addTrait(lookClose);
                        npc.addTrait(skinTrait);
                        break;
                    case "armor_merchant":
                        break;
                }
            }

        }
        return false;
    }
}
