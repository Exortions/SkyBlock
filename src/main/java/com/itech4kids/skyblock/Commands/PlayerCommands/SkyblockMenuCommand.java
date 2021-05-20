package com.itech4kids.skyblock.Commands.PlayerCommands;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockGuiItem;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkyblockMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
            skyblockPlayer.setInventory("Skyblock Menu", Bukkit.createInventory(null, 54, "Skyblock Menu"));
            Inventory menu = skyblockPlayer.getInventory("Skyblock Menu");
            ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
            ItemMeta itemMeta = space1.getItemMeta();
            itemMeta.setDisplayName(" ");
            space1.setItemMeta(itemMeta);

            for (int i = 0; i < 54; ++i){
                menu.setItem(i, space1);
            }

            ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwner(player.getName());
            skull.setItemMeta(skullMeta);
            ArrayList<String> lore = new ArrayList<String>();
            skullMeta.setDisplayName(ChatColor.GREEN + "Your Skyblock Stats");
            lore.add(ChatColor.RED + "❤ Health " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.HEALTH) + " HP");
            lore.add(ChatColor.GREEN + "❈ Defense " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.DEFENSE));
            lore.add(ChatColor.RED + "❁ Strength " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.STRENGTH));
            lore.add(ChatColor.WHITE + "✦ Speed " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.SPEED));
            lore.add(ChatColor.BLUE + "☣ Crit Chance " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE) + "%");
            lore.add(ChatColor.BLUE + "☠ Crit Damage " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE) + "%");
            lore.add(ChatColor.AQUA + "✎ Intelligence " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.MANA));
            lore.add(ChatColor.YELLOW + "⚔ Attack Speed " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.ATTACK_SPEED) + "%");
            lore.add(ChatColor.DARK_AQUA + "∞ Sea Creature Chance " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.SEA_CREATURE_CHANCE) + "%");
            lore.add(ChatColor.AQUA + "✯ Magic Find " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.MAGIC_FIND));
            lore.add(ChatColor.LIGHT_PURPLE + "♣ Pet Luck " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.PET_LUCK));
            lore.add(ChatColor.WHITE + "❂ True Defense " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.TRUE_DEFENSE));
            lore.add(ChatColor.RED + "⫽ Ferocity " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.FEROCITY));
            lore.add(ChatColor.RED + "✹ Ability Damage " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + "%");
            lore.add(ChatColor.GOLD + "☘ Mining Fortune " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.MINING_FORTUNE));
            lore.add(ChatColor.GOLD + "⸕ Mining Speed " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.MINING_SPEED));
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to view!");
            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);

            ArrayList<String> skillsLore = new ArrayList<String>();
            skillsLore.add("§6" + Main.getMain().getSkillAverage(player) + " Skill Avg §8(non-cosmetic)");
            SkyblockGuiItem skillsItem = new SkyblockGuiItem(ChatColor.GREEN + "Your Skills", "View your skill progression and rewards.", skillsLore, Material.DIAMOND_SWORD);
            SkyblockGuiItem collectionsItem = new SkyblockGuiItem(ChatColor.GREEN + "Collections", "View all of the items available in Skyblock. Collect more of an item to unlock rewards on your way to becoming a master of Skyblock!", null, Material.PAINTING);
            SkyblockGuiItem recipieBook = new SkyblockGuiItem(ChatColor.GREEN + "Recipe Book", "Through your adventure, you will unlock recipes for all kinds of special items! You can view how to craft these items here.", null, Material.BOOK);
            SkyblockGuiItem tradesItem = new SkyblockGuiItem(ChatColor.GREEN + "Trades", "View your available trades. These trades are always available and accessible through the Skyblock Menu.", null, Material.EMERALD);
            SkyblockGuiItem questLog = new SkyblockGuiItem(ChatColor.GREEN + "Quest Log", "View your active quests, progress, and rewards", null, Material.BOOK_AND_QUILL);
            SkyblockGuiItem calendar = new SkyblockGuiItem(ChatColor.GREEN + "Calendar and Events", "View the Skyblock Calendar, upcoming events, and event rewards!", null, Material.WATCH);
            SkyblockGuiItem storage = new SkyblockGuiItem(ChatColor.GREEN + "Storage", "Store global items that you want to access at any time from anywhere here.", null, Material.CHEST);
            ArrayList<String> affectsAddLore = new ArrayList<String>();
            affectsAddLore.add(ChatColor.GRAY + "Drink Potions or splash them on the ground to buff yourself!");
            affectsAddLore.add(" ");
            affectsAddLore.add(ChatColor.GRAY + "Active Affects: " + "§e" + player.getActivePotionEffects().size());
            SkyblockGuiItem activeAffects = new SkyblockGuiItem(ChatColor.GREEN + "Active Effects", "View and manage all of your active potion effects.", affectsAddLore, Material.POTION);
            ArrayList<String> petsLore = new ArrayList<>();
            petsLore.add(ChatColor.GRAY + "Level up your pets faster by gaining xp in their favourite skill!");
            //petsLore.add(" ");
            //petsLore.add(ChatColor.GRAY + "Selected pet: " + ChatColor.getLastColors(Config.getActivePet(player).getItemMeta().getDisplayName()) + Config.getActivePet(player).getItemMeta().getDisplayName().split(" ")[2]);
            SkyblockGuiItem petsItem = new SkyblockGuiItem(ChatColor.GREEN + "Pets", "View and manage all of your Pets.", petsLore, Material.BONE);
            SkyblockGuiItem workbenchItem = new SkyblockGuiItem(ChatColor.GREEN + "Crafting Table", "Opens the crafting grid", null, Material.WORKBENCH);

            ItemStack wardrobeItem = new ItemStack(Material.LEATHER_CHESTPLATE);
            LeatherArmorMeta wardrobeMeta = (LeatherArmorMeta) wardrobeItem.getItemMeta();
            List<String> wardrobeLore = new ArrayList<>();
            wardrobeMeta.setDisplayName(ChatColor.GREEN + "Wardrobe");
            wardrobeMeta.setColor(Color.PURPLE);
            wardrobeLore.add(ChatColor.GRAY + "Store armor set and quickly");
            wardrobeLore.add(ChatColor.GRAY + "swap between them!");
            wardrobeLore.add(" ");
            wardrobeLore.add(ChatColor.YELLOW + "Click to view!");
            wardrobeMeta.setLore(wardrobeLore);
            wardrobeItem.setItemMeta(wardrobeMeta);

            SkyblockGuiItem fastTravel = new SkyblockGuiItem(ChatColor.AQUA  + "Fast Travel", "Teleport to islands you've already visited.", null, Material.SKULL_ITEM);
            if (fastTravel.getType().equals(Material.SKULL_ITEM)) {
                SkullMeta skullMeta1 = (SkullMeta) fastTravel.itemMeta;
                skullMeta1.setOwner("earth");
                fastTravel.setItemMeta(skullMeta1);
            }

            ArrayList<String> profilesLore = new ArrayList<>();
            petsLore.add(ChatColor.GRAY + "Each profile has its own island, inventory, quest log...");
            SkyblockGuiItem profiles = new SkyblockGuiItem(ChatColor.GREEN + "Profile Management", "You can have multiple Skyblock Profiles at the same time", profilesLore, Material.NAME_TAG);

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.RED + "Close");
            close.setItemMeta(closeMeta);

            SkyblockGuiItem settingsItem = new SkyblockGuiItem(ChatColor.GREEN + "Settings", "View and edit your Skyblock settings.", null, Material.REDSTONE_TORCH_ON);

            menu.setItem(13, skull);
            menu.setItem(19, skillsItem);
            menu.setItem(20, collectionsItem);
            menu.setItem(21, recipieBook);
            menu.setItem(22, tradesItem);
            menu.setItem(23, questLog);
            menu.setItem(24, calendar);
            menu.setItem(25, storage);
            menu.setItem(29, activeAffects);
            menu.setItem(30, petsItem);
            menu.setItem(31, workbenchItem);
            menu.setItem(32, wardrobeItem);
            menu.setItem(47, fastTravel);
            menu.setItem(48, profiles);
            menu.setItem(49, close);
            menu.setItem(50, settingsItem);

            player.openInventory(skyblockPlayer.getInventory("Skyblock Menu"));

        }
        return false;
    }
}
