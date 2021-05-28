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
import java.util.Map;

public class ItemBrowserCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "[Skyblock] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if(args.length == 0) {
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
            skyblockPlayer.setInventory("Item Browser", Bukkit.createInventory(null, 36, "Item Browser"));
            Inventory menu = skyblockPlayer.getInventory("Item Browser");

            ItemStack emptySpace = ItemHandler.createEmptySpace();

            for (int index = 0; index < menu.getSize(); index++) {
                menu.setItem(index, emptySpace);
            }
            // Sword category
            List<String> swordCategoryLore = new ArrayList<>();
            swordCategoryLore.add(ChatColor.GRAY + "Click to view the");
            swordCategoryLore.add(ChatColor.GRAY + "Sword Category!");
            swordCategoryLore.add("");
            swordCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack swordCategoryItem = ItemHandler.createBasicItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "Swords", swordCategoryLore, (short) 0,false, 1);

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
            ItemStack materialCategoryItem = ItemHandler.createBasicItem(Material.DIAMOND_BLOCK, ChatColor.GREEN + "Material", materialCategoryLore, (short) 0,true, 1);

            // Tools category
            List<String> toolsCategoryLore = new ArrayList<>();
            toolsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            toolsCategoryLore.add(ChatColor.GRAY + "Tools Category!");
            toolsCategoryLore.add("");
            toolsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack toolsCategoryItem = ItemHandler.createBasicItem(Material.DIAMOND_PICKAXE, ChatColor.GREEN + "Tools", toolsCategoryLore, (short) 0,false, 1);

            // Tools category
            List<String> bowsCategoryLore = new ArrayList<>();
            bowsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            bowsCategoryLore.add(ChatColor.GRAY + "Bows Category!");
            bowsCategoryLore.add("");
            bowsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack bowsCategoryItem = ItemHandler.createBasicItem(Material.BOW, ChatColor.GREEN + "Bows", bowsCategoryLore, (short) 0,false, 1);

            List<String> petsCategoryLore = new ArrayList<>();
            petsCategoryLore.add(ChatColor.GRAY + "Click to view the");
            petsCategoryLore.add(ChatColor.GRAY + "Pets Category!");
            petsCategoryLore.add("");
            petsCategoryLore.add(ChatColor.YELLOW + "Click to view!");
            ItemStack petsCategoryItem = ItemHandler.createBasicHead(ChatColor.GREEN + "Pets", petsCategoryLore, 1, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmM0MjYzODc0NDkyMmI1ZmNmNjJjZDliZjI3ZWVhYjkxYjJlNzJkNmM3MGU4NmNjNWFhMzg4Mzk5M2U5ZDg0In19fQ==", 1);



            ItemStack search = new ItemStack(Material.SIGN);
            ItemHandler.setDisplayName(search, "&aSearch &7(Click)");
            ItemHandler.addAbililtyLore(search, "&7Click to search!");

            // Add categories to the item browser
            menu.setItem(10, swordCategoryItem);
            menu.setItem(11, helmetCategoryItem);
            menu.setItem(12, chestplateCategoryItem);
            menu.setItem(13,leggingsCategoryItem);
            menu.setItem(14,bootsCategoryItem);
            menu.setItem(15,materialCategoryItem);
            menu.setItem(16,toolsCategoryItem);
            menu.setItem(19,bowsCategoryItem);
            menu.setItem(20,petsCategoryItem);

            menu.setItem(31, search);

            player.openInventory(skyblockPlayer.getInventory("Item Browser"));
            return false;
        }else{
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
            skyblockPlayer.setInventory("Item Browser - Search", Bukkit.createInventory(null, 54, "Item Browser - Search"));
            Inventory menu = skyblockPlayer.getInventory("Item Browser - Search");

            ItemStack emptySpace = ItemHandler.createEmptySpace();

            for(int index = 0; index < 9; index++){
                menu.setItem(index, emptySpace);
            }

            menu.setItem(9, emptySpace);
            menu.setItem(17, emptySpace);
            menu.setItem(18, emptySpace);
            menu.setItem(26, emptySpace);
            menu.setItem(27, emptySpace);
            menu.setItem(36, emptySpace);
            menu.setItem(35, emptySpace);

            for (int i = 44; i < 54; ++i){
                menu.setItem(i, emptySpace);
            }

            String desc = "";
            for(int i = 0; i < args.length; i++){
                String arg;
                if (i == args.length - 1){
                    arg= args[i];
                }else{
                    arg= args[i] + " ";
                }
                desc = desc + arg;
            }

            for (Map<String, ItemStack> map : ItemHandler.maps){
                for (Map.Entry<String, ItemStack> entry : map.entrySet()){
                    if (entry.getKey().toLowerCase().replaceAll("_", " ").contains(desc)){
                        if (entry.getValue() != null){
                            menu.addItem(entry.getValue());
                        }
                    }
                }
            }

            player.openInventory(menu);
        }
        return false;
    }

}