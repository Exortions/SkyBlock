package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.SkillLevelsInventory;
import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockGuiItem;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockSkillGuiItem;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockStatItem;
import com.itech4kids.skyblock.Objects.Pets.PetEvent.SkyblockPetEquipEvent;
import com.itech4kids.skyblock.Objects.Pets.PetEvent.SkyblockPetUnequipEvent;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPet;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPetGuiItem;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SkyblockMenuListener implements Listener {

    public Main main;

    public SkyblockMenuListener(Main main){
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        for (Map.Entry<String, Inventory> entry : skyblockPlayer.getInventories().entrySet()){
            if (e.getClickedInventory().equals(entry.getValue())){
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Close")){
                    player.closeInventory();
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Go Back")) {
                    player.openInventory(skyblockPlayer.lastInventory);
                }
            }
        }
    }

    @EventHandler
    public void onSkyblockMenuClick(InventoryClickEvent e) throws IOException {
        Player player = (Player) e.getWhoClicked();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        Inventory menu;
        if (e.getView().getTitle().equalsIgnoreCase("Skyblock Menu")){
            e.setCancelled(true);
            skyblockPlayer.lastInventory = e.getClickedInventory();
            ItemStack goback = new ItemStack(Material.ARROW);
            ItemMeta gobackmeta = goback.getItemMeta();
            gobackmeta.setDisplayName(ChatColor.GREEN + "Go Back");
            goback.setItemMeta(gobackmeta);

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.RED + "Close");

            ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
            ItemMeta itemMeta = space1.getItemMeta();
            itemMeta.setDisplayName(" ");
            space1.setItemMeta(itemMeta);
            close.setItemMeta(closeMeta);
            switch (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase())){
                case "your skyblock stats":
                    skyblockPlayer.setInventory("Your Skyblock Profile", Bukkit.createInventory(null, 54, "Your Skyblock Profile"));
                    menu = skyblockPlayer.getInventory("Your Skyblock Profile");

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
                    lore.add(ChatColor.GREEN + "❈ Defence " + ChatColor.WHITE + skyblockPlayer.getStat(SkyblockStats.DEFENSE));
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
                    skullMeta.setLore(lore);
                    skull.setItemMeta(skullMeta);

                    SkyblockStatItem health = new SkyblockStatItem(SkyblockStats.HEALTH, skyblockPlayer, ChatColor.RED + "❤ Health", "Health is your total maximum health. Your natural regeneration gives XX HP every 2s.", "Increase your base Health by leveling your Farming and Fishing skills and contributing to the Fairy in the Wilderness.", 322, 0);
                    SkyblockStatItem defense = new SkyblockStatItem(SkyblockStats.DEFENSE, skyblockPlayer, ChatColor.GREEN + "❈ Defense", "Defense reduces the damage that you take from enemies", "Increase your base defense by leveling your Mining skill and contributing to the Fairy in the Wilderness.",  307, 0);
                    SkyblockStatItem strength = new SkyblockStatItem(SkyblockStats.STRENGTH, skyblockPlayer, ChatColor.RED + "❁ Strength", "Strength increases your base melee damage, including punching and weapons", "Increase your base strength by leveling your Foraging skill and contributing to the Fairy in the Wilderness.", 377, 0);
                    SkyblockStatItem speed = new SkyblockStatItem(SkyblockStats.SPEED, skyblockPlayer, ChatColor.WHITE + "✦ Speed", "Speed increases your walk speed", "Increase your base speed by contributing to the Fairy in the Wilderness.", 353, 0);
                    SkyblockStatItem critChance = new SkyblockStatItem(SkyblockStats.CRIT_CHANCE, skyblockPlayer, ChatColor.BLUE + "☣ Crit Chance", "Crit Chance is your chance to deal extra damage on enemies", "Increase your base crit chance by leveling your Combat skill.", 397, 3);
                    SkyblockStatItem critDamage = new SkyblockStatItem(SkyblockStats.CRIT_DAMAGE, skyblockPlayer, ChatColor.BLUE + "☠ Crit Damage", "Crit Damage is the amount of extra damage you deal when landing a critical hit", "Increase your base crit damage by exploring the world", 397, 3);
                    SkyblockStatItem mana = new SkyblockStatItem(SkyblockStats.MANA, skyblockPlayer, ChatColor.AQUA + "✎ Intelligence", "Intelligence increases both your mana pool and the damage of your magical items.", "Increate your base intelligence by leveling your Enchanting and Alchemy skills.", 403, 0);
                    SkyblockStatItem miningSpeed = new SkyblockStatItem(SkyblockStats.MINING_SPEED, skyblockPlayer, ChatColor.GOLD + "⸕ Mining Speed", "Increases the speed of breaking mining blocks.", "Increase your base mining speed by exploring the world!", 278, 0);
                    SkyblockStatItem attackSpeed = new SkyblockStatItem(SkyblockStats.ATTACK_SPEED, skyblockPlayer, ChatColor.YELLOW + "⚔ Bonus Attack Speed", "Bonus Attack Speed decreases the time between hits on your opponent", "All players have the same bonus attack speed", 286,0 );
                    SkyblockStatItem seaCreatureChance = new SkyblockStatItem(SkyblockStats.SEA_CREATURE_CHANCE, skyblockPlayer, ChatColor.DARK_AQUA + "∞ Sea Creature Chance", "Sea Creature is your chance of catching Sea Creautres while fishing", "All players have to same base sea creature chance", 410, 0);
                    SkyblockStatItem magicFind = new SkyblockStatItem(SkyblockStats.MAGIC_FIND, skyblockPlayer, ChatColor.AQUA + "✯ Magic Find", "Magic Find increases how many rare items you find", "Increase your base magic find by collecting unique pets", 280, 0);
                    SkyblockStatItem petLuck = new SkyblockStatItem(SkyblockStats.PET_LUCK, skyblockPlayer, ChatColor.LIGHT_PURPLE + "♣ Pet Luck", "Pet Luck increases how many pets you find and gives you better luck when crafting pets.", "Increase your base pet luck by leveling your Taming Skill", 397, 3);
                    SkyblockStatItem trueDefense = new SkyblockStatItem(SkyblockStats.TRUE_DEFENSE, skyblockPlayer, ChatColor.WHITE + "❂ True Defense", " ", "Increase your true defense by exploring the world!", 351, 15);
                    SkyblockStatItem ferocity = new SkyblockStatItem(SkyblockStats.FEROCITY, skyblockPlayer, ChatColor.RED + "⫽ Ferocity", "Ferocity grants percent chance to double-strike enemies. Increments of 100 inceases the base number of strikes", "Increase your base ferocity by exploring the world!", 351, 1);
                    SkyblockStatItem abilityDamage = new SkyblockStatItem(SkyblockStats.ABILITY_DAMAGE, skyblockPlayer, ChatColor.RED + "✹ Ability Damage", "Ability Damage is the percentage of bonus damage applied to your spells!", "Increase your base Ability Damage by leveling your Enchanting skill", 138, 0);
                    SkyblockStatItem miningFortune = new SkyblockStatItem(SkyblockStats.MINING_FORTUNE, skyblockPlayer, ChatColor.GOLD + "☘ Mining Fortune", "Mining Fortune in the chance to gain double drops from ores, with a chance for triple drops at values greater than 100.", "Increase your base Mining fortune by upgrading your Heart of the Mountain", 397, 3);

                    menu.setItem(4, skull);
                    menu.setItem(19, health);
                    menu.setItem(20, defense);
                    menu.setItem(21, strength);
                    menu.setItem(22, speed);
                    menu.setItem(23, critChance);
                    menu.setItem(24, critDamage);
                    menu.setItem(25, mana);
                    menu.setItem(28, miningSpeed);
                    menu.setItem(29, attackSpeed);
                    menu.setItem(30, seaCreatureChance);
                    menu.setItem(31, magicFind);
                    menu.setItem(32, petLuck);
                    menu.setItem(33, trueDefense);
                    menu.setItem(34, ferocity);
                    menu.setItem(37, abilityDamage);
                    menu.setItem(38, miningFortune);
                    menu.setItem(48, goback);
                    menu.setItem(49, close);

                    player.openInventory(menu);
                    break;
                case "your skills":
                    skyblockPlayer.setInventory("Your Skills", Bukkit.createInventory(null, 54, "Your Skills"));
                    menu = skyblockPlayer.getInventory("Your Skills");

                    for (int i = 0; i < 54; ++i){
                        menu.setItem(i, space1);
                    }

                    SkyblockSkillGuiItem farming = new SkyblockSkillGuiItem(SkillType.FARMING, skyblockPlayer, "Harvest crops and shear sheep to earn Farming XP!", "Farmhand", null,294, 0);
                    SkyblockSkillGuiItem mining = new SkyblockSkillGuiItem(SkillType.MINING, skyblockPlayer, "Spelunk islands for ores and valuable materials to earn Mining XP!", "Spelunker", null, 274, 0);
                    SkyblockSkillGuiItem combat = new SkyblockSkillGuiItem(SkillType.COMBAT, skyblockPlayer, "Fight mobs and players to earn Combat XP!", "Warrior", null, 272, 0);
                    SkyblockSkillGuiItem foraging = new SkyblockSkillGuiItem(SkillType.FORAGING, skyblockPlayer, "Cut trees and forage for other plants to earn Foraging XP!", "Logger", null, 6, 3);
                    SkyblockSkillGuiItem fishing = new SkyblockSkillGuiItem(SkillType.FISHING, skyblockPlayer, "Visit your local pond to fish and earn Fishing XP!", "Treasure Hunter", null, 346, 0);
                    SkyblockSkillGuiItem enchanting = new SkyblockSkillGuiItem(SkillType.ENCHANTING, skyblockPlayer, "Enchant items to earn Enchanting XP!", "Conjourer", null, 116, 0);
                    SkyblockSkillGuiItem alchemy = new SkyblockSkillGuiItem(SkillType.ALCHEMY, skyblockPlayer, "Brew potions to earn Alchemy XP!", "Brewer", null, 379, 0);
                    SkyblockSkillGuiItem carpentry = new SkyblockSkillGuiItem(SkillType.CARPENTRY, skyblockPlayer, "Craft items to earn Carpentry XP!", "Carpentry", null, 58, 0);
                    List<String> runecraftingRewards = new ArrayList<>();
                    runecraftingRewards.add(ChatColor.GRAY + "Access to runes with the same level");
                    SkyblockSkillGuiItem runecrafting = new SkyblockSkillGuiItem(SkillType.RUNECRAFTING, skyblockPlayer, "Slay bosses, runic mobs & fuse runes to earn Runecrafting XP!", "Runecrafter", runecraftingRewards, 378, 0);
                    SkyblockSkillGuiItem social = new SkyblockSkillGuiItem(SkillType.SOCIAL, skyblockPlayer, "Gain Social XP for every new unique guest, hosting guests and visiting islands!", "Social", null, 388, 0);
                    SkyblockSkillGuiItem taming = new SkyblockSkillGuiItem(SkillType.TAMING, skyblockPlayer, "Level up pets to gain Taming XP!", "Zoologist", null, 383, 0);
                    SkyblockSkillGuiItem dungeoneering = new SkyblockSkillGuiItem(SkillType.CATACOMBS, skyblockPlayer, "Coming Soon!", "Catacombs", null, 397, 3);
                    SkyblockGuiItem skillsItem = new SkyblockGuiItem(ChatColor.GREEN + "Your Skills", "View your skill progression and rewards.", null, Material.DIAMOND_SWORD);

                    menu.setItem(4, skillsItem);
                    menu.setItem(19, farming);
                    menu.setItem(20, mining);
                    menu.setItem(21, combat);
                    menu.setItem(22, foraging);
                    menu.setItem(23, fishing);
                    menu.setItem(24, enchanting);
                    menu.setItem(25, alchemy);
                    menu.setItem(29, carpentry);
                    menu.setItem(30, runecrafting);
                    menu.setItem(31, social);
                    menu.setItem(32, taming);
                    menu.setItem(33, dungeoneering);

                    menu.setItem(48, goback);
                    menu.setItem(49, close);
                    player.openInventory(menu);
                    break;
                case "collections":
                    player.performCommand("collections main");
                    break;
                case "recipe book":
                    skyblockPlayer.setInventory("Recipe Book", Bukkit.createInventory(null, 54, "Recipe Book"));
                    break;
                case "trades":
                    skyblockPlayer.setInventory("Trades", Bukkit.createInventory(null, 54, "Trades"));
                    break;
                case "quest log":
                    skyblockPlayer.setInventory("Quest Log", Bukkit.createInventory(null, 54, "Quest Log"));
                    break;
                case "calendar and events":
                    skyblockPlayer.setInventory("Calendar and Events", Bukkit.createInventory(null, 54, "Calendar and Events"));
                    break;
                case "storage":
                    player.openInventory(player.getEnderChest());
                    player.playSound(player.getLocation(), Sound.CHEST_OPEN, 10, 1);
                    break;
                case "active effects":
                    skyblockPlayer.setInventory("Active Effects", Bukkit.createInventory(null, 54, "Active Effects"));
                    break;
                case "pets":
                    skyblockPlayer.setInventory("Pets", Bukkit.createInventory(null, 54, "Pets"));
                    menu = skyblockPlayer.getInventory("Pets");

                    menu.setItem(17, space1);
                    menu.setItem(18, space1);
                    menu.setItem(26, space1);
                    menu.setItem(27, space1);
                    menu.setItem(35, space1);
                    menu.setItem(36, space1);
                    menu.setItem(44, space1);
                    for (int i = 45; i < 54; ++i){ menu.setItem(i, space1); }
                    for (int i = 0; i < 10; ++i){ menu.setItem(i, space1); }

                    menu.setItem(48, goback);
                    menu.setItem(49, close);

                    ItemStack convertPet = new ItemStack(Material.INK_SACK, 1);
                    convertPet.setDurability((short) 10);
                    ItemMeta convertPetMeta = convertPet.getItemMeta();
                    convertPetMeta.setDisplayName(ChatColor.GREEN + "Convert Pet to an Item");
                    List<String> conevertPetLore = new ArrayList<>();
                    ItemUtil.addLoreMessage("Enable this setting and click a pet to convert it to an item.", conevertPetLore);
                    convertPetMeta.setLore(conevertPetLore);
                    convertPet.setItemMeta(convertPetMeta);

                    ItemStack hidePets = new ItemStack(Material.STONE_BUTTON);
                    ItemMeta hidePetsMeta = hidePets.getItemMeta();
                    hidePetsMeta.setDisplayName(ChatColor.GREEN + "Hide Pets");
                    List<String> hidePetsLore = new ArrayList<>();
                    ItemUtil.addLoreMessage("Hide all pets which are little heads from being visible in the world.", hidePetsLore);
                    hidePetsMeta.setLore(hidePetsLore);
                    hidePets.setItemMeta(hidePetsMeta);

                    menu.setItem(50, convertPet);
                    menu.setItem(51, hidePets);

                    for (ItemStack pets : Config.getPets(player)){
                        if (skyblockPlayer.activePet != null) {
                            if (skyblockPlayer.activePet.getHelmet().getItemMeta().getDisplayName().equals(pets.getItemMeta().getDisplayName())){
                                ItemMeta petsMeta = pets.getItemMeta();
                                List<String> petLore = pets.getItemMeta().getLore();
                                for (int i = 0; i < petLore.size(); i++){
                                    String string = petLore.get(i);
                                    if (ChatColor.stripColor(string).startsWith("LEGENDARY") || ChatColor.stripColor(string).startsWith("EPIC") || ChatColor.stripColor(string).startsWith("RARE") || ChatColor.stripColor(string).startsWith("UNCOMMON") || ChatColor.stripColor(string).startsWith("COMMON")){
                                        petLore.set(i, ChatColor.RED + "Click to despawn!");
                                        petLore.add(" ");
                                        petLore.add(string);

                                        petsMeta.setLore(petLore);
                                        pets.setItemMeta(petsMeta);
                                        SkyblockPetGuiItem guiItem = new SkyblockPetGuiItem(pets);
                                        menu.addItem(guiItem);
                                        break;
                                    }
                                }
                            }else{
                                SkyblockPetGuiItem guiItem = new SkyblockPetGuiItem(pets);
                                menu.addItem(guiItem);
                            }
                        }else{
                            SkyblockPetGuiItem guiItem = new SkyblockPetGuiItem(pets);
                            menu.addItem(guiItem);
                        }
                    }

                    player.openInventory(menu);
                    break;
                case "crafting table":
                    player.performCommand("craft");
                    break;
                case "wardrobe":
                    skyblockPlayer.setInventory("Wardrobe", Bukkit.createInventory(null, 54, "Wardrobe"));
                    break;
                case "fast travel":
                    skyblockPlayer.setInventory("Fast Travel", Bukkit.createInventory(null, 54, "Fast Travel"));
                    break;
                case "profile management":
                    skyblockPlayer.setInventory("Profiles Management", Bukkit.createInventory(null, 54, "Profiles Management"));
                    break;
                case "close":
                    player.closeInventory();
                    break;
                case "settings":
                    skyblockPlayer.setInventory("Settings", Bukkit.createInventory(null, 54, "Settings"));
                    break;
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("Your Skills")){
            String[] strings = e.getCurrentItem().getItemMeta().getDisplayName().split(" ");
            SkillLevelsInventory inventory;
            skyblockPlayer.lastInventory = e.getClickedInventory();
            switch (ChatColor.stripColor(strings[0].toLowerCase())){
                case "farming":
                    inventory = new SkillLevelsInventory(SkillType.FARMING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "mining":
                    inventory = new SkillLevelsInventory(SkillType.MINING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "combat":
                    inventory = new SkillLevelsInventory(SkillType.COMBAT, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "foraging":
                    inventory = new SkillLevelsInventory(SkillType.FORAGING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "fishing":
                    inventory = new SkillLevelsInventory(SkillType.FISHING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "enchanting":
                    inventory = new SkillLevelsInventory(SkillType.ENCHANTING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "alchemy":
                    inventory = new SkillLevelsInventory(SkillType.ALCHEMY, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "carpentry":
                    inventory = new SkillLevelsInventory(SkillType.CARPENTRY, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "runecrafting":
                    inventory = new SkillLevelsInventory(SkillType.RUNECRAFTING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "social":
                    inventory = new SkillLevelsInventory(SkillType.SOCIAL, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "taming":
                    inventory = new SkillLevelsInventory(SkillType.TAMING, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
                case "catacombs":
                    inventory = new SkillLevelsInventory(SkillType.CATACOMBS, skyblockPlayer);
                    player.openInventory(inventory);
                    break;
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("Pets")){
            if (e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
                if (!skyblockPlayer.convertPetToItem) {
                    ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                    List<String> lore = itemMeta.getLore();

                    for (int i = 0; i < lore.size(); ++i) {
                        String string = lore.get(i);
                        if (string.startsWith(ChatColor.RED + "Click to despawn!")) {
                            Bukkit.getPluginManager().callEvent(new SkyblockPetUnequipEvent(player, skyblockPlayer.activePet));
                            skyblockPlayer.activePet.remove();
                            skyblockPlayer.activePet = null;
                            Config.setActivePet(player, null);
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "You despawned your " + e.getCurrentItem().getItemMeta().getDisplayName());
                            break;
                        }
                        if (ChatColor.stripColor(string).startsWith("LEGENDARY") || ChatColor.stripColor(string).startsWith("EPIC") || ChatColor.stripColor(string).startsWith("RARE") || ChatColor.stripColor(string).startsWith("UNCOMMON") || ChatColor.stripColor(string).startsWith("COMMON")) {
                            Config.setActivePet(player, e.getCurrentItem());
                            player.closeInventory();
                            player.sendMessage(ChatColor.GREEN + "You summoned your " + e.getCurrentItem().getItemMeta().getDisplayName());
                            if (skyblockPlayer.activePet != null) {
                                skyblockPlayer.activePet.remove();
                            }
                            skyblockPlayer.activePet = SkyblockPet.spawnArmorStand(player, e.getCurrentItem());
                            Bukkit.getPluginManager().callEvent(new SkyblockPetEquipEvent(player, skyblockPlayer.activePet));
                            break;
                        }
                    }

                    itemMeta.setLore(lore);
                    e.getCurrentItem().setItemMeta(itemMeta);
                }else{
                    for (ItemStack itemStack : Config.getPets(player)){
                        if (itemStack.getItemMeta().getDisplayName().equals(e.getCurrentItem().getItemMeta().getDisplayName())){
                            player.getInventory().addItem(itemStack);
                            Config.removePet(player, e.getCurrentItem());
                            break;
                        }
                    }
                    e.getClickedInventory().setItem(e.getSlot(), null);
                }
            }else if (e.getCurrentItem().getType().equals(Material.INK_SACK)){
                if (skyblockPlayer.convertPetToItem){
                    skyblockPlayer.convertPetToItem = false;
                    ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                    itemMeta.setDisplayName(ChatColor.GREEN + "Convert Pet to an Item!");
                    e.getCurrentItem().setItemMeta(itemMeta);
                }else{
                    skyblockPlayer.convertPetToItem = true;
                    ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
                    itemMeta.setDisplayName(ChatColor.GREEN + "Currently Coverting!");
                    e.getCurrentItem().setItemMeta(itemMeta);
                }
            }
        }else if (e.getView().getTitle().startsWith("Visit")){
            if (e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
                OfflinePlayer target = Bukkit.getOfflinePlayer(e.getView().getTitle().split(" ")[1]);
                player.teleport(new Location(IslandManager.getIsland(target.getPlayer()), 0, 100, 0));
                if (target.isOnline()){

                    target.getPlayer().sendMessage(ChatColor.AQUA + "[SKYBLOCK] " + ChatColor.GRAY + player.getName() + ChatColor.YELLOW + " is visiting your island!");
                }
            }
        }else if (e.getView().getTitle().endsWith("'s Profile")){
            Player target = Bukkit.getPlayer(e.getView().getTitle().split("'s Profile")[0]);
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase()).startsWith("visit island")) {
                player.performCommand("visit " + target.getName());
            }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toLowerCase()).startsWith("trade request")){
                player.performCommand("trade " + target.getName());
            }
        }
    }

}
