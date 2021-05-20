package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.GuiItems.SkyblockSkillGuiItem;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.SkillsManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SkillsLevelsInventory2 extends CraftInventoryCustom implements Listener {

    public SkillType skillType;
    public SkyblockPlayer skyblockPlayer;

    public SkillsLevelsInventory2(SkillType skillType, SkyblockPlayer skyblockPlayer) {
        super(null, 54, StringUtils.capitalize(skillType.name().toLowerCase()) + " Skill");
        skyblockPlayer.setInventory(skillType.name().toLowerCase() + " Skill - Page 2", this);
        this.skillType = skillType;
        this.skyblockPlayer = skyblockPlayer;
        Bukkit.getPluginManager().registerEvents(this, Main.getMain());
        SkyblockSkillGuiItem farming = new SkyblockSkillGuiItem(SkillType.FARMING, skyblockPlayer, "Harvest crops and shear sheep to earn Farming XP!", "Farmhand", null, 294, 0);
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

        ItemStack goback = new ItemStack(Material.ARROW);
        ItemMeta gobackmeta = goback.getItemMeta();
        gobackmeta.setDisplayName(ChatColor.GREEN + "Go Back");
        goback.setItemMeta(gobackmeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closeMeta);

        ItemStack nextPage = new ItemStack(Material.ARROW);
        ItemMeta nextPageMeta = nextPage.getItemMeta();
        nextPageMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
        nextPage.setItemMeta(nextPageMeta);

        ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
        ItemMeta itemMeta = space1.getItemMeta();
        itemMeta.setDisplayName(" ");
        space1.setItemMeta(itemMeta);

        for (int i = 0; i < 54; ++i){
            this.setItem(i, space1);
        }

        this.setItem(48, goback);
        this.setItem(49, close);
        this.setItem(50, nextPage);

        int i = Config.getStatLvl(skyblockPlayer.getBukkitPlayer(), skillType.name().toLowerCase()) + 1;

        ItemStack lvlItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData());
        ItemMeta meta = lvlItem.getItemMeta();
        List<String> lore = new ArrayList<>();

        int index = 0;

        switch (skillType){
            case FARMING:
                this.setItem(0, farming);
                createInventory("Farmhand", index, lvlItem, meta, lore, i, ChatColor.RED + "❤ Health", ChatColor.WHITE + "Grants " + ChatColor.GOLD + "+"  + ChatColor.GOLD + "4 ☘ Farming Fortune");
                break;
            case MINING:
                this.setItem(0, mining);
                createInventory("Spelunker", index, lvlItem, meta, lore, i, ChatColor.GREEN + "❈ Defense", ChatColor.WHITE + "Grants " + ChatColor.GOLD + "+"  + ChatColor.GOLD + "4 ☘ Mining Fortune");
                break;
            case COMBAT:
                this.setItem(0, combat);
                createInventory("Warrior", index, lvlItem, meta, lore, i, ChatColor.BLUE + "☣ Crit Chance", ChatColor.WHITE + "Grants " + ChatColor.RED + "+"  + ChatColor.RED + "4%" + ChatColor.WHITE + " extra damage to mobs");
                break;
            case FORAGING:
                this.setItem(0, foraging);
                createInventory("Logger", index, lvlItem, meta, lore, i, ChatColor.RED + "❁ Strength", ChatColor.WHITE + "Grants " + ChatColor.GOLD + "+"  + ChatColor.GOLD + "2 ☘ Foraging Fortune");
                break;
            case FISHING:
                this.setItem(0, fishing);
                createInventory("Treasure Hunter", index, lvlItem, meta, lore, i, ChatColor.RED + "❤ Health", ChatColor.WHITE + "Grants " + ChatColor.DARK_AQUA + "+"  + ChatColor.DARK_AQUA + "0.2% " + ChatColor.WHITE + " chance to find treasure");
                break;
            case ENCHANTING:
                this.setItem(0, enchanting);
                createInventory("Conjourer", index, lvlItem, meta, lore, i, ChatColor.RED + "✹ Ability Damage", ChatColor.WHITE + "Grants " + ChatColor.DARK_AQUA + "+"  + ChatColor.AQUA + "4% " + ChatColor.WHITE + " more xp from any source");
                break;
            case ALCHEMY:
                this.setItem(0, alchemy);
                createInventory("Brewer", index, lvlItem, meta, lore, i, ChatColor.AQUA + "✎ Intelligence", ChatColor.WHITE + "Potions your brew have a " + ChatColor.AQUA + "1%" + ChatColor.WHITE + " longer duration");
                break;
            case CARPENTRY:
                this.setItem(0, carpentry);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i, "Placeholder", "Placeholder");
                break;
            case RUNECRAFTING:
                this.setItem(0, runecrafting);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i, "Placeholder", ChatColor.GRAY + "Access to Level " + ChatColor.DARK_PURPLE + "<newlvl> " + ChatColor.GRAY + "Runes");
                break;
            case SOCIAL:
                this.setItem(0, social);
                createInventory("Cosmetic Skill", index, lvlItem, meta, lore, i, "Placeholder", "Placeholder");
                break;
            case TAMING:
                this.setItem(0, taming);
                createInventory("Zoologist", index, lvlItem, meta, lore, i, ChatColor.LIGHT_PURPLE + "♣ Pet Luck", ChatColor.WHITE + "Gain <oldlvl>" + ChatColor.DARK_GRAY + "➜" + ChatColor.GREEN + "<newlvl>% " + ChatColor.WHITE + " extra pet exp.");
                break;
            case CATACOMBS:
                this.setItem(0, dungeoneering);
                createInventory("Catacombs", index, lvlItem, meta, lore, i, "Placeholder", "Placeholder");
                break;
        }

        for (ItemStack itemStack : this.getContents()){
            if (itemStack.getAmount() % 5 == 0 & itemStack.getDurability() == 5){
                itemStack.setDurability((short) 0);
                switch (skillType){
                    case FARMING:
                        itemStack.setType(Material.HAY_BLOCK);
                        break;
                    case MINING:
                        itemStack.setType(Material.IRON_BLOCK);
                        break;
                    case COMBAT:
                        itemStack.setType(Material.DIAMOND_HELMET);
                        break;
                    case FORAGING:
                        itemStack.setType(Material.LOG);
                        break;
                    case FISHING:
                        itemStack.setType(Material.PRISMARINE);
                        break;
                    case ENCHANTING:
                        itemStack.setType(Material.ENCHANTED_BOOK);
                        break;
                    case ALCHEMY:
                        itemStack.setType(Material.BLAZE_ROD);
                        break;
                    case CARPENTRY:
                        itemStack.setType(Material.ARMOR_STAND);
                        break;
                    case RUNECRAFTING:
                        itemStack.setType(Material.ENDER_PORTAL_FRAME);
                        break;
                    case SOCIAL:
                        itemStack.setType(Material.BLAZE_POWDER);
                        break;
                    case TAMING:
                        itemStack.setType(Material.GOLDEN_CARROT);
                        break;
                    case CATACOMBS:
                        itemStack.setType(Material.SKULL_ITEM);
                        itemStack.setDurability((short) 3);
//                        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
//                        skullMeta.setOwner("Fantasizers");
//                        itemStack.setItemMeta(skullMeta);
                        break;
                }
            }
        }
    }

    public void createInventory(String s, int index, ItemStack lvlItem, ItemMeta meta, List<String> lore, int i, String stat, String stat2){
        for (index = 1; index < 26; ++index){
            String statII = " " + stat2.replaceAll("<oldlvl>", String.valueOf(index+25 - 1)).replaceAll("<newlvl>", String.valueOf(index+25));
            double lvl = 0;
            DecimalFormat format = new DecimalFormat("#,###");
            format.setGroupingUsed(true);
            String s1 = ChatColor.YELLOW + " " + s + " " + (Integer.valueOf(index) + 25);
            if (index >= 1 && index < 4) {
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + SkillsManager.getCoinRewards(index+25) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(1 + 8 + (9 * (index - 1)), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(1 + 8 + (9 * (index - 1)), lvlItem);
                    lore.clear();
                }
            }else if (index == 4){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(28, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(28, lvlItem);
                    lore.clear();
                }
            }else if (index >= 5 && index < 9){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(5 + 24 - ((index - 5) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(5 + 24 - ((index - 5) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 9){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(3, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(3, lvlItem);
                    lore.clear();
                }
            }else if (index >= 10 && index < 14){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(10 - 6 + ((index - 10) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(10 - 6 + ((index - 10) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 14){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(32, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(32, lvlItem);
                    lore.clear();
                }
            }else if (index >= 15 && index < 19){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(15 + 18 - ((index - 15) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(15 + 18 - ((index - 15) * 9), lvlItem);
                    lore.clear();
                }
            }else if (index == 19){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(7, lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(7, lvlItem);
                    lore.clear();
                }
            }else if (index >= 20 && index < 26){
                if (i >= 26 && index + 25 < i) {
                    lvlItem.setDurability((short) 5);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(20 - 12 + ((index - 20) * 9), lvlItem);
                    lore.clear();
                } else {
                    lvlItem.setDurability((short) 14);
                    meta.setDisplayName(ChatColor.GREEN + StringUtils.capitalize(skillType.name().toLowerCase()) + " " + (Integer.valueOf(index) + 25));
                    lore.add(ChatColor.GRAY + "Rewards:");
                    lore.add(s1);
                    if (!ChatColor.stripColor(stat2).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(statII);
                    }
                    if (ChatColor.stripColor(stat).toLowerCase().contains("pet luck")) {
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("defense")){
                        lvl = 1;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("health")){
                        lvl = 5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("crit chance")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("strength")){
                        lvl = 3;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("ability damage")){
                        lvl = 0.5;
                    }else if (ChatColor.stripColor(stat).toLowerCase().contains("intelligence")){
                        lvl = 1;
                    }
                    if (!ChatColor.stripColor(stat).toLowerCase().equalsIgnoreCase("placeholder")) {
                        lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.getLastColors(stat) + lvl + stat);
                    }
                    lore.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(SkillsManager.getCoinRewards(index+25)) + ChatColor.GRAY + " Coins");
                    meta.setLore(lore);
                    lvlItem.setItemMeta(meta);
                    lvlItem.setAmount(index + 25);
                    this.setItem(20 - 12 + ((index - 20) * 9), lvlItem);
                    lore.clear();
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if (e.getClickedInventory().equals(this)){
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Previous Page")) {
                SkillLevelsInventory skillsLevelsInventory2 = new SkillLevelsInventory(skillType, skyblockPlayer);
                skyblockPlayer.getBukkitPlayer().openInventory(skillsLevelsInventory2);
            }
        }
    }

}
