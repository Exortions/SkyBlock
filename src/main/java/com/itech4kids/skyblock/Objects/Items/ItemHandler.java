package com.itech4kids.skyblock.Objects.Items;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itech4kids.skyblock.Enums.ReforgeTypes;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class ItemHandler {

    // Swords
    public static ItemStack aspect_of_the_jerry;
    public static ItemStack fancy_sword;
    public static ItemStack rogue_sword;
    public static ItemStack spider_sword;
    public static ItemStack undead_sword;
    public static ItemStack end_sword;
    public static ItemStack cleaver;
    public static ItemStack flaming_sword;
    public static ItemStack prismarine_blade;
    public static ItemStack hunter_knife;
    public static ItemStack tatician_sword;
    public static ItemStack thick_tatician_sword;
    public static ItemStack ember_rod;
    public static ItemStack frozen_scythe;
    public static ItemStack golem_sword;
    public static ItemStack aspect_of_the_end;
    public static ItemStack raider_axe;
    public static ItemStack revenant_falchion;
    public static ItemStack silver_fang;
    public static ItemStack shaman_sword;
    public static ItemStack scorpion_foil;
    public static ItemStack thick_scorpion_foil;
    public static ItemStack zombie_sword;
    public static ItemStack ornate_zombie_sword;
    public static ItemStack end_stone_sword;
    public static ItemStack recluse_fang;
    public static ItemStack reaper_falchion;
    public static ItemStack pooch_sword;
    public static ItemStack edibleMace;

    // Armor
    public static ItemStack superior_dragon_helmet;
    public static ItemStack superior_dragon_chestplate;
    public static ItemStack superior_dragon_leggings;
    public static ItemStack superior_dragon_boots;

    public static ItemStack farm_suit_helmet;
    public static ItemStack farm_suit_chestplate;
    public static ItemStack farm_suit_leggings;
    public static ItemStack farm_suit_boots;

    public static ItemStack mushroom_helmet;
    public static ItemStack mushroom_chestplate;
    public static ItemStack mushroom_leggings;
    public static ItemStack mushroom_boots;

    // Misc
    public static ItemStack grappling_hook;

    //Material/Minerals
    public static Map<String, ItemStack> materialMap;
    //Farming
    public static ItemStack enchanted_bread;
    public static ItemStack enchanted_hay_bale;
    public static ItemStack enchanted_carrot;
    public static ItemStack enchanted_carrot_on_a_stick;
    public static ItemStack enchanted_golden_carrot;
    public static ItemStack enchanted_potato;
    public static ItemStack enchanted_baked_potato;
    public static ItemStack enchanted_pumpkin;
    public static ItemStack enchanted_melon;
    public static ItemStack enchanted_glistering_melon;
    public static ItemStack enchanted_melon_block;
    public static ItemStack enchanted_red_mushroom;
    public static ItemStack enchanted_brown_mushroom;
    public static ItemStack enchanted_red_mushroom_block;
    public static ItemStack enchanted_brown_mushroom_block;
    public static ItemStack enchanted_cocoa_beans;
    public static ItemStack enchanted_cookie;
    public static ItemStack enchanted_cactus_green;
    public static ItemStack enchanted_cactus;
    public static ItemStack enchanted_sugar;
    public static ItemStack enchanted_paper;
    public static ItemStack enchanted_sugar_cane;
    public static ItemStack enchanted_leather;
    public static ItemStack enchanted_raw_beef;
    public static ItemStack enchanted_pork;
    public static ItemStack enchanted_grilled_pork;
    public static ItemStack enchanted_raw_chicken;
    public static ItemStack enchanted_egg;
    public static ItemStack enchanted_cake;
    public static ItemStack enchanted_super_egg;
    public static ItemStack enchanted_feather;
    public static ItemStack enchanted_mutton;
    public static ItemStack enchanted_cooked_mutton;
    public static ItemStack enchanted_raw_rabbit;
    public static ItemStack enchanted_rabbit_foot;
    public static ItemStack enchanted_rabbit_hide;
    public static ItemStack enchanted_nether_wart;

    //Mining
    public static ItemStack enchanted_cobblestone;
    public static ItemStack enchanted_coal;
    public static ItemStack enchanted_coal_block;
    public static ItemStack enchanted_iron;
    public static ItemStack enchanted_iron_block;
    public static ItemStack enchanted_gold;
    public static ItemStack enchanted_gold_block;
    public static ItemStack enchanted_diamond;
    public static ItemStack enchanted_diamond_block;
    public static ItemStack enchanted_lapis;
    public static ItemStack enchanted_lapis_block;
    public static ItemStack enchanted_emerald;
    public static ItemStack enchanted_emerald_block;
    public static ItemStack enchanted_redstone;
    public static ItemStack enchanted_redstone_block;
    public static ItemStack enchanted_quartz;
    public static ItemStack enchanted_quartz_block;
    public static ItemStack enchanted_obsidian;
    public static ItemStack enchanted_glowstone;
    public static ItemStack enchanted_glowstone_block;
    public static ItemStack enchanted_flint;
    public static ItemStack enchanted_ice;
    public static ItemStack enchanted_packed_ice;
    public static ItemStack enchanted_sand;
    public static ItemStack enchanted_endstone;
    public static ItemStack enchanted_snow_block;

    //Combat
    public static ItemStack enchanted_rotten_flesh;
    public static ItemStack enchanted_bone;
    public static ItemStack enchanted_bone_block;
    public static ItemStack enchanted_string;
    public static ItemStack enchanted_spider_eye;
    public static ItemStack enchanted_gunpowder;
    public static ItemStack enchanted_firework;
    public static ItemStack enchanted_ender_pearl;
    public static ItemStack enchanted_ghast_tear;
    public static ItemStack enchanted_slime_ball;
    public static ItemStack enchanted_blaze_powder;
    public static ItemStack enchanted_blaze_rod;
    public static ItemStack enchanted_magma_ball;

    //Foraging

    //Fishing

    // Other
    public static ItemStack skyblock_menu;

    public static void init(){

        initSwords();
        initArmor();
        initTools();
        initFishing();
        initMisc();
        initMaterials();
    }

    public static void initArmor(){
        Map<String, ItemStack> armorMap = new HashMap<String, ItemStack>();

        List<String> superiorFSBDesc = new ArrayList<>();
        superiorFSBDesc.add(ChatColor.GRAY + "Most of your stats are increased");
        superiorFSBDesc.add(ChatColor.GRAY + "by " + ChatColor.GREEN + "5% " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Aspect of the");
        superiorFSBDesc.add(ChatColor.GOLD + "Dragons " + ChatColor.GRAY + "ability deals " + ChatColor.GREEN + "50%");
        superiorFSBDesc.add(ChatColor.GRAY + "more damage.");

        List<String> farmerSuitDesc = new ArrayList<>();
        farmerSuitDesc.add(ChatColor.GOLD + "Full Set Bonus: Bonus Speed");
        farmerSuitDesc.add(ChatColor.GRAY + "Increases your " + ChatColor.WHITE + "✦ Speed");
        farmerSuitDesc.add(ChatColor.GRAY + "by " + ChatColor.GREEN + "+20 " + ChatColor.GRAY + "near Farming");
        farmerSuitDesc.add(ChatColor.GRAY + "Minions or farming islands.");
        farmerSuitDesc.add("");
        farmerSuitDesc.add(ChatColor.GOLD + "Full Set Bonus: Farmer Aura");
        farmerSuitDesc.add(ChatColor.GRAY + "Regrow an extra crop on");
        farmerSuitDesc.add(ChatColor.GRAY + "farming islands every " + ChatColor.GREEN + "3");
        farmerSuitDesc.add(ChatColor.GRAY + "seconds.");
        farmerSuitDesc.add("");
        farmerSuitDesc.add(ChatColor.GRAY + "Farming islands:");
        farmerSuitDesc.add(ChatColor.GRAY + "- " + ChatColor.AQUA + "Farm");
        farmerSuitDesc.add(ChatColor.GRAY + "- " + ChatColor.AQUA + "The Barn");
        farmerSuitDesc.add(ChatColor.GRAY + "- " + ChatColor.YELLOW + "Mushroom Desert");
        farmerSuitDesc.add("");

        List<String> mushroomSuitAbilityDesc = new ArrayList<>();
        mushroomSuitAbilityDesc.add(ChatColor.GRAY + "Grants the wearer with");
        mushroomSuitAbilityDesc.add(ChatColor.DARK_GREEN + "permanent Night Vision " + ChatColor.GRAY + "while");
        mushroomSuitAbilityDesc.add(ChatColor.GRAY + "worn, and during the night, the");
        mushroomSuitAbilityDesc.add(ChatColor.GRAY + "stats of the armor pieces are");
        mushroomSuitAbilityDesc.add(ChatColor.DARK_GREEN + "tripled" + ChatColor.GRAY + ".");

        superior_dragon_helmet = createArmorHead("Orthyx", ChatColor.GOLD + "Superior Dragon Helmet", null, 1, "LEGENDARY HELMET", true, 0, 10, 2, 10, 0, 90, 130, 3, 25, true, "Superior Blood", superiorFSBDesc);
        superior_dragon_chestplate = createArmorPiece(Material.LEATHER_CHESTPLATE, ChatColor.GOLD + "Superior Dragon Chestplate", Color.fromRGB(242, 223, 17), ReforgeTypes.NO_REFORGE, (short) 4, "LEGENDARY CHESTPLATE", 1, null, false, true, "Superior Blood", superiorFSBDesc, 0, 10, 2, 10, 0, 150, 190, 3, 25, true);
        superior_dragon_leggings = createArmorPiece(Material.LEATHER_LEGGINGS, ChatColor.GOLD + "Superior Dragon Leggings", Color.fromRGB(242, 223, 17), ReforgeTypes.NO_REFORGE, (short) 4, "LEGENDARY LEGGINGS", 1, null, false, true, "Superior Blood", superiorFSBDesc, 0, 10, 2, 10, 0, 130, 170, 3, 25, true);
        superior_dragon_boots = createArmorPiece(Material.LEATHER_BOOTS, ChatColor.GOLD + "Superior Dragon Boots", Color.fromRGB(242, 93, 24), ReforgeTypes.NO_REFORGE, (short) 1, "LEGENDARY BOOTS", 1, null, false, true, "Superior Blood", superiorFSBDesc, 0, 10, 2, 10, 0, 80, 110, 3, 25, true);

        farm_suit_helmet = createArmorPiece(Material.LEATHER_HELMET, ChatColor.WHITE + "Farm Suit Helmet", Color.fromRGB(255, 255, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON HELMET", 1, farmerSuitDesc, false, false, "", null, 0, 0, 0, 0, 0, 0, 15, 0,0, true);
        farm_suit_chestplate = createArmorPiece(Material.LEATHER_CHESTPLATE, ChatColor.WHITE + "Farm Suit Chestplate", Color.fromRGB(255, 255, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON CHESTPLATE", 1, farmerSuitDesc, false, false, "", null, 0, 0, 0, 0, 0, 0, 15, 0,0, true);
        farm_suit_leggings = createArmorPiece(Material.LEATHER_LEGGINGS, ChatColor.WHITE + "Farm Suit Leggings", Color.fromRGB(255, 255, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON LEGGINGS", 1, farmerSuitDesc, false, false, "", null, 0, 0, 0, 0, 0, 0, 15, 0,0, true);
        farm_suit_boots = createArmorPiece(Material.LEATHER_BOOTS, ChatColor.WHITE + "Farm Suit Boots", Color.fromRGB(255, 255, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON BOOTS", 1, farmerSuitDesc, false, false, "", null, 0, 0, 0, 0, 0, 0, 15, 0,0, true);

        mushroom_helmet = createArmorPiece(Material.LEATHER_HELMET, ChatColor.WHITE + "Mushroom Helmet", Color.fromRGB(255, 0, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON HELMET", 1, null, false, true, "Night Afinity", mushroomSuitAbilityDesc, 0, 0, 0, 0, 0, 20, 0,0,0, false);
        mushroom_chestplate = createArmorPiece(Material.LEATHER_CHESTPLATE, ChatColor.WHITE + "Mushroom Chestplate", Color.fromRGB(255, 0, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON CHESTPLATE", 1, null, false, true, "Night Afinity", mushroomSuitAbilityDesc, 0, 0, 0, 0, 0, 10, 10,0,0, false);
        mushroom_leggings = createArmorPiece(Material.LEATHER_LEGGINGS, ChatColor.WHITE + "Mushroom Leggings", Color.fromRGB(255, 0, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON LEGGINGS", 1, null, false, true, "Night Afinity", mushroomSuitAbilityDesc, 0, 0, 0, 0, 0, 10, 5,0,0, false);
        mushroom_boots = createArmorPiece(Material.LEATHER_BOOTS, ChatColor.WHITE + "Mushroom Boots", Color.fromRGB(255, 0, 0), ReforgeTypes.NO_REFORGE, (short) 1, "COMMON BOOTS", 1, null, false, true, "Night Afinity", mushroomSuitAbilityDesc, 0, 0, 0, 0, 0, 15, 0,0,0, false);

        armorMap.put("superior_dragon_helmet", superior_dragon_helmet);
        armorMap.put("superior_dragon_chestplate", superior_dragon_chestplate);
        armorMap.put("superior_dragon_leggings", superior_dragon_leggings);
        armorMap.put("superior_dragon_boots", superior_dragon_boots);

        armorMap.put("farm_suit_helmet", farm_suit_helmet);
        armorMap.put("farm_suit_chestplate", farm_suit_chestplate);
        armorMap.put("farm_suit_leggings", farm_suit_leggings);
        armorMap.put("farm_suit_boots", farm_suit_boots);

        armorMap.put("mushroom_helmet", mushroom_helmet);
        armorMap.put("mushroom_chestplate", mushroom_chestplate);
        armorMap.put("mushroom_leggings", mushroom_leggings);
        armorMap.put("mushroom_boots", mushroom_boots);
    }

    public static void initTools(){

    }

    public static void initFishing(){

    }

    public static void initMaterials(){
        materialMap = new HashMap<>();

        enchanted_baked_potato = createMaterialItem(Material.BAKED_POTATO, (short) 0, true, ChatColor.GREEN + "UNCOMMON", false, "Baked Potato");
        enchanted_blaze_powder = createMaterialItem(Material.BLAZE_POWDER, (short) 0, true, ChatColor.GREEN + "UNCOMMON", true, "Blaze Powder");
        enchanted_blaze_rod = createMaterialItem(Material.BLAZE_ROD, (short) 0, true, ChatColor.GREEN + "UNCOMMON", true, "Blaze Rod");
        enchanted_bone = createMaterialItem(Material.BONE, (short) 0, true, ChatColor.WHITE + "COMMON", false, "Bone");
        enchanted_bone_block = createMaterialItem(Material.QUARTZ_BLOCK, (short) 0, true, ChatColor.BLUE + "RARE", false, "Bone Block");
        enchanted_bread = createMaterialItem(Material.BREAD, (short) 0, true, ChatColor.GREEN + "UNCOMMON", false, "Bread");
        enchanted_brown_mushroom = createMaterialItem(Material.BROWN_MUSHROOM, (short) 0, true, ChatColor.WHITE + "COMMON", false, "Brown Mushroom");
        enchanted_brown_mushroom_block = createMaterialItem(Material.HUGE_MUSHROOM_2, (short) 0, true, ChatColor.WHITE + "COMMON", false, "Brown Mushroom Block");
        enchanted_cactus = createMaterialItem(Material.INK_SACK, (short) 2, true, ChatColor.GREEN + "UNCOMMON", true, "Cactus");
        enchanted_cactus_green = createMaterialItem(Material.CACTUS, (short) 0, true, ChatColor.BLUE + "RARE", true, "Cactus Green");
        enchanted_cake = createMaterialItem(Material.CAKE, 0, true, ChatColor.GREEN + "UNCOMMON", true, "Cake");
        enchanted_carrot = createMaterialItem(Material.CARROT, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Carrot");
        enchanted_carrot_on_a_stick = createMaterialItem(Material.CARROT_STICK, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Carrot on a Stick");
        enchanted_coal = createMaterialItem(Material.COAL, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Coal");
        enchanted_coal_block = createMaterialItem(Material.COAL_BLOCK, 0, true, ChatColor.BLUE + "RARE", false, "Coal Block");
        enchanted_cobblestone = createMaterialItem(Material.COBBLESTONE, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Cobblestone");
        enchanted_cocoa_beans = createMaterialItem(Material.COCOA, 0, true, ChatColor.GREEN + "UNCOMMON", true, "Cocoa Beans");
        enchanted_cooked_mutton = createMaterialItem(Material.COOKED_MUTTON, 0, true, ChatColor.BLUE + "RARE", true, "Cooked Mutton");
        enchanted_cookie = createMaterialItem(Material.COOKIE, 0, true, ChatColor.BLUE + "RARE", true, "Cookie");
        enchanted_diamond = createMaterialItem(Material.DIAMOND, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Diamond");
        enchanted_diamond_block = createMaterialItem(Material.DIAMOND_BLOCK, 0, true, ChatColor.BLUE + "RARE", false, "Diamond Block");
        enchanted_egg = createMaterialItem(Material.EGG, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Egg");
        enchanted_emerald = createMaterialItem(Material.EMERALD, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Emerald");
        enchanted_emerald_block = createMaterialItem(Material.EMERALD_BLOCK, 0, true, ChatColor.BLUE + "RARE", false, "Emerald Block");
        enchanted_ender_pearl = createMaterialItem(Material.ENDER_PEARL, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Ender Pearl");
        enchanted_endstone = createMaterialItem(Material.ENDER_STONE, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Endstone");
        enchanted_feather = createMaterialItem(Material.FEATHER, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Feather");
        enchanted_flint = createMaterialItem(Material.FLINT, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Flint");
        enchanted_ghast_tear = createMaterialItem(Material.GHAST_TEAR, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Ghast Tear");
        enchanted_glistering_melon = createMaterialItem(Material.SPECKLED_MELON, 0, true, ChatColor.GREEN + "UNCOMMON", true, "Glistering Melon");
        enchanted_glowstone = createMaterialItem(Material.GLOWSTONE_DUST, 0, true, ChatColor.GREEN + "UNCOMMON", true, "Glowstone Dust");
        enchanted_glowstone_block = createMaterialItem(Material.GLOWSTONE, 0, true, ChatColor.BLUE + "RARE", true, "Glowstone");
        enchanted_gold = createMaterialItem(Material.GOLD_INGOT, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Gold Ingot");
        enchanted_gold_block = createMaterialItem(Material.GOLD_BLOCK, 0, true, ChatColor.BLUE + "RARE", false, "Gold Block");
        enchanted_golden_carrot = createMaterialItem(Material.GOLDEN_CARROT, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Golden Carrot");
        enchanted_grilled_pork = createMaterialItem(Material.GRILLED_PORK, 0, true, ChatColor.BLUE + "RARE", false, "Grilled Pork");
        enchanted_gunpowder = createMaterialItem(Material.SULPHUR, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Gunpowder");
        enchanted_firework = createMaterialItem(Material.FIREWORK, 0, true, ChatColor.BLUE + "RARE", false, "Firework");
        enchanted_hay_bale = createMaterialItem(Material.HAY_BLOCK, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Hay Bale");
        enchanted_ice = createMaterialItem(Material.ICE, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Ice");
        enchanted_iron = createMaterialItem(Material.IRON_INGOT, 0, true, ChatColor.GREEN + "UNCOMMON", false, "Iron Ingot");
        /*
        enchanted_iron_block;
        enchanted_lapis;
        enchanted_lapis_block;
        enchanted_leather;
        enchanted_magma_ball;
        enchanted_melon;
        enchanted_melon_block;
        enchanted_nether_wart;
        enchanted_obsidian;
        enchanted_packed_ice;
        enchanted_paper;
        enchanted_pork;
        enchanted_potato;
        enchanted_pumpkin;
        enchanted_quartz;
        enchanted_quartz_block;
        enchanted_rabbit_foot;
        enchanted_mutton;
        enchanted_rabbit_hide;
        enchanted_raw_beef;
        enchanted_raw_chicken;
        enchanted_raw_rabbit;
        enchanted_red_mushroom;
        enchanted_red_mushroom_block;
        enchanted_redstone;
        enchanted_redstone_block;
        enchanted_rotten_flesh;
        enchanted_sand;
        enchanted_slime_ball;
        enchanted_snow_block;
        enchanted_spider_eye;
        enchanted_sugar;
        enchanted_sugar_cane;
        enchanted_super_egg;
        enchanted_string;
         */
    }

    public static void initMisc(){
        Map<String, ItemStack> miscMap = new HashMap<String, ItemStack>();

        List<String> grapplingHookDesc = new ArrayList<>();
        grapplingHookDesc.add(ChatColor.GRAY + "Travel around in style using");
        grapplingHookDesc.add(ChatColor.GRAY + "this Grappling Hook.");
        grapplingHookDesc.add("");
        grapplingHookDesc.add(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");

        grappling_hook = createBasicItem(Material.FISHING_ROD, ChatColor.GREEN + "Grappling Hook", grapplingHookDesc, (short) 0, false, 1);
        skyblock_menu = createSkyblockMenu();

        miscMap.put("grappling_hook", grappling_hook);
        miscMap.put("skyblock_menu", skyblock_menu);
    }

    public static void initSwords(){
        Map<String, ItemStack> swordMap = new HashMap<String, ItemStack>();

        List<String> fancySwordDescription = new ArrayList<>();
        fancySwordDescription.add(ChatColor.BLUE + "Scavenger I");
        fancySwordDescription.add(ChatColor.GRAY + "Scavenge" + ChatColor.GOLD + " 0.5 Coins " + ChatColor.GRAY + "per");
        fancySwordDescription.add(ChatColor.GRAY + "monster level on kill.");
        fancySwordDescription.add(ChatColor.BLUE + "Sharpness II");
        fancySwordDescription.add(ChatColor.GRAY + "Increases melee damage dealt by");
        fancySwordDescription.add(ChatColor.GREEN + "10%");
        fancySwordDescription.add(ChatColor.BLUE + "First Strike I");
        fancySwordDescription.add(ChatColor.GRAY + "Increases melee damage dealt by");
        fancySwordDescription.add(ChatColor.GREEN + "25% " + ChatColor.GRAY + "for the first hit on a");
        fancySwordDescription.add(ChatColor.GRAY + "mob.");
        fancySwordDescription.add(ChatColor.BLUE + "Vampirism I");
        fancySwordDescription.add(ChatColor.GRAY + "Heals for " + ChatColor.GREEN + "1% " + ChatColor.GRAY + "of your missing");
        fancySwordDescription.add(ChatColor.GRAY + "health whenever you kill an");
        fancySwordDescription.add(ChatColor.GRAY + "enemy.");
        fancySwordDescription.add("");
        List<String> rogueSwordAbilityDesc = new ArrayList<>();
        rogueSwordAbilityDesc.add(ChatColor.GRAY + "Increases your movement " + ChatColor.WHITE + "✦");
        rogueSwordAbilityDesc.add(ChatColor.WHITE + "Speed " + ChatColor.GRAY + "by" + ChatColor.GREEN + " +20 " + ChatColor.GRAY + "for" + ChatColor.GREEN + " 30");
        rogueSwordAbilityDesc.add(ChatColor.GRAY + "seconds.");
        List<String> spiderSwordDesc = new ArrayList<>();
        spiderSwordDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+100% " + ChatColor.GRAY + "damage to Cave");
        spiderSwordDesc.add(ChatColor.GRAY + "Spiders, Silverfish, and");
        spiderSwordDesc.add(ChatColor.GRAY + "Spiders.");
        spiderSwordDesc.add("");
        List<String> undeadSwordDesc = new ArrayList<>();
        undeadSwordDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+100% " + ChatColor.GRAY + "damage to");
        undeadSwordDesc.add(ChatColor.GRAY + "Skeletons, Withers,");
        undeadSwordDesc.add(ChatColor.GRAY + "Zombie Pigmen, and");
        undeadSwordDesc.add(ChatColor.GRAY + "Zombies.");
        undeadSwordDesc.add("");
        List<String> endSwordDesc = new ArrayList<>();
        endSwordDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+100% " + ChatColor.GRAY + "damage to");
        endSwordDesc.add(ChatColor.GRAY + "Endermites, Ender");
        endSwordDesc.add(ChatColor.GRAY + "Dragons, and Endermen.");
        endSwordDesc.add("");
        List<String> cleaverAbilityDesc = new ArrayList<>();
        cleaverAbilityDesc.add(ChatColor.GRAY + "When hitting an entity, monsters");
        cleaverAbilityDesc.add(ChatColor.GRAY + "in a " + ChatColor.GREEN + "3 " + ChatColor.GRAY + "block range will be");
        cleaverAbilityDesc.add(ChatColor.GRAY + "hit for a portion of that damage");
        cleaverAbilityDesc.add(ChatColor.GRAY + "too.");
        List<String> flamingSwordDesc = new ArrayList<>();
        flamingSwordDesc.add(ChatColor.GRAY + "Ignites enemies for " + ChatColor.GREEN + "3s" + ChatColor.GRAY + ".");
        flamingSwordDesc.add("");
        List<String> prismarineBladeDesc = new ArrayList<>();
        prismarineBladeDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+200% " + ChatColor.GRAY + "damage while in water.");
        prismarineBladeDesc.add("");
        List<String> taticianSwordDesc = new ArrayList<>();
        taticianSwordDesc.add(ChatColor.GRAY + "Gains " + ChatColor.RED + "+15 Damage " + ChatColor.GRAY + "for");
        taticianSwordDesc.add(ChatColor.GRAY + "each Combat collection of");
        taticianSwordDesc.add(ChatColor.GRAY + "Tier VII and over of its");
        taticianSwordDesc.add(ChatColor.GRAY + "wearer.");
        taticianSwordDesc.add("");
        taticianSwordDesc.add(ChatColor.GRAY + "Your collections: " + ChatColor.YELLOW + "0" + ChatColor.GRAY + "/" + ChatColor.YELLOW + "10");
        taticianSwordDesc.add("");
        List<String> emberRodAbilityDesc = new ArrayList<>();
        emberRodAbilityDesc.add(ChatColor.GRAY + "Shoot 3 Fireballs which deal");
        emberRodAbilityDesc.add(ChatColor.RED + "30" + ChatColor.GRAY + " damage in rapid");
        emberRodAbilityDesc.add(ChatColor.GRAY + "succession in front of you!");
        List<String> frozenScytheAbilityDesc = new ArrayList<>();
        frozenScytheAbilityDesc.add(ChatColor.GRAY + "Shoots " + ChatColor.GREEN + "1 " + ChatColor.GRAY + "Ice Bolt that deals");
        frozenScytheAbilityDesc.add(ChatColor.RED + "1,000" + ChatColor.GRAY + " damage and slows");
        frozenScytheAbilityDesc.add(ChatColor.GRAY + "enemies hit for " + ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds!");
        List<String> golemSwordAbilityDesc = new ArrayList<>();
        golemSwordAbilityDesc.add(ChatColor.GRAY + "Punch the ground, damaging");
        golemSwordAbilityDesc.add(ChatColor.GRAY + "enemies in a hexagon around you");
        golemSwordAbilityDesc.add(ChatColor.GRAY + "for " + ChatColor.GREEN + "255 " + ChatColor.GRAY + "base Magic Damage.");
        List<String> aoteAbilitydesc = new ArrayList<>();
        aoteAbilitydesc.add(ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "3 blocks " + ChatColor.GRAY + " ahead of");
        aoteAbilitydesc.add(ChatColor.GRAY + "you and gain +50 " + ChatColor.WHITE + "✦ Speed");
        aoteAbilitydesc.add(ChatColor.GRAY + "for " + ChatColor.GREEN + "3 seconds.");
        List<String> raiderAxeDesc = new ArrayList<>();
        raiderAxeDesc.add(ChatColor.GRAY + "Earn " + ChatColor.GOLD + "+25 coins " + ChatColor.GRAY + "from monster kills.");
        raiderAxeDesc.add("");
        raiderAxeDesc.add(ChatColor.RED + "+1 Damage " + ChatColor.GRAY + "per " + ChatColor.RED + "500 " + ChatColor.GRAY + "kills.");
        raiderAxeDesc.add(ChatColor.DARK_GRAY + "Mobs level 10+, max +35");
        raiderAxeDesc.add(ChatColor.GRAY + "Kills: " + ChatColor.RED + "0");
        raiderAxeDesc.add("");
        raiderAxeDesc.add(ChatColor.RED + "+1❁ Strength" + ChatColor.GRAY + " per " + ChatColor.YELLOW + "500" + ChatColor.GRAY + " wood");
        raiderAxeDesc.add(ChatColor.GRAY + "Wood collections: " + ChatColor.YELLOW + "0");
        raiderAxeDesc.add("");
        List<String> revFalchionDesc = new ArrayList<>();
        revFalchionDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+150%" + ChatColor.GRAY + " damage to");
        revFalchionDesc.add(ChatColor.GRAY + "Zombies.");
        revFalchionDesc.add("");
        List<String> shamanSwordDesc = new ArrayList<>();
        shamanSwordDesc.add(ChatColor.GRAY + "Deal " + ChatColor.RED + "+1 Damage" + ChatColor.GRAY + " per " + ChatColor.RED + "50 max ❤" + ChatColor.GRAY + ".");
        shamanSwordDesc.add(ChatColor.GRAY + "Receive " + ChatColor.GREEN + "-20% " +ChatColor.GRAY + "damage from wolves.");
        shamanSwordDesc.add("");
        List<String> scorpionFoilDesc = new ArrayList<>();
        scorpionFoilDesc.add(ChatColor.GRAY + "Deals " + ChatColor.GREEN + "+150% " + ChatColor.GRAY + "to Zombies.");
        scorpionFoilDesc.add("");
        List<String> scorpionFoilAbilityDesc = new ArrayList<>();
        scorpionFoilAbilityDesc.add(ChatColor.GRAY + "You have " + ChatColor.YELLOW + "4 Ⓞ tickers" + ChatColor.GRAY + ".");
        scorpionFoilAbilityDesc.add(ChatColor.GRAY + "Blocking clears" + ChatColor.YELLOW + " 1 Ⓞ " + ChatColor.GRAY + "and heals " + ChatColor.RED + "60❤"  + ChatColor.GRAY + ".");
        scorpionFoilAbilityDesc.add(ChatColor.GRAY + "Once all tickers are cleared,");
        scorpionFoilAbilityDesc.add(ChatColor.GRAY + "your next attack is empowered");
        scorpionFoilAbilityDesc.add(ChatColor.GRAY + "for" + ChatColor.RED + " +250% damage" + ChatColor.GRAY + ".");
        scorpionFoilAbilityDesc.add(ChatColor.DARK_GRAY + "Tickers refill after 5 seconds.");
        List<String> zombieSwordAbilityDesc = new ArrayList<>();
        zombieSwordAbilityDesc.add(ChatColor.GRAY + "Heal for " + ChatColor.RED + "120" + ChatColor.GRAY + " + " + ChatColor.RED + "5%❤ " + ChatColor.GRAY + "and");
        zombieSwordAbilityDesc.add(ChatColor.GRAY + "heal players within " + ChatColor.GREEN + "7" + ChatColor.GRAY + " blocks");
        zombieSwordAbilityDesc.add(ChatColor.GRAY + "for " + ChatColor.RED + "40❤" + ChatColor.GRAY + ".");
        zombieSwordAbilityDesc.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "70");
        zombieSwordAbilityDesc.add(ChatColor.DARK_GRAY + "Charges: " + ChatColor.YELLOW + "4 " + ChatColor.DARK_GRAY + "/ " + ChatColor.GREEN + "15s");
        List<String> ornateZombieSwordAbilityDesc = new ArrayList<>();
        ornateZombieSwordAbilityDesc.add(ChatColor.GRAY + "Heal for " + ChatColor.RED + "144" + ChatColor.GRAY + " + " + ChatColor.RED + "5%❤ " + ChatColor.GRAY + "and");
        ornateZombieSwordAbilityDesc.add(ChatColor.GRAY + "heal players within " + ChatColor.GREEN + "7" + ChatColor.GRAY + " blocks");
        ornateZombieSwordAbilityDesc.add(ChatColor.GRAY + "for " + ChatColor.RED + "48❤" + ChatColor.GRAY + ".");
        ornateZombieSwordAbilityDesc.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.DARK_AQUA + "70");
        ornateZombieSwordAbilityDesc.add(ChatColor.DARK_GRAY + "Charges: " + ChatColor.YELLOW + "5 " + ChatColor.DARK_GRAY + "/ " + ChatColor.GREEN + "15s");
        List<String> end_stone_swordAbilityDesc = new ArrayList<>();
        end_stone_swordAbilityDesc.add(ChatColor.GRAY + "Consumes all of your remaining mana");
        end_stone_swordAbilityDesc.add(ChatColor.GRAY + "to grant Damage Resistance for");
        end_stone_swordAbilityDesc.add(ChatColor.GREEN + "5 " + ChatColor.GRAY + "seconds and extra damage");
        end_stone_swordAbilityDesc.add(ChatColor.GRAY + "on your next hit (within 5");
        end_stone_swordAbilityDesc.add(ChatColor.GRAY + "seconds) depending on how");
        end_stone_swordAbilityDesc.add(ChatColor.GRAY + "much mana was consumed!");
        List<String> recluse_fangAbilityDesc = new ArrayList<>();;
        List<String> reaper_falchionLore = new ArrayList<>();
        List<String> pooch_SwordLore = new ArrayList<>();
        List<String> edible_MaceAbliityDesc = new ArrayList<>();

        aspect_of_the_jerry = createInGameItem(Material.WOOD_SWORD, ChatColor.WHITE + "Aspect of the Jerry", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Parley", Collections.singletonList(ChatColor.GRAY + "Channel your inner Jerry."), "RIGHT CLICK", 0, "5s", "COMMON SWORD", 1, 0, 0, 0, 0, 0, 0,0,true);
        fancy_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.WHITE + "Fancy Sword", ReforgeTypes.NO_REFORGE, 1, fancySwordDescription, true, false, "", null, "", 0, "", "COMMON SWORD", 20, 0, 0, 0, 0, 0, 0,0,true);
        rogue_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.WHITE + "Rogue Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Speed Boost", rogueSwordAbilityDesc, "RIGHT CLICK", 50, "0s", "COMMON SWORD", 20, 0,0,0,0,0, 0,0,true);
        spider_sword = createInGameItem(Material.IRON_SWORD, ChatColor.WHITE + "Spider Sword", ReforgeTypes.NO_REFORGE, 1, spiderSwordDesc, false, false, "", null, "", 0, "", "COMMON SWORD", 30,0,0,0,0,0, 0,0,true);
        undead_sword = createInGameItem(Material.IRON_SWORD, ChatColor.WHITE + "Undead Sword", ReforgeTypes.NO_REFORGE, 1, undeadSwordDesc, false, false, "", null, "", 0, "", "COMMON SWORD", 30,0,0,0,0,0, 0,0,true);
        end_sword = createInGameItem(Material.DIAMOND_SWORD, ChatColor.GREEN + "End Sword", ReforgeTypes.NO_REFORGE, 1, endSwordDesc, false, false, "", null, "", 0, "", "UNCOMMON SWORD", 35, 0,0,0,0,0, 0,0,true);
        cleaver = createInGameItem(Material.GOLD_SWORD, ChatColor.GREEN + "Cleaver", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Cleave", cleaverAbilityDesc, "RIGHT CLICK", 0, "", "UNCOMMON SWORD", 60, 10, 0,0,0,0, 0,0,true);
        flaming_sword = createInGameItem(Material.IRON_SWORD, ChatColor.GREEN + "Flaming Sword", ReforgeTypes.NO_REFORGE, 1, flamingSwordDesc, false, false, "", Collections.singletonList(""),"", 0, "", "UNCOMMON SWORD", 50, 20, 0,0,0,0,0,0,true);
        prismarine_blade = createInGameItem(Material.PRISMARINE_SHARD, ChatColor.GREEN + "Prismarine Blade", ReforgeTypes.NO_REFORGE, 1, prismarineBladeDesc, false, false, "", Collections.singletonList(""), "", 0, "", "UNCOMMON", 50,25, 0,0,0,0,0,0,true);
        hunter_knife = createInGameItem(Material.IRON_SWORD, ChatColor.GREEN + "Hunter Knife", ReforgeTypes.NO_REFORGE, 1, null, false, false, "", null, "", 0,"", "UNCOMMON SWORD", 50, 0,0,0,0,0,40, 0,true);
        tatician_sword = createInGameItem(Material.WOOD_SWORD, ChatColor.BLUE + "Tatician's Sword", ReforgeTypes.NO_REFORGE, 1, taticianSwordDesc,false, false, "", null, "", 0, "", "RARE SWORD", 50, 0, 20, 0,0,0,0, 0,true);
        thick_tatician_sword = createInGameItem(Material.WOOD_SWORD, ChatColor.DARK_PURPLE + "Thick Tatician's Sword", ReforgeTypes.NO_REFORGE, 1, taticianSwordDesc,true, false, "", null, "", 0, "", "EPIC SWORD", 50, 100, 20, 0,0,0,0, 0,true);
        ember_rod = createInGameItem(Material.BLAZE_ROD, ChatColor.DARK_PURPLE + "Ember Rod", ReforgeTypes.NO_REFORGE, 1, null, true, true, "Fire Blast", emberRodAbilityDesc, "RIGHT CLICK", 150, "30s", "EPIC SWORD", 80, 35, 0 ,0 ,0,50,0, 0,true);
        frozen_scythe = createInGameItem(Material.IRON_HOE, ChatColor.BLUE + "Frozen Scythe", ReforgeTypes.NO_REFORGE, 1,null, false, true, "Ice Bolt", frozenScytheAbilityDesc, "RIGHT CLICK", 50, "", "RARE SWORD", 80,0,0,0,0,0,0, 0,true);
        golem_sword = createInGameItem(Material.IRON_SWORD, ChatColor.BLUE + "Golem Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Iron Punch", golemSwordAbilityDesc, "RIGHT CLICK", 70, "3s", "RARE SWORD", 80, 125, 0 ,0 ,0 ,0 ,0, 25, true);
        aspect_of_the_end = createInGameItem(Material.DIAMOND_SWORD, ChatColor.BLUE + "Aspect of the End", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Instant Transmission", aoteAbilitydesc, "RIGHT CLICK", 50, "", "RARE SWORD", 100, 100, 0, 0, 0, 0, 0, 0, true);
        raider_axe = createInGameItem(Material.IRON_AXE, ChatColor.BLUE + "Raider Axe", ReforgeTypes.NO_REFORGE, 1, raiderAxeDesc, false, false, "", null, "", 0,"", "RARE SWORD", 80, 50, 0, 0,0,0,0,0, true);
        revenant_falchion = createInGameItem(Material.DIAMOND_SWORD, ChatColor.BLUE + "Revenant Falchion", ReforgeTypes.NO_REFORGE, 1, revFalchionDesc, false, false, "", null, "", 0, "", "RARE SWORD", 90, 50, 0, 0, 0, 0, 0, 0, true);
        silver_fang = createInGameItem(Material.GHAST_TEAR, ChatColor.GREEN + "Silver Fang", ReforgeTypes.NO_REFORGE, 1, null, true, false, "", null, "", 0, "", "UNCOMMON SWORD", 100, 0, 0, 0, 0, 0,0, 0, true);
        shaman_sword = createInGameItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Shaman Sword", ReforgeTypes.NO_REFORGE, 1, shamanSwordDesc, false, false, "", null, "", 0, "", "EPIC SWORD", 100, 20, 0, 0, 0 ,0 ,5, 0 ,true);
        scorpion_foil = createInGameItem(Material.WOOD_SWORD, ChatColor.DARK_PURPLE + "Scorpion Foil", ReforgeTypes.NO_REFORGE, 1, scorpionFoilDesc, false, true, "Heartstopper", scorpionFoilAbilityDesc, "", 0, "", "EPIC SWORD", 100, 100, 0, 0 ,0 , 0, 0, 0, true);
        thick_scorpion_foil = createInGameItem(Material.WOOD_SWORD, ChatColor.GOLD + "Thick Scorpion Foil", ReforgeTypes.NO_REFORGE, 1, scorpionFoilDesc, false, true, "Heartstopper", scorpionFoilAbilityDesc, "", 0, "", "LEGENDARY SWORD", 100, 200, 0, 0 ,0 ,0 ,0,0, true);
        zombie_sword = createInGameItem(Material.IRON_SWORD, ChatColor.BLUE + "Zombie Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Instant Heal", zombieSwordAbilityDesc, "RIGHT CLICK", 0, "", "RARE SWORD", 100, 50, 0, 0, 0, 50, 0, 0, true);
        ornate_zombie_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.DARK_PURPLE + "Ornate Zombie Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Instant Heal", ornateZombieSwordAbilityDesc, "RIGHT CLICK", 70, "", "EPIC SWORD", 110, 60, 0, 0, 0, 50, 0, 0, true);
        end_stone_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.LIGHT_PURPLE + "End Stone Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Extreme Focus", end_stone_swordAbilityDesc, "RIGHT CLICK", 0, "", "EPIC SWORD", 120, 80, 0, 0, 0, 0, 0, 0, true);
        //recluse_fang = createInGameItem(Material.IRON_SWORD, ChatColor.BLUE + "Recluse Fang", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Squash Em'", ItemUtil.addLoreMessage("§7Squash §eSpiders §7to accumulate §c❁Strength §7against them. §c+1 §c❁Strength§7 per §a40 §7squashed".replaceAll(" ", " "), recluse_fangAbilityDesc), "", 0, "", "RARE SWORD", 120, 30, 0, 20, 0, 0, 0, 0, true);
        //reaper_falchion = createInGameItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Reaper Falchion", ReforgeTypes.NO_REFORGE, 1, ItemUtil.addLoreMessage("§7Heal §c10❤ HP§7 per hit. §7Deal §a+200% damage to Zombies. §7Receive §a20% less damage from Zombies when held.", reaper_falchionLore), false, false, "", null, "", 0, "", "EPIC SWORD", 120, 100, 0, 0, 0, 200, 0, 0, true);
        //pooch_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.GOLD + "Pooch Sword", ReforgeTypes.NO_REFORGE, 1, ItemUtil.addLoreMessage("§7Deal §c+1 §7Damage per §c50 §cmax ❤§7. Receive §a-20% §7damage from wolves. Gain §c+150❁ Strength §7against wolves.".replaceAll(" ", " "), pooch_SwordLore), false, false, "", null, "", 0, "", "LEGENDARY SWORD", 120, 20, 0, 0, 0, 0, 5, 0, true);
        //edibleMace = createInGameItem(Material.MUTTON, ChatColor.BLUE + "Edible Mace", ReforgeTypes.NO_REFORGE, 1, null, false, true, "ME SMASH HEAD", ItemUtil.addLoreMessage("§7Your next attack deals double damage and §7weakens animals, making them deal §7-35% damage for §a30 §7seconds." + "\n §8Debuff §8doesn't stack.", edible_MaceAbliityDesc), "RIGHT CLICK", 100, "", "RARE SWORD", 125, 25, 0, 0, 0, 0, 0, 0, true);

        swordMap.put("aspect_of_the_jerry", aspect_of_the_jerry);
        swordMap.put("fancy_sword", fancy_sword);
        swordMap.put("rogue_sword", rogue_sword);
        swordMap.put("spider_sword", spider_sword);
        swordMap.put("undead_sword", undead_sword);
        swordMap.put("end_sword", end_sword);
        swordMap.put("cleaver", cleaver);
        swordMap.put("flaming_sword", flaming_sword);
        swordMap.put("prismarine_blade", prismarine_blade);
        swordMap.put("hunter_knife", hunter_knife);
        swordMap.put("tatician_sword", tatician_sword);
        swordMap.put("thick_tatician_sword", thick_tatician_sword);
        swordMap.put("ember_rod", ember_rod);
        swordMap.put("frozen_scythe", frozen_scythe);
        swordMap.put("golem_sword", golem_sword);
        swordMap.put("aspect_of_the_end", aspect_of_the_end);
        swordMap.put("raider_axe", raider_axe);
        swordMap.put("revenant_falchion", revenant_falchion);
        swordMap.put("silver_fang", silver_fang);
        swordMap.put("shaman_sword", shaman_sword);
        swordMap.put("scorpion_foil", scorpion_foil);
        swordMap.put("thick_scorpion_foil", thick_scorpion_foil);
        swordMap.put("zombie_sword", zombie_sword);
        swordMap.put("ornate_zombie_sword", ornate_zombie_sword);
        swordMap.put("end_stone_sword", end_stone_sword);
        swordMap.put("recluse_fang", recluse_fang);
        swordMap.put("reaper_falchion", reaper_falchion);
        swordMap.put("pooch_sword", pooch_sword);
        swordMap.put("edible_mace", edibleMace);
    }

    public static ItemStack createBasicItem(Material mat, String name, List<String> lore, short data, boolean enchantmentGlint, int amount){
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setDurability(data);
        if(enchantmentGlint == true){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createBasicHead(String itemName, List<String> lore, int amount, String ID, int maxStackSize){
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount);
        item.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        IDtoSkull(item, ID);
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        setMaxStackSize(CraftItemStack.asNMSCopy(item).getItem(), maxStackSize);
        return item;
    }

    public static ItemStack createBasicLeatherArmor(Material mat, String name, Color color, List<String> lore, int amount, int maxStackSize){
        ItemStack item = new ItemStack(mat, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setLore(lore);
        meta.setColor(color);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        setMaxStackSize(CraftItemStack.asNMSCopy(item).getItem(), 1);
        return item;
    }

    public static ItemStack createArmorHead(String headName, String itemName, List<String> description, int amount, String rarity, boolean reforgeAble, int damage, int strength, int critChance, int critDMG, int atkSpeed, int health, int defense, int speed, int intelligence, boolean fullSetBonus, String fsbName, List<String> fsbDesc) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount);
        item.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        List<String> lore = new ArrayList<>();

        if(damage != 0){
            lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if(strength != 0){
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + strength);
        }
        if(critChance != 0){
            lore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if(critDMG != 0){
            lore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + critDMG + "%");
        }
        if(atkSpeed != 0){
            lore.add(ChatColor.GRAY + "Attack Speed: " + ChatColor.RED + "+" + atkSpeed + "%");
        }
        lore.add("");
        if(health != 0){
            lore.add(ChatColor.GRAY + "Health: " + ChatColor.GREEN + "+" + health + " HP");
        }
        if(defense != 0){
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        }
        if(speed != 0){
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
        }
        if(intelligence != 0){
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+25");
        }
        if(description != null){
            lore.add("");
            for(String loop : description){
                lore.add(loop);
            }
        }
        if(description == null && fullSetBonus == true){
            lore.add("");
        }
        if(description != null && fullSetBonus == true){
            lore.add("");
        }
        if(fullSetBonus){
            lore.add(ChatColor.GOLD + "Full Set Bonus: " + fsbName);
            for(String loop : fsbDesc){
                lore.add(loop);
            }
            lore.add("");
        }
        if(reforgeAble == true){
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        }
        if(rarity != null){
            if(rarity.contains("LEGENDARY") || rarity.contains("legendary")){
                lore.add("" + ChatColor.GOLD + ChatColor.BOLD + rarity.toUpperCase());
            } else if(rarity.contains("EPIC") || rarity.contains("epic")){
                lore.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + rarity.toUpperCase());
            } else if(rarity.contains("RARE") || rarity.contains("rare")){
                lore.add("" + ChatColor.BLUE + ChatColor.BOLD + rarity.toUpperCase());
            } else if(rarity.contains("UNCOMMON") || rarity.contains("uncommon")) {
                lore.add("" + ChatColor.GREEN + ChatColor.BOLD + rarity.toUpperCase());
            } else if(rarity.contains("COMMON") || rarity.contains("common")) {
                lore.add("" + ChatColor.WHITE + ChatColor.BOLD + rarity.toUpperCase());
            }
        }
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        meta.setDisplayName(itemName);
        meta.setOwner(headName);
        item.setItemMeta(meta);
        setMaxStackSize(CraftItemStack.asNMSCopy(item).getItem(), 1);
        return item;
    }

    public static ItemStack createArmorPiece(Material mat, String name, Color color, ReforgeTypes reforge, Short data, String rarity, int amount, List<String> description, boolean enchantGlint, boolean fullSetBonus, String fsbName, List<String> fsbDesc, int damage, int strength, int critChance, int critDMG, int atkSpeed, int health, int defense, int speed, int intelligence, boolean reforgeAble){
        ItemStack item = new ItemStack(mat, amount);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(color);
        List<String> lore = new ArrayList<>();
        if(damage != 0){
            lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if(strength != 0){
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + strength);
        }
        if(critChance != 0){
            lore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if(critDMG != 0){
            lore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + critDMG + "%");
        }
        if(atkSpeed != 0){
            lore.add(ChatColor.GRAY + "Attack Speed: " + ChatColor.RED + "+" + atkSpeed + "%");
        }
        lore.add("");
        if(health != 0){
            lore.add(ChatColor.GRAY + "Health: " + ChatColor.GREEN + "+" + health + " HP");
        }
        if(defense != 0){
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        }
        if(speed != 0){
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
        }
        if(intelligence != 0){
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+25");
        }
        if(description != null){
            lore.add("");
            for(String loop : description){
                lore.add(loop);
            }
        }
        if(description == null && fullSetBonus == true){
            lore.add("");
        }
        if(description != null && fullSetBonus == true){
            lore.add("");
        }
        if(fullSetBonus){
            lore.add(ChatColor.GOLD + "Full Set Bonus: " + fsbName);
            for(String loop : fsbDesc){
                lore.add(loop);
            }
            lore.add("");
        }
        if(reforgeAble == true){
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        }
        if(rarity.contains("LEGENDARY") || rarity.contains("legendary")){
            lore.add("" + ChatColor.GOLD + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("EPIC") || rarity.contains("epic")){
            lore.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("RARE") || rarity.contains("rare")){
            lore.add("" + ChatColor.BLUE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("UNCOMMON") || rarity.contains("uncommon")) {
            lore.add("" + ChatColor.GREEN + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("COMMON") || rarity.contains("common")) {
            lore.add("" + ChatColor.WHITE + ChatColor.BOLD + rarity.toUpperCase());
        }

        if(enchantGlint){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
    public static ItemStack createInGameItem(Material mat, String name, ReforgeTypes reforge, int amount, List<String> description, boolean enchantGlint, boolean hasAbility, String abilityName, List<String> abilityDesc, String abilityType, int abilityCost, String abilityCD, String rarity, int damage, int strength, int critChance, int critDMG, int atkSpeed, int intelligence, int speed, int defense, boolean reforgeAble){
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(damage != 0){
            lore.add(ChatColor.GRAY + "Damage: " + ChatColor.RED + "+" + damage);
        }
        if(strength != 0){
            lore.add(ChatColor.GRAY + "Strength: " + ChatColor.RED + "+" + strength);
        }
        if(critChance != 0){
            lore.add(ChatColor.GRAY + "Crit Chance: " + ChatColor.RED + "+" + critChance + "%");
        }
        if(critDMG != 0){
            lore.add(ChatColor.GRAY + "Crit Damage: " + ChatColor.RED + "+" + critDMG + "%");
        }
        if(atkSpeed != 0){
            lore.add(ChatColor.GRAY + "Attack Speed: " + ChatColor.RED + "+" + atkSpeed + "%");
        }
        if(speed != 0 && intelligence != 0 && defense != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + intelligence);
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        } else if(intelligence != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Intelligence: " + ChatColor.GREEN + "+" + intelligence);
        } else if(speed != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Speed: " + ChatColor.GREEN + "+" + speed);
        } else if(defense != 0){
            lore.add("");
            lore.add(ChatColor.GRAY + "Defense: " + ChatColor.GREEN + "+" + defense);
        }
        lore.add("");
        if (description != null){
            for (String s : description){
                lore.add(s);
            }
        }
        if(hasAbility){
            lore.add(ChatColor.GOLD + "Item Ability: " + abilityName + " " + ChatColor.YELLOW + ChatColor.BOLD  + abilityType);
            for(String temp : abilityDesc){
                lore.add(temp);
            }
            if(abilityCost != 0){
                lore.add(ChatColor.DARK_GRAY + "Mana Cost: " + ChatColor.AQUA + abilityCost);
            }
            if(abilityCD != ""){
                lore.add(ChatColor.DARK_GRAY + "Cooldown: " + ChatColor.GREEN + abilityCD);
            }
            lore.add("");
        }
        if(reforgeAble == true){
            lore.add(ChatColor.DARK_GRAY + "This item can be reforged!");
        }
        if(rarity.contains("LEGENDARY") || rarity.contains("legendary")){
            lore.add("" + ChatColor.GOLD + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("EPIC") || rarity.contains("epic")){
            lore.add("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("RARE") || rarity.contains("rare")){
            lore.add("" + ChatColor.BLUE + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("UNCOMMON") || rarity.contains("uncommon")) {
            lore.add("" + ChatColor.GREEN + ChatColor.BOLD + rarity.toUpperCase());
        } else if(rarity.contains("COMMON") || rarity.contains("common")) {
            lore.add("" + ChatColor.WHITE + ChatColor.BOLD + rarity.toUpperCase());
        }

        if(!(reforge == ReforgeTypes.NO_REFORGE)){
            meta.setDisplayName(reforge + " " +name);
        } else{
            meta.setDisplayName(name);
        }
        meta.setLore(lore);
        if(enchantGlint){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createMaterialItem(Material material, short durability, boolean enchGlint, String rarity, boolean brewingIngredient, String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setDurability(durability);
        if (enchGlint){
            meta.addEnchant(Enchantment.LURE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (brewingIngredient){
            lore.add(ChatColor.DARK_GRAY + "Brewing Ingredient");
        }
        meta.setDisplayName(ChatColor.getLastColors(rarity) + "Enchanted " + name);
        lore.add(ChatColor.getLastColors(rarity) + ChatColor.BOLD + ChatColor.stripColor(rarity));
        meta.setLore(lore);
        item.setItemMeta(meta);
        materialMap.put(ChatColor.stripColor(item.getItemMeta().getDisplayName()), item);
        return item;
    }

    public static ItemStack createMaterialItem(Material material, int durability, boolean enchGlint, String rarity, boolean brewingIngredient, String name){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        item.setDurability((short) durability);
        if (enchGlint){
            meta.addEnchant(Enchantment.LURE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (brewingIngredient){
            lore.add(ChatColor.DARK_GRAY + "Brewing Ingredient");
        }
        meta.setDisplayName(ChatColor.getLastColors(rarity) + "Enchanted " + name);
        lore.add(ChatColor.getLastColors(rarity) + ChatColor.BOLD + ChatColor.stripColor(rarity));
        meta.setLore(lore);
        item.setItemMeta(meta);
        materialMap.put(ChatColor.stripColor(item.getItemMeta().getDisplayName()), item);
        return item;
    }

    public static ItemStack createNavigationArrow(String direction, int newPageNumber){
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Page " + newPageNumber);
        if(direction == "next"){
            meta.setDisplayName(ChatColor.GREEN + "Next Page");
        } else if(direction == "previous"){
            meta.setDisplayName(ChatColor.GREEN + "Previous Page");
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createPageBackArrow(String newPage){
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "To " + newPage);
        meta.setDisplayName(ChatColor.GREEN + "Go Back");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createExitBarrier(){
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Close");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createEmptySpace(){
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static void setMaxStackSize(net.minecraft.server.v1_8_R3.Item item, int i){
        try {

            Field field = Item.class.getDeclaredField("maxStackSize");
            field.setAccessible(true);
            field.setInt(item, i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ItemStack IDtoSkull(ItemStack head, String id) {
        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(new String(org.apache.commons.codec.binary.Base64.decodeBase64(id))).getAsJsonObject();
        String skinUrl = o.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject().get("url").getAsString();
        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = org.apache.commons.codec.binary.Base64.encodeBase64(("{textures:{SKIN:{url:\"" + skinUrl + "\"}}}").getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public static ItemStack createSkyblockMenu(){
        ItemStack menu = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = menu.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
        menu.setItemMeta(itemMeta);
        return menu;
    }

    public static ItemStack createCoin(int amount){
        ItemStack coin = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
        ItemMeta coinMeta = coin.getItemMeta();

        if (amount <= 5){
            coinMeta.setDisplayName(ChatColor.GOLD + "coin_iron_" + amount);
            IDtoSkull(coin, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzhhNDY1MGVlM2I3NDU5NDExMjQyNjAwNDI0NmRmNTMxZTJjNjhiNmNhNDdjYWI4ZmUyMzIzYjk3OTBhMWE1ZSJ9fX0=");
        }else if (amount >= 10 && amount <= 50){
            coinMeta.setDisplayName(ChatColor.GOLD + "coin_gold_" + amount);
            IDtoSkull(coin, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhMDg3ZWI3NmU3Njg3YTgxZTRlZjgxYTdlNjc3MjY0OTk5MGY2MTY3Y2ViMGY3NTBhNGM1ZGViNmM0ZmJhZCJ9fX0=");
        }else if (amount >= 51){
            coinMeta.setDisplayName(ChatColor.GOLD + "coin_diamond_" + amount);
            IDtoSkull(coin, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RlZTYyMWViODJiMGRhYjQxNjYzMzBkMWRhMDI3YmEyYWMxMzI0NmE0YzFlN2Q1MTc0ZjYwNWZkZGYxMGExMCJ9fX0=");
        }

        coin.setItemMeta(coinMeta);
        return coin;
    }

    public static ItemStack createCollectionItem(int type, String collectionName, String collectionLevel, int percentageUnlocked, int collected, int maxCollected, List<String> coopPlayers, List<String> collectionRewards, Short data){
        ItemStack item = new ItemStack(type);
        ItemMeta meta = item.getItemMeta();
        String itemName = collectionName + " " + collectionLevel;
        item.setTypeId(type);
        item.setDurability(data);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "View all your " + collectionName + " Collection");
        lore.add(ChatColor.GRAY + "progress and rewards!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Collection Unlocked: " + ChatColor.YELLOW + percentageUnlocked + ChatColor.GOLD + "%");
        lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + collected + ChatColor.GOLD + "/" + ChatColor.YELLOW + maxCollected);
        lore.add("");
        lore.add(ChatColor.GRAY + "Co-op Contributions:");
        if(coopPlayers.size() >= 2){
            for(String s : coopPlayers){
                lore.add(ChatColor.RED + "[ADMIN] " + s);
            }
        } else{
            lore.add(coopPlayers.get(0));
        }
        lore.add(ChatColor.GRAY + "Total: " + ChatColor.YELLOW + collected);
        lore.add("");
        lore.add(ChatColor.GRAY + itemName + " Rewards:");
        if(collectionRewards.size() >= 2){
            for(String s : collectionRewards){
                lore.add(" " + s);
            }
        } else{
            lore.add(" " + collectionRewards.get(0));
        }
        lore.add("");
        lore.add(ChatColor.YELLOW + "Click to view!");
        meta.setDisplayName(ChatColor.YELLOW + itemName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}