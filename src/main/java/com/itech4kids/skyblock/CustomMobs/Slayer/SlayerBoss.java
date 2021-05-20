package com.itech4kids.skyblock.CustomMobs.Slayer;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.Events.SlayerEvent.SkyblockSlayerKillEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Util.ItemUtil;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public abstract class SlayerBoss extends SEntity {

    private Integer slayerExp;
    private int bossLevel;

    private HashMap<String, ItemStack> equipment;

    private EntityType entityType;

    private int lifespan;

    public SlayerBoss(EntityType slayerEntityType, int bossLevel) {
        super(slayerEntityType);
        equipment = new HashMap<>();

        this.bossLevel = bossLevel;

        slayerExp = 0;

        entityType = slayerEntityType;
        lifespan = 180*20;

        switch (slayerEntityType){
            case ZOMBIE:
                ItemStack zombieHelmet = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
                zombieHelmet = ItemHandler.IDtoSkull(zombieHelmet, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhiZWUyM2I1YzcyNmFlOGUzZDAyMWU4YjRmNzUyNTYxOWFiMTAyYTRlMDRiZTk4M2I2MTQxNDM0OWFhYWM2NyJ9fX0=");

                ItemStack zombieChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                zombieChestplate.addEnchantment(Enchantment.DURABILITY, 1);

                ItemStack zombieLeggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
                zombieLeggings.addEnchantment(Enchantment.DURABILITY, 1);

                equipment.put("iteminhand", new ItemStack(Material.DIAMOND_HOE));
                equipment.put("helmet", zombieHelmet);
                equipment.put("chestplate", zombieChestplate);
                equipment.put("leggings", zombieLeggings);
                equipment.put("boots", new ItemStack(Material.DIAMOND_BOOTS));

                switch (bossLevel){
                    case 1:
                        loadStats(500, 15, true, equipment, "Revenant Horror", 10);
                        slayerExp = 5;
                        break;
                    case 2:
                        loadStats(20000, 25, true, equipment, "Revenant Horror", 70);
                        slayerExp = 25;
                        break;
                    case 3:
                        loadStats(400000, 90, true, equipment, "Revenant Horror", 310);
                        slayerExp = 100;
                        break;
                    case 4:
                        loadStats(1500000, 300, true, equipment, "Revenant Horror", 610);
                        slayerExp = 500;
                        break;
                    case 5:
                        loadStats(10000000, 1800, true, equipment, "Revenant Horror", 1210);
                        slayerExp = 1000;
                        break;
                }
                break;
            case WOLF:
                switch (bossLevel){
                    case 1:
                        loadStats(2000, 60, false, new HashMap<>(), "Sven Packmaster", 20);
                        slayerExp = 5;
                        break;
                    case 2:
                        loadStats(40000, 75, false, new HashMap<>(), "Sven Packmaster", 100);
                        slayerExp = 25;
                        break;
                    case 3:
                        loadStats(750000, 135, false, new HashMap<>(), "Sven Packmaster", 430);
                        slayerExp = 100;
                        break;
                    case 4:
                        loadStats(2000000, 330, false, new HashMap<>(), "Sven Packmaster", 700);
                        slayerExp = 500;
                        break;
                }
                break;
            case SPIDER:
                switch (bossLevel) {
                    case 1:
                        loadStats(750, 35, false, new HashMap<>(), "Tarantula Broodfather", 40);
                        slayerExp = 5;
                        break;
                    case 2:
                        loadStats(30000, 45, false, new HashMap<>(), "Tarantula Broodfather", 90);
                        slayerExp = 25;
                        break;
                    case 3:
                        loadStats(900000, 155, false, new HashMap<>(), "Tarantula Broodfather", 180);
                        slayerExp = 100;
                        break;
                    case 4:
                        loadStats(2400000, 400, false, new HashMap<>(), "Tarantula Broodfather", 260);
                        slayerExp = 500;
                        break;
                }
                break;
        }
    }

    public Entity spawnSlayerBoss(Location location, double displayHeight){
        Entity entity = location.getWorld().spawn(location, entityType.getEntityClass());
        ArmorStand display = location.getWorld().spawn(location, ArmorStand.class);
        vanillaEntity = entity;

        if (entity instanceof LivingEntity){
            LivingEntity lentity = (LivingEntity) entity;

            if (equipment.size() > 0){
                lentity.getEquipment().setItemInHand(equipment.get("iteminhand"));
                lentity.getEquipment().setHelmet(equipment.get("helmet"));
                lentity.getEquipment().setChestplate(equipment.get("chestplate"));
                lentity.getEquipment().setLeggings(equipment.get("leggings"));
                lentity.getEquipment().setBoots(equipment.get("boots"));
            }

            if (lentity.getType().equals(EntityType.ENDERMAN)){
                if (equipment.get("iteminhand") != null) {
                    ItemUtil.setCarriedItem((Enderman) entity, equipment.get("iteminhand"));
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (entity.isDead()){
                        Bukkit.getPluginManager().callEvent(new SkyblockSlayerKillEvent(getLastDamager(), Main.getMain().slayerManger.getEntity(getVanillaEntity())));
                        display.remove();
                        Main.getMain().handler.unRegisterEntity(getVanillaEntity());
                        Main.getMain().slayerManger.unRegisterBoss(getVanillaEntity());
                        lentity.setHealth(0);
                        cancel();
                    }else{
                        entity.setCustomNameVisible(true);
                        display.setVisible(false);
                        display.setGravity(false);
                        display.setCustomNameVisible(true);
                        display.setCustomName(ChatColor.RED + getDespawnDelayDisplay());
                        entity.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lv" + getLevel() + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "☠ " + ChatColor.WHITE + getName() + " " + ChatColor.GREEN + Main.format(getHealth()) + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + Main.format(getMaximumHealth()) + ChatColor.RED + "❤");
                        display.teleport(new Location(entity.getWorld(), entity.getLocation().getX(), entity.getLocation().getY() + displayHeight, entity.getLocation().getZ()));
                        lifespan = lifespan - 1;
                        if (lifespan <= 0){
                            display.remove();
                            Main.getMain().handler.unRegisterEntity(getVanillaEntity());
                            Main.getMain().slayerManger.unRegisterBoss(getVanillaEntity());
                            entity.remove();
                        }
                        if (getHealth() <= 0){
                            Bukkit.getPluginManager().callEvent(new SkyblockSlayerKillEvent(getLastDamager(), Main.getMain().slayerManger.getEntity(getVanillaEntity())));
                            display.remove();
                            Main.getMain().handler.unRegisterEntity(getVanillaEntity());
                            Main.getMain().slayerManger.unRegisterBoss(getVanillaEntity());
                            lentity.setHealth(0);
                        }
                    }
                }
            }.runTaskTimer(Main.getMain(), 5L, 1);
        }

        vanillaEntity = entity;
        return entity;
    }

    public int getBossLevel(){
        return bossLevel;
    }

    public int getExpReward(){
        return slayerExp;
    }

    public String getDespawnDelayDisplay(){
        int ticks = lifespan;
        long minute = ticks / 1200;
        long second = ticks / 20 - minute*60;
        String s = "" + Math. round(minute) + ":" + Math. round(second);
        return s;
    }

}
