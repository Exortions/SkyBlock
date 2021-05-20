package com.itech4kids.skyblock.CustomMobs.Slayer.Miniboss.Revenant;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.SEntityAI;
import com.itech4kids.skyblock.Enums.SkillType;
import com.itech4kids.skyblock.Main;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class DeformedRevenant extends SEntity {

    public DeformedRevenant() {
        super(EntityType.ZOMBIE);

        HashMap<String, ItemStack> equipment = new HashMap<>();

        ItemStack zombieChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        zombieChestplate.addEnchantment(Enchantment.DURABILITY, 1);

        ItemStack zombieLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
        zombieLeggings.addEnchantment(Enchantment.DURABILITY, 1);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) zombieLeggings.getItemMeta();
        leggingsMeta.setColor(Color.RED);
        zombieLeggings.setItemMeta(leggingsMeta);

        ItemStack zombieHelmet = new ItemStack(Material.LEATHER_HELMET);
        zombieHelmet.addEnchantment(Enchantment.DURABILITY, 1);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) zombieHelmet.getItemMeta();
        helmetMeta.setColor(Color.RED);
        zombieHelmet.setItemMeta(helmetMeta);

        equipment.put("iteminhand", new ItemStack(Material.DIAMOND_SWORD));
        equipment.put("helmet", new ItemStack(zombieHelmet));
        equipment.put("chestplate", zombieChestplate);
        equipment.put("leggings", zombieLeggings);
        equipment.put("boots", new ItemStack(Material.DIAMOND_BOOTS));

        loadStats(360000, 4000, true, equipment, "Deformed Revenant", 300);
        setSkillExpDropped(1200);
        setSkillType(SkillType.COMBAT);
    }

    public void summon(Location location){
        spawn(location);
        registerEntity();
        SEntityAI.runGhoulAI(this);
        Zombie zombie = (Zombie) getVanillaEntity();
        zombie.setBaby(false);
        zombie.setVillager(false);

        for (Player player : Bukkit.getOnlinePlayers()){
            player.playSound(location, Sound.EXPLODE, 10, 2);
            player.playEffect(location, Effect.EXPLOSION_LARGE, 10);

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.playSound(location, Sound.EXPLODE, 10, 2);
                    player.playEffect(location, Effect.EXPLOSION_LARGE, 10);
                }
            }.runTaskLater(Main.getMain(), 4);
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.playSound(location, Sound.EXPLODE, 10, 2);
                    player.playEffect(location, Effect.EXPLOSION_LARGE, 10);
                }
            }.runTaskLater(Main.getMain(), 8);
        }
    }

}
