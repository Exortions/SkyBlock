package com.itech4kids.skyblock.CustomMobs.Zombie;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.SEntityAI;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;

public class SkyblockZombie extends SEntity {

    private HashMap<String, ItemStack> equipment;

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack weapon;

    public SkyblockZombie(SkyblockZombieType zombieType, Location spawnLocation) {
        super(EntityType.ZOMBIE);

        equipment = new HashMap<>();

        switch (zombieType){
            case GRAVEYARD:
                loadStats(100, 20, true, equipment, "Zombie", 1);
                setSkillExpDropped(7);
                setSkillType(SkillType.COMBAT);
                setCoins(1);
                break;
            case SEA_WALKER:
                helmet = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                helmet = Main.getMain().IDtoSkull(helmet, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJlN2ZhMmY5YjhkNmQxZTczNGVkYTVlM2NlMDI2Njg4MTM0MjkyZmNhZmMzMjViMWVhZDQzZDg5Y2MxZTEifX19");
                chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLUE.getData());
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                chestplateMeta.setColor(Color.BLUE);
                chestplate.setItemMeta(chestplateMeta);
                leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1, DyeColor.BLUE.getData());
                LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
                leggingsMeta.setColor(Color.BLUE);
                leggings.setItemMeta(leggingsMeta);
                boots = new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.BLUE.getData());
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta.setColor(Color.BLUE);
                boots.setItemMeta(bootsMeta);
                weapon = new ItemStack(Material.DIAMOND_SWORD);

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(1500, 60, true, equipment, "Sea Walker", 4);
                setCoins(0);
                break;
            case CRYPT_GHOUL:
                helmet = new ItemStack(Material.AIR);
                chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
                leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                boots = new ItemStack(Material.CHAINMAIL_BOOTS);
                weapon = new ItemStack(Material.IRON_SWORD);
                helmet = null;

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(2000, 350, true, equipment, "Crypt Ghoul", 30);

                SEntityAI.runGhoulAI(this);
                setSkillExpDropped(30);
                setSkillType(SkillType.COMBAT);
                setCoins(13);
                break;
            case GOLDEN_GHOUL:
                chestplate = new ItemStack(Material.GOLD_CHESTPLATE);
                leggings = new ItemStack(Material.GOLD_LEGGINGS);
                boots = new ItemStack(Material.GOLD_BOOTS);
                weapon = new ItemStack(Material.GOLD_SWORD);

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(45000, 800, true, equipment, "Golden Ghoul", 50);

                SEntityAI.runGhoulAI(this);
                setSkillExpDropped(45);
                setSkillType(SkillType.COMBAT);
                setCoins(100);
                break;
            case LAPIS_ZOMBIE:
                helmet = new ItemStack(Material.SEA_LANTERN);
                chestplate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                LeatherArmorMeta chestplateMeta2 = (LeatherArmorMeta) chestplate.getItemMeta();
                chestplateMeta2.setColor(Color.BLUE);
                chestplate.setItemMeta(chestplateMeta2);
                leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                LeatherArmorMeta leggingsMeta2 = (LeatherArmorMeta) leggings.getItemMeta();
                leggingsMeta2.setColor(Color.BLUE);
                leggings.setItemMeta(leggingsMeta2);
                boots = new ItemStack(Material.LEATHER_BOOTS, 1);
                LeatherArmorMeta bootsMeta2 = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta2.setColor(Color.BLUE);
                boots.setItemMeta(bootsMeta2);
                weapon = null;

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(200, 50, true, equipment, "Lapis Zombie", 7);
                setCoins(5);
                break;
            case DIAMOND_RESERVE:
                helmet = new ItemStack(Material.DIAMOND_HELMET);
                chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                boots = new ItemStack(Material.DIAMOND_BOOTS);
                weapon = new ItemStack(Material.DIAMOND_SWORD);

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(250, 200, true, equipment, "Zombie", 15);
                setCoins(8);
                break;
            case ZOMBIE_VILLAGER:
                helmet = new ItemStack(Material.LEATHER_HELMET);
                chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                leggings = new ItemStack(Material.LEATHER_LEGGINGS);
                boots = new ItemStack(Material.LEATHER_BOOTS);
                weapon = null;

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(120, 24, true, equipment, "Zombie Villager", 1);
                Zombie zombie = (Zombie) getVanillaEntity();
                zombie.setVillager(true);
                setCoins(1);
                break;
            case OBSIDIAN_SANCTUARY:
                helmet = new ItemStack(Material.DIAMOND_BLOCK);
                helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                boots = new ItemStack(Material.DIAMOND_BOOTS);
                boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                weapon = new ItemStack(Material.DIAMOND_SWORD);
                weapon.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);

                equipment.put("iteminhand", weapon);
                equipment.put("helmet", helmet);
                equipment.put("chestplate", chestplate);
                equipment.put("leggings", leggings);
                equipment.put("boots", boots);

                loadStats(300, 275, true, equipment, "Zombie", 20);
                setCoins(8);
                break;
        }
        spawn(spawnLocation);
        registerEntity();
    }
}
