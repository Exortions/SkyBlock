package com.itech4kids.skyblock.Commands.Items;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemBrowserCommand implements CommandExecutor {

    private ItemHandler itemHandler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        this.itemHandler = itemHandler;
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "[Skyblock] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
            skyblockPlayer.setInventory("Item Browser", Bukkit.createInventory(null, 27, "Item Browser"));
            Inventory menu = skyblockPlayer.getInventory("Item Browser");

            ItemStack emptySpace = itemHandler.createEmptySpace();

            for (int index = 0; index < 27; index++) {
                menu.setItem(index, emptySpace);
            }
            // Sword category
            List<String> swordCategoryLore = new ArrayList<>();
            swordCategoryLore.add(ChatColor.GRAY + "Click to view the");
            swordCategoryLore.add(ChatColor.GRAY + "Sword Category!");
            swordCategoryLore.add("");
            swordCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack swordCategoryItem = itemHandler.createBasicItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Swords", swordCategoryLore, (short) 0,false, 1);

            // Helmet category
            List<String> helmetCategoryLore = new ArrayList<>();
            helmetCategoryLore.add(ChatColor.GRAY + "Click to view the");
            helmetCategoryLore.add(ChatColor.GRAY + "Helmet Category!");
            helmetCategoryLore.add("");
            helmetCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack helmetCategoryItem = ItemHandler.createBasicHead(ChatColor.GREEN + "Helmets", helmetCategoryLore, 1, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=", 1);

            // Chestplate category
            List<String> chestplateCategoryLore = new ArrayList<>();
            chestplateCategoryLore.add(ChatColor.GRAY + "Click to view the");
            chestplateCategoryLore.add(ChatColor.GRAY + "Chestplate Category!");
            chestplateCategoryLore.add("");
            chestplateCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack chestplateCategoryItem = ItemHandler.createBasicLeatherArmor(Material.LEATHER_CHESTPLATE, ChatColor.GREEN + "Chestplates", Color.fromRGB(242, 223, 17), chestplateCategoryLore, 1, 1);

            // Leggings category
            List<String> leggingsCategoryLore = new ArrayList<>();
            leggingsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            leggingsCategoryLore.add(ChatColor.GRAY + "Leggings Category!");
            leggingsCategoryLore.add("");
            leggingsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack leggingsCategoryItem = ItemHandler.createBasicLeatherArmor(Material.LEATHER_LEGGINGS, ChatColor.GREEN + "Leggings", Color.fromRGB(242, 223, 17), leggingsCategoryLore, 1, 1);

            // Boots category
            List<String> bootsCategoryLore = new ArrayList<>();
            bootsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            bootsCategoryLore.add(ChatColor.GRAY + "Boots Category!");
            bootsCategoryLore.add("");
            bootsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack bootsCategoryItem = ItemHandler.createBasicLeatherArmor(Material.LEATHER_BOOTS, ChatColor.GREEN + "Boots", Color.fromRGB(242, 93, 24), bootsCategoryLore, 1, 1);

            // Material category
            List<String> materialCategoryLore = new ArrayList<>();
            materialCategoryLore.add(ChatColor.GRAY + "Click to view the");
            materialCategoryLore.add(ChatColor.GRAY + "Materials Category!");
            materialCategoryLore.add("");
            materialCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack materialCategoryItem = itemHandler.createBasicItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Material", materialCategoryLore, (short) 0,true, 1);

            // Tools category
            List<String> toolsCategoryLore = new ArrayList<>();
            toolsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            toolsCategoryLore.add(ChatColor.GRAY + "Tools Category!");
            toolsCategoryLore.add("");
            toolsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack toolsCategoryItem = itemHandler.createBasicItem(Material.DIAMOND_PICKAXE, ChatColor.GREEN + "Tools", materialCategoryLore, (short) 0,false, 1);


            // Add categories to the item browser
            menu.setItem(10, swordCategoryItem);
            menu.setItem(11, helmetCategoryItem);
            menu.setItem(12, chestplateCategoryItem);
            menu.setItem(13,leggingsCategoryItem);
            menu.setItem(14,bootsCategoryItem);
            menu.setItem(15,materialCategoryItem);
            menu.setItem(16,toolsCategoryItem);

            player.openInventory(skyblockPlayer.getInventory("Item Browser"));
            return false;
        }
        return false;
    }

}