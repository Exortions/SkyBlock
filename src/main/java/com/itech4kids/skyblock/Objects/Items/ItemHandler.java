package com.itech4kids.skyblock.Objects.Items;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itech4kids.skyblock.Enums.ReforgeTypes;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.itech4kids.skyblock.Util.Util;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.*;

public class ItemHandler {

    // Swords
    public static Map<String, ItemStack> swordMap;
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
    public static ItemStack ink_wand;
    public static ItemStack emerald_blade;
    public static ItemStack midas_staff;
    public static ItemStack axe_of_the_shredded;
    public static ItemStack soul_whip;
    public static ItemStack leaping_sword;
    public static ItemStack yeti_sword;
    public static ItemStack silk_edge_sword;
    public static ItemStack sword_of_revelations;
    public static ItemStack thick_sword_of_revelations;
    public static ItemStack daedalus_axe;
    public static ItemStack phantom_rod;
    public static ItemStack pigman_sword;
    public static ItemStack aspect_of_the_dragons;
    public static ItemStack midas_sword_50m;
    public static ItemStack reaper_scythe;

    //Bows
    public static Map<String, ItemStack> bowMap;
    public static ItemStack bow;
    public static ItemStack wither_bow;
    public static ItemStack decent_bow;
    public static ItemStack savanna_bow;
    public static ItemStack prismarine_bow;
    public static ItemStack ender_bow;
    public static ItemStack undead_bow;
    public static ItemStack machine_gun_bow;
    public static ItemStack sniper_bow;
    public static ItemStack soulstealer_bow;
    public static ItemStack explosive_bow;
    public static ItemStack magma_bow;
    public static ItemStack slime_bow;
    public static ItemStack super_undead_bow;
    public static ItemStack scorpion_bow;
    public static ItemStack hurricane_bow;
    public static ItemStack end_stone_bow;
    public static ItemStack death_bow;
    public static ItemStack spider_queens_stinger;
    public static ItemStack venoms_touch;
    public static ItemStack souls_rebound;
    public static ItemStack runaans_bow;
    public static ItemStack mosquito_bow;
    public static ItemStack spirit_bow;
    public static ItemStack bonemerang;
    public static ItemStack last_breath;

    //Dungeon Swords
    public static ItemStack super_cleaver;
    public static ItemStack bonzo_staff;
    public static ItemStack dreadlord_sword;
    public static ItemStack epic_dreadlord_sword;
    public static ItemStack zombie_soldier_cutlass;
    public static ItemStack epic_zombie_soldier_cutlass;
    public static ItemStack silent_death;
    public static ItemStack epic_silent_death;
    public static ItemStack conjuring;
    public static ItemStack epic_conjuring;
    public static ItemStack adaptive_blade;
    public static ItemStack crypt_witherlord_sword;
    public static ItemStack zombie_commander_whip;
    public static ItemStack hyper_cleaver;
    public static ItemStack spirit_sword;
    public static ItemStack zombie_knight_sword;
    public static ItemStack legendary_zombie_knight_sword;
    public static ItemStack spirit_sceptre;
    public static ItemStack legendary_spirit_sceptre;
    public static ItemStack ice_spray_wand;
    public static ItemStack epic_ice_spray_wand;
    public static ItemStack livid_dagger;
    public static ItemStack shadow_fury;
    public static ItemStack flower_of_truth;
    public static ItemStack fel_sword;
    public static ItemStack wither_cloak_sword;
    public static ItemStack necromancer_sword;
    public static ItemStack giants_sword;
    public static ItemStack necrons_blade;
    public static ItemStack hyperion;
    public static ItemStack astraea;
    public static ItemStack scylla;
    public static ItemStack valkyrie;

    // Armor
    public static HashMap<String, ItemStack> armorMap;

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

    public static ItemStack anglerHelmet;
    public static ItemStack anglerChestplate;
    public static ItemStack anglerLeggings;
    public static ItemStack anglerBoots;

    public static ItemStack pumpkinHelmet;
    public static ItemStack pumpkinChestplate;
    public static ItemStack pumpkinLeggings;
    public static ItemStack pumpkinBoots;

    public static ItemStack cactusHelmet;
    public static ItemStack cactusChestplate;
    public static ItemStack cactusLeggings;
    public static ItemStack cactusBoots;

    public static ItemStack leafletHelmet;
    public static ItemStack leafletChestplate;
    public static ItemStack leafletLeggings;
    public static ItemStack leafletBoots;

    public static ItemStack lapisHelmet;
    public static ItemStack lapisChestplate;
    public static ItemStack lapisLeggings;
    public static ItemStack lapisBoots;

    public static ItemStack minersOutfitHelmet;
    public static ItemStack minersOutfitChestplate;
    public static ItemStack minersOutfitLeggings;
    public static ItemStack minersOutfitBoots;

    public static ItemStack golemHelmet;
    public static ItemStack golemChestplate;
    public static ItemStack golemLeggings;
    public static ItemStack golemBoots;

    // Misc
    public static HashMap<String, ItemStack> miscMap;
    public static ItemStack grappling_hook;
    public static ItemStack maddox_batphone;
    public static SkullMeta maddox_meta;

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

    public static HashMap<String, ItemStack> vanillaMap;

    public static ArrayList<Map<String, ItemStack>> maps;

    public static void init(){
        initSwords();
        initArmor();
        initTools();
        initFishing();
        initMisc();
        initMaterials();
        initVanilla();
        initBow();
        maps = new ArrayList<>();
        maps.add(swordMap);
        maps.add(armorMap);
        maps.add(materialMap);
        maps.add(vanillaMap);
        maps.add(miscMap);
        maps.add(bowMap);
    }

    public static void initBow(){
        bowMap = new HashMap<>();

        bow = createInGameBow(Material.BOW, ChatColor.WHITE + "Bow", ReforgeTypes.NO_REFORGE, 1, null, false, false, null, null, null, 0, null, "COMMON BOW", 30, 0, 0, 0, 0, 0, 0, 0, true);
        wither_bow = createInGameBow(Material.BOW, ChatColor.GREEN + "Wither Bow", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Deals &a+100% &7damage", "&7to Withers and", "&7Wither Skeletons."), false, false, null, null, null, 0, null, "UNCOMMON BOW", 30, 0, 0, 0, 0, 0, 0, 0, true);
        decent_bow = createInGameBow(Material.BOW, ChatColor.GREEN + "Decent Bow", ReforgeTypes.NO_REFORGE, 1, null, false, false, null, null, null, 0, null, "UNCOMMON BOW", 45, 0, 0, 0, 0, 0, 0, 0, true);
        savanna_bow = createInGameBow(Material.BOW, ChatColor.GREEN + "Savanna Bow", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7All damage dealt with this", "&7bow is &adoubled&7."), false, false, null, null, null, 0, null, "UNCOMMON BOW", 50, 0, 0, 0, 0, 0, 0, 0, true);
        prismarine_bow = createInGameBow(Material.BOW, ChatColor.GREEN + "Prismarine Bow", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Will consume Prismarine", "&7Shards in your inventory or", "&7your Quiver to deal", "&7with &a+300% damage &7to Squids,", "&7Guardians, and Elder Guardians."), false, false, null, null, null, 0, null, "UNCOMMON BOW", 50, 25, 0, 0, 0, 0, 0, 0, true);
        ender_bow = createInGameBow(Material.BOW, ChatColor.BLUE + "Ender Bow", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Ender Warp", addAbililtyLore(createEmptySpace(), "&7Shoots an Ender Pearl.", "&7Upon landing, you deal damage to", "&7all Monsters in an &a8", "&7block radius for", "&a10.0% &7of their Health."), "RIGHT CLICK", 50, "45s", "RARE BOW",  60, 0, 0, 0, 0, 0, 0, 0, true);
        undead_bow = createInGameBow(Material.BOW, ChatColor.BLUE + "Undead Bow", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Deals &a+100% &7damage to", "&7Undead monsters."), false, false, null, null, null, 0, null, "RARE BOW", 80, 0, 0, 0, 0, 0, 0, 0, true);

    }

    public static void initArmor(){
        armorMap = new HashMap<String, ItemStack>();

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

        superior_dragon_helmet = createArmorHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzU1OGVmYmU2Njk3NjA5OWNmZDYyNzYwZDllMDUxNzBkMmJiOGY1MWU2ODgyOWFiOGEwNTFjNDhjYmM0MTVjYiJ9fX0=", ChatColor.GOLD + "Superior Dragon Helmet", null, 1, "LEGENDARY HELMET", true, 0, 10, 2, 10, 0, 90, 130, 3, 25, true, "Superior Blood", superiorFSBDesc);
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

        //pumpkinHelmet = createArmorPiece(Material.LEATHER_HELMET, ChatColor.WHITE + "Pumpkin Helmet", Color.ORANGE, ReforgeTypes.NO_REFORGE, 0, "COMMON HELMET", 1, )
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
        miscMap = new HashMap<String, ItemStack>();

        List<String> grapplingHookDesc = new ArrayList<>();
        grapplingHookDesc.add(ChatColor.GRAY + "Travel around in style using");
        grapplingHookDesc.add(ChatColor.GRAY + "this Grappling Hook.");
        grapplingHookDesc.add("");
        grapplingHookDesc.add(ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON");

        grappling_hook = createBasicItem(Material.FISHING_ROD, ChatColor.GREEN + "Grappling Hook", grapplingHookDesc, (short) 0, false, 1);
        skyblock_menu = createSkyblockMenu();

        miscMap.put("grappling_hook", grappling_hook);
        miscMap.put("skyblock_menu", skyblock_menu);
        miscMap.put("maddox_batphone", maddox_batphone);
    }

    public static void initVanilla(){
        vanillaMap = new HashMap<>();
        for (int i = 1; i < Material.values().length; ++i){
            ItemStack item = new ItemStack(Material.DIRT, 1);
            item.setTypeId(i);
            if (!item.getType().equals(Material.AIR) && !item.getType().equals(Material.BOW)) {
                ItemMeta meta = item.getItemMeta();
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                meta.setLore(lore);
                item.setItemMeta(meta);
                vanillaMap.put(item.getType().name().toLowerCase() + "_" + item.getDurability(), item);
            }
        }
    }

    public static void initSwords(){
        swordMap = new HashMap<String, ItemStack>();

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
        end_stone_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.DARK_PURPLE + "End Stone Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Extreme Focus", end_stone_swordAbilityDesc, "RIGHT CLICK", 0, "", "EPIC SWORD", 120, 80, 0, 0, 0, 0, 0, 0, true);
        recluse_fang = createInGameItem(Material.IRON_SWORD, ChatColor.BLUE + "Recluse Fang", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Squash 'em", addAbililtyLore(createEmptySpace(), "&7Squash Spiders to accumulate", "&7strength against them.", "&7Bonus: &c+0❁", "&8+1 strength per 40 squashed"), "", 0, "", "RARE SWORD", 120, 30, 0, 20, 0, 0, 0, 0, true);
        reaper_falchion = createInGameItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Reaper Falchion", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Heal &c10hp &7per hit.", "&7Deal &a+200% &7 damage to Zombies.", "&7Receive &a20% &7less damage", "&7from Zombies when held."), false, false, "", null, "", 0, "", "EPIC SWORD", 120, 100, 0 , 0, 0 ,0, 0, 0, true);
        pooch_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.GOLD + "Pooch Sword", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Deal &c+1 Damage &7per &c50 max hp&7.", "&7Receive &a-20% &7damage from wolves.", "&7Gain &c+150 Strength &7against wolves."), false, false, null, null, null, 0, null, "LEGENDARY SWORD", 120, 20, 0, 0, 0, 0, 5, 0, true);
        edibleMace = createInGameItem(Material.MUTTON, ChatColor.BLUE + "Edible Mace", ReforgeTypes.NO_REFORGE, 1, null, false, true, "ME SMASH HEAD", addAbililtyLore(createEmptySpace(), "&7Your next attack deals &cdouble", "&cdamage &7and weakens animals,", "&7making them deal &c-35% &cdamage", "&7for &a30 &7seconds.", "&8Debuff doesn't stack"), "RIGHT CLICK", 100, "", "EPIC SWORD", 125, 25, 0, 0, 0, 0, 0, 0, true);
        ink_wand = createInGameItem(Material.STICK, ChatColor.DARK_PURPLE + "Ink Wand", ReforgeTypes.NO_REFORGE, 1, null, true, true, "Ink Bomb", addAbililtyLore(createEmptySpace(), "&7Shoot and ink bomb in front of", "&7you dealing &a10,000 &7damage", "&7and giving blindness!"), "RIGHT CLICK", 60, "30s", "EPIC SWORD", 130, 90, 0, 0, 0, 0, 0, 0, true);
        emerald_blade = createInGameItem(Material.EMERALD, ChatColor.DARK_PURPLE + "Emerald Blade", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7A powerful blade made from pure", "&2Emeralds&7. This blade becomes", "&7stronger as you carry more", "&6coins &7in your purse.", "", "&7Current Damage Bonus: &a0.0"), false, false, null, null, null, 0, null, "EPIC SWORD", 130, 0, 0, 0, 0, 0, 0, 0, true);
        midas_staff = createInGameItem(Material.GOLD_SPADE, ChatColor.GOLD + "Midas Staff", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&6Item Ability: Molten Mave &e&lRIGHT CLICK", "&7Cast a wave of molten gold in", "&7the direction you are facing!", "&7Deals up to &c16,000 &7damage.", "&8Mana Cost: &3500", "&8Cooldown: &a1s", "", "&6Item Ability: Greed", "&7The &3ability damage bonus &7of", "&7this item is dependent on the", "&7price paid for it at the &5Dark", "&5Auction&7!"), false, false, null, null, null, 0, null, "LEGENDARY SWORD", 150, 0, 0, 0, 0, 50, 0, 0, true);
        axe_of_the_shredded = createInGameItem(Material.DIAMOND_AXE, ChatColor.GOLD + "Axe of the Shredded", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Heal &c50hp &7per hit.", "&7Deal &a+250% &7 damage to Zombies.", "&7Receive &a25% &7less damage", "&7from Zombies when held."), false, true, "Throw", addAbililtyLore(createEmptySpace(), "&7Throw your axe damaging all", "&7enemies in its path dealing", "&c10% &7melee damage.", "&7Consecutive throws stack &c2x", "&7damage but cost &92x &7mana up", "&7to 16x"), "RIGHT CLICK", 20, "", "LEGENDARY SWORD", 140, 115, 0, 0, 0, 0, 0, 0, true);
        soul_whip = createInGameItem(Material.FISHING_ROD, ChatColor.GOLD + "Soul Whip", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Flay", addAbililtyLore(createEmptySpace(), "&7Flay you whip in an arc.", "&7dealing your melee damage to all", "&7enemies in its path."), "RIGHT CLICK", 0, "", "LEGENDARY SWORD", 145, 175, 0, 0, 0, 0, 0, 0, true);
        leaping_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.DARK_PURPLE + "Leaping Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Leap", addAbililtyLore(createEmptySpace(), "&7Leap into the air and deal", "&c350 &7damage to nearby enemies", "&7upon landing on the ground.", "&7Damaged enemies will also be", "&7frozen for &a1 &7second."), "RIGHT CLICK", 50, "1s", "EPIC SWORD", 150, 100, 25, 0, 0, 0, 0, 0, true);
        yeti_sword = createInGameItem(Material.IRON_SWORD, ChatColor.GOLD + "Yeti Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Terrain Toss", addAbililtyLore(createEmptySpace(), "&7Throws a chunk of terrain in the", "&7direction you are facing! Deals", "&7up to &c15,000 &7damage."), "RIGHT CLICK", 125, "1s", "LEGENDARY SWORD", 150, 150, 0, 0, 0, 50, 0, 0, true);
        silk_edge_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.GOLD + "Silk Edge Sword", ReforgeTypes.NO_REFORGE, 1, null, false,  true, "Leap", addAbililtyLore(createEmptySpace(), "&7Leap into the air and deal", "&a400 &7base &bMagic Damage &7to nearby", "&7enemies upon landing on the", "&7ground. Damaged enemies will", "&7also be frozen for &a1", "&7second."), "RIGHT CLICK", 50, "1s", "LEGENDARY SWORD", 175, 125, 0, 25, 0, 0, 0, 0, true);
        sword_of_revelations = createInGameItem(Material.WOOD_SWORD, ChatColor.DARK_PURPLE + "Sword of Revelations", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Deals &a+200% &7damage against", "&7mythological creatures and Minos", "&7followers but receive &c+75%", "&7damage from them."), false, false, null, null, null, 0, null, "EPIC SWORD", 180, 50, 0, 0, 0, 0, 0, 0, true);
        thick_sword_of_revelations = createInGameItem(Material.WOOD_SWORD, ChatColor.GOLD + "Thick Sword of Revelations", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Deals &a+200% &7damage against", "&7mythological creatures and Minos", "&7followers but receive &c+75%", "&7damage from them."), false, false, null, null, null, 0, null, "LEGENDARY SWORD", 180, 50, 0, 0, 0, 0, 0, 0, true);
        daedalus_axe = createInGameItem(Material.GOLD_AXE, ChatColor.GOLD + "Daedalus Axe", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Gains &c+4 Damage &7per Taming", "&7level", "&7Copies the stats from your", "&7active pet.", "", "&7Earn &6+35 coins &7from", "&7monster kills", "", "&7Deals &a+200% &7damage against", "&7mythological creatures and Minos", "&7folllowers"), false, false, null, null, null,0, null, "LEGENDARY SWORD", 0, 0, 0, 0, 0, 0, 0, 0, true);
        phantom_rod = createInGameItem(Material.FISHING_ROD, ChatColor.GOLD + "Phantom Rod", ReforgeTypes.NO_REFORGE, 1, addAbililtyLore(createEmptySpace(), "&6Item Ability: Phantom Impel &e&lLEFT CLICK", "&7Terrify hooked enemies pushing", "&7them away and dealing massive", "&7damage.", "", "&7Increases fishing speed by &965%", "", "&8This item can be reforged!", "&cRequires &aFishing Skill 21"), false, false, null, null, null, 0, null, "LEGENDARY FISHING WEAPON", 200, 125, 0, 0, 0, 0, 0, 0, false);
        pigman_sword = createInGameItem(Material.GOLD_SWORD, ChatColor.GOLD + "Pigman Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Burning Souls", addAbililtyLore(createEmptySpace(), "&7Gain &a+300 Defense &7for", "&a5s &7and cast a vortex of flames", "&7towards enemies, dealing up to", "&c30,000 &7over &a5 &7seconds."), "RIGHT CLICK", 400, "5s", "LEGENDARY SWORD", 200, 100, 5, 30, 0, 300, 0, 0, true);
        aspect_of_the_dragons = createInGameItem(Material.DIAMOND_SWORD, ChatColor.GOLD + "Aspect of the Dragons", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Dragon Rage", addAbililtyLore(createEmptySpace(), "&7All Monsters in front of you", "&7take &a### &7damage. Hit", "&7monsters take large knockback."), "RIGHT CLICK", 100, "5s", "LEGENDARY SWORD", 225, 100, 0, 0, 0, 0, 0, 0, true);
        midas_sword_50m = createInGameItem(Material.GOLD_SWORD, ChatColor.GOLD + "Midas Sword", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&6Item Ability: Greed", "&7The strength and damage bonus of", "&7This item is dependent on the", "&7price paid for it at the &5Dark", "&5Auction&7!", "&7The maximum bonus of this item", "&7is &c120 &7if the bid was", "&650,000,000 Coins &7or higher!", "", "&7Price paid: &650,000,000 Coins", "&7Strength Bonus: &c120", "&7Damage Bonus: &c120"), false, false, null, null, null, 0, null, "LEGENDARY SWORD", 270, 120, 0, 0, 0, 0, 0, 0, true);
        reaper_scythe = createInGameItem(Material.DIAMOND_HOE, ChatColor.GOLD + "Reaper Scythe", ReforgeTypes.NO_REFORGE, 1, addAbililtyLore(createEmptySpace(), "&6Item Ability: Raise Souls &e&lRIGHT CLICK", "&7Monsters you kill using this", "&7item will drop their soul. You", "&7can click on their souls on the", "&7ground using this item to abosrb", "&7them and then spawn to", "&7fight by your side", "", "&7Mana cost is based on the power", "&7of the monsters that you summon.", "&7Shift right-click to view and", "&7remove souls from this item. If", "&7your summoned monster dies the", "&7soul will be removed.", "&8Cooldown: &e1s", "", "&7Max Souls: &d3", "", "&c☠ Requires &5Zombie Slayer 7", "&8This item can be reforged!"), false, false, null, null, null, 0, null, "LEGENDARY SWORD", 333, 0, 0, 0, 0, 0, 10, 0, false);

// Dungeon Swords
        super_cleaver = createInGameDungeonItem(Material.GOLD_SWORD, ChatColor.BLUE + "Super Cleaver", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Cleave", addAbililtyLore(createEmptySpace(), "&7When hitting an entity, monsters", "&7in a &a3 &7block range will be hit for", "&7a portion of that damage too."), "RIGHT CLICK", 0, "", "RARE DUNGEON SWORD", 0, 85, 20, 0, 0, 0, 0, 0, 0, true);
        bonzo_staff = createInGameDungeonItem(Material.BLAZE_ROD, ChatColor.BLUE + "Bonzo's Staff", ReforgeTypes.NO_REFORGE, 1, null, true, true, "Showtime", addAbililtyLore(createEmptySpace(), "&7Shoots ballons that create a", "&7large explosion on impact,", "&7dealing up to &c1,000 &7damage."), "RIGHT CLICK", 100, "", "RARE DUNGEON SWORD", 285, 160, 0, 0, 0, 0, 250, 0, 0, true);
        dreadlord_sword = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.BLUE + "Dreadlord Sword ", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Dreadlord", addAbililtyLore(createEmptySpace(), "&7Shoots a skull that does", "&aX &7damage."), "RIGHT CLICK", 40, "", "RARE DUNGEON SWORD", 69420, 52, 50, 0, 0, 0, 85, 0, 0, true);
        epic_dreadlord_sword = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Dreadlord Sword", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Dreadlord", addAbililtyLore(createEmptySpace(), "&7Shoots a skull that does", "&aX &7damage."), "RIGHT CLICK", 40, "", "EPIC DUNGEON SWORD", 69420, 120, 75, 0, 0, 0, 210, 0, 0, true);
        zombie_soldier_cutlass = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.BLUE + "Zombie Soldier Cutlass ", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Love Tap", addAbililtyLore(createEmptySpace(), "&7Heals you for &c+10❤ Health", "&7when you hit an entity!"), "", 0, "", "RARE DUNGEON SWORD", 69420, 60, 30, 0, 0, 100, 0, 0, 0, true);
        epic_zombie_soldier_cutlass = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Zombie Soldier Cutlass", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Love Tap", addAbililtyLore(createEmptySpace(), "&7Heals you for &c+10❤ Health", "&7when you hit an entity!"), "", 0, "", "EPIC DUNGEON SWORD", 69420, 120, 60, 0, 0, 100, 0, 0, 0, true);
        silent_death = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.BLUE + "Silent Death ", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Shadowstep", addAbililtyLore(createEmptySpace(), "&7Teleport behind the enemy you are looking at,", "&7gaining &c+25❁ Strength &7for &a10 &7seconds. Max", "&7range of 20 blocks. Cooldown resets on kills."), "RIGHT CLICK", 0, "60s", "RARE DUNGEON SWORD", 69420, 100, 44, 2, 13, 0, 0, 0, 0, false);
        epic_silent_death = createInGameDungeonItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Silent Death", ReforgeTypes.NO_REFORGE, 1, null, false, true, "Shadowstep", addAbililtyLore(createEmptySpace(), "&7Teleport behind the enemy you are looking at,", "&7gaining &c+25❁ Strength &7for &a10 &7seconds. Max", "&7range of 20 blocks. Cooldown resets on kills."), "RIGHT CLICK", 0, "60s", "EPIC DUNGEON SWORD", 69420, 210, 78, 3, 24, 0, 0, 0, 0, false);
        conjuring = createInGameDungeonItem(Material.STONE_SWORD, ChatColor.BLUE + "Conjuring ", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Reduces the cooldown of Guided", "&7Sheep by &65 &7seconds, and", "&7prevents Guided Sheep from", "&7spawning randomly while holding", "&7this item."), false, false, "",null, null, 0, "", "RARE DUNGEON SWORD", 69420, 84, 45, 0, 0, 0, 109, 0, 0, true);
        epic_conjuring = createInGameDungeonItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Conjuring", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7Reduces the cooldown of Guided", "&7Sheep by &65 &7seconds, and", "&7prevents Guided Sheep from", "&7spawning randomly while holding", "&7this item."), false, false, "",null, null, 0, "", "RARE DUNGEON SWORD", 69420, 158, 84, 0, 0, 0, 210, 0, 0, true);
        adaptive_blade = createInGameDungeonItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Adaptive Blade", ReforgeTypes.NO_REFORGE, 1, addLore(createEmptySpace(), "&7As a weapon created by &cScarf&7,", "&7it automatically adapts to its user", "&7inside Dungeons.", "", "&bBerserk&7: +&c35❁ Strength &7+&f✦ Speed", "&bHealer&7: +&a20% &7HP regen & +&a5 &7HP/s aura", "&bMage&7: +&b200✎ Intelligence", "&bTank&7: +&a100❈ Defense &7+&f5❂ True Defense", "&bArcher&7: +&9100☠ Crit Damage", "", "&eRight-click to use your class ability!"), false, false, null, null, null, 0, "", "EPIC DUNGEON SWORD", 69420, 170, 0, 0, 0, 0, 0, 0, 0, false);
    }

    public static ItemStack createInGameDungeonItem(Material mat, String name, ReforgeTypes reforge, int amount, List<String> description, boolean enchantGlint, boolean hasAbility, String abilityName, List<String> abilityDesc, String abilityType, int abilityCost, String abilityCD, String rarity, int gearScore, int damage, int strength, int critChance, int critDMG, int atkSpeed, int intelligence, int speed, int defense, boolean reforgeAble){
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(gearScore != 0 && gearScore != 69420){
            lore.add(ChatColor.GRAY + "Gear Score: " + ChatColor.LIGHT_PURPLE + gearScore);
        }
        if(gearScore == 69420){
            lore.add(ChatColor.GRAY + "Gear Score: " + ChatColor.LIGHT_PURPLE + "#");
        }
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
        swordMap.put(ChatColor.stripColor(name.toLowerCase().replaceAll(" ", "_")), item);
        return item;
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

    public static ItemStack createArmorHead(String id, String itemName, List<String> description, int amount, String rarity, boolean reforgeAble, int damage, int strength, int critChance, int critDMG, int atkSpeed, int health, int defense, int speed, int intelligence, boolean fullSetBonus, String fsbName, List<String> fsbDesc) {
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
        item.setItemMeta(meta);
        IDtoSkull(item, id);
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
        armorMap.put(name.toLowerCase().replaceAll(" ", "_"), item);
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
        swordMap.put(ChatColor.stripColor(name.toLowerCase().replaceAll(" ", "_")), item);
        return item;
    }

    public static ItemStack createInGameBow(Material mat, String name, ReforgeTypes reforge, int amount, List<String> description, boolean enchantGlint, boolean hasAbility, String abilityName, List<String> abilityDesc, String abilityType, int abilityCost, String abilityCD, String rarity, int damage, int strength, int critChance, int critDMG, int atkSpeed, int intelligence, int speed, int defense, boolean reforgeAble){
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
        bowMap.put(ChatColor.stripColor(name.toLowerCase().replaceAll(" ", "_")), item);
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
        ItemStack item1 = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item1.getItemMeta();
        List<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
        ItemUtil.addLoreMessage("View all of your Skyblock progress, including your Skills, Collections, Recipes, and more!", lore);
        itemMeta.setLore(lore);
        item1.setItemMeta(itemMeta);
        return item1;
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

    public static ItemStack createCollectionItem(int type, String collectionName, String collectionLevel, int percentageUnlocked, String collected, String maxCollected, List<String> coopPlayers, String collectionRewards, Short data){
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
        lore.add(" " + collectionRewards);
        lore.add("");
        lore.add(ChatColor.YELLOW + "Click to view!");
        meta.setDisplayName(ChatColor.YELLOW + itemName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createMerchantItem(ItemStack itemStack, int coins){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore() != null){
            List<String> lore = meta.getLore();
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Cost");
            lore.add(ChatColor.GOLD + "" + coins + " Coins");
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to trade!");
            if (itemStack.getMaxStackSize() == 64) {
                lore.add(ChatColor.YELLOW + "Right-Click for more trading options!");
            }
            meta.setLore(lore);
        }else{
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Cost");
            lore.add(ChatColor.GOLD + "" + coins + " Coins");
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to trade!");
            if (itemStack.getMaxStackSize() == 64) {
                lore.add(ChatColor.YELLOW + "Right-Click for more trading options!");
            }
            meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createMerchantItem(ItemStack itemStack, int coins, boolean b){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta.getLore() != null){
            List<String> lore = meta.getLore();
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Cost");
            lore.add(ChatColor.GOLD + "" + coins + " Coins");
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to trade!");
            meta.setLore(lore);
        }else{
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Cost");
            lore.add(ChatColor.GOLD + "" + coins + " Coins");
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "Click to trade!");
            meta.setLore(lore);
        }
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack merchantItemToItemStack(ItemStack itemStack1){
        ItemStack itemStack = new ItemStack(itemStack1);
        List<String> lore = itemStack.getItemMeta().getLore();
        ItemMeta meta = itemStack.getItemMeta();
        if (itemStack.getMaxStackSize() > 1) {
            lore.remove(lore.size() - 6);
            lore.remove(lore.size() - 5);
            lore.remove(lore.size() - 4);
            lore.remove(lore.size() - 3);
            lore.remove(lore.size() - 2);
            lore.remove(lore.size() - 1);
        }else{
            lore.remove(lore.size() - 5);
            lore.remove(lore.size() - 4);
            lore.remove(lore.size() - 3);
            lore.remove(lore.size() - 2);
            lore.remove(lore.size() - 1);
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createRedEmptySpace(){
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createCollectionProgressItem(int index, String collectionName, String collectionLevel, int collected, String maxCollected, int maxCollectedInt, List<String> collectionRewards, boolean showRewards, int amount){
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, amount);
        ItemMeta meta = item.getItemMeta();
        int percentageUnlocked = Util.percentage(collected, maxCollectedInt);
        List<String> lore = new ArrayList<>();
        switch (index){
            case 1:
                item.setDurability((short) 14);
                meta.setDisplayName(ChatColor.RED + "" + StringUtils.capitalize(collectionName) + " " + collectionLevel);
                lore.add("");
                lore.add(ChatColor.GRAY + "Progress: " + ChatColor.YELLOW + percentageUnlocked + ChatColor.GOLD + "%");
                lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + collected + ChatColor.GOLD + "/" + ChatColor.YELLOW + maxCollected);
                lore.add("");
                lore.add(ChatColor.GRAY + "Rewards: ");
                for(String s : collectionRewards){
                    lore.add(s);
                }
                lore.add("");
                if(showRewards){
                    lore.add(ChatColor.YELLOW + "Click to view rewards!");
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
                break;
            case 2:
                item.setDurability((short) 4);
                meta.setDisplayName(ChatColor.YELLOW + "" + StringUtils.capitalize(collectionName) + " " + collectionLevel);
                lore.add("");
                lore.add(ChatColor.GRAY + "Progress: " + ChatColor.YELLOW + percentageUnlocked + ChatColor.GOLD + "%");
                lore.add(ChatColor.WHITE + "-------------------- " + ChatColor.YELLOW + collected + ChatColor.GOLD + "/" + ChatColor.YELLOW + maxCollected);
                lore.add("");
                lore.add(ChatColor.GRAY + "Rewards: ");
                for(String s : collectionRewards){
                    lore.add(s);
                }
                lore.add("");
                if(showRewards){
                    lore.add(ChatColor.YELLOW + "Click to view rewards!");
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
                break;
            case 3:
                item.setDurability((short) 5);
                meta.setDisplayName(ChatColor.GREEN + "" + StringUtils.capitalize(collectionName) + " " + collectionLevel);
                lore.add("");
                lore.add(ChatColor.GRAY + "Progress: " + ChatColor.GREEN + "100%");
                lore.add(ChatColor.DARK_GREEN + "-------------------- " + ChatColor.YELLOW + collected + ChatColor.GOLD + "/" + ChatColor.YELLOW + maxCollected);
                lore.add("");
                lore.add(ChatColor.GRAY + "Rewards: ");
                for(String s : collectionRewards){
                    lore.add(s);
                }
                lore.add("");
                if(showRewards){
                    lore.add(ChatColor.YELLOW + "Click to view rewards!");
                }
                meta.setLore(lore);
                item.setItemMeta(meta);
                break;
        }
        return item;
    }

    public static void fill(Inventory inv, int index){
        if(index == 1){
            for(int i = 0; i < inv.getSize(); i++){
                inv.setItem(i, createEmptySpace());
            }
        } else if(index == 2){
            for(int i = 0; i < inv.getSize(); i++){
                inv.setItem(i, createRedEmptySpace());
            }
        } else{
            Main.getMain().getServer().getConsoleSender().sendMessage(ChatColor.RED + "Hm, when you tried the ItemHandler.fill method, you entered " + index + " as the index for color. They have to be between 1 and 2!");
        }
    }
    public static void fill(Inventory inv, int index, int s, int e){
        if(index == 1){
            for(int i = s; i < e; i++){
                inv.setItem(i, createEmptySpace());
            }
        } else if(index == 2){
            for(int i = s; i < e; i++){
                inv.setItem(i, createRedEmptySpace());
            }
        } else{
            Main.getMain().getServer().getConsoleSender().sendMessage(ChatColor.RED + "Hm, when you tried the ItemHandler.fill method, you entered " + index + " as the index for color. They have to be between 1 and 2!");
        }
    }

    public static ItemStack createAir(){
        return new ItemStack(Material.AIR);
    }

    public static List<String> addAbililtyLore(ItemStack item, String... lore){
        ItemMeta meta = item.getItemMeta();
        List<String> itemLore = new ArrayList<>();
        if (item.getItemMeta().getLore() != null){
            itemLore = item.getItemMeta().getLore();
        }
        for (String s : lore){
            itemLore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return itemLore;
    }

    public static List<String> addLore(ItemStack item, String... lore){
        ItemMeta meta = item.getItemMeta();
        List<String> itemLore = new ArrayList<>();
        if (item.getItemMeta().getLore() != null){
            itemLore = item.getItemMeta().getLore();
        }
        for (String s : lore){
            itemLore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        itemLore.add(" ");
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return itemLore;
    }

    public static void setDisplayName(ItemStack item, String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
    }

    public static ItemStack toSkyblockItem(ItemStack item){
        if (item.getItemMeta().getLore() == null){
            List<String> lore = new ArrayList<>();
            ItemMeta meta = item.getItemMeta();
            lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }
}