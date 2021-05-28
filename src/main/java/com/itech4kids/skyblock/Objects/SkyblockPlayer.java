package com.itech4kids.skyblock.Objects;

import com.itech4kids.skyblock.Enums.PlayerScoreboardState;
import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkyblockPlayer {

    private Player player;
    private HashMap<SkyblockStats, Integer> stats;
    private HashMap<String, Inventory> inventories;
    public Inventory lastInventory;

    public ArmorStand activePet;

    public boolean convertPetToItem;
    public boolean ferocityCooldown;
    public boolean aoteSpeed;
    public Block brokenBlock;
    public String padName;

    public ItemStack itemInHand;
    public ItemStack lastPickedUp;
    public ItemStack oldItemInHand;

    public HashMap<String, Integer> cooldowns;

    public ArrayList<Player> tradedPlayers;
    public boolean tradeAccepted;

    public ArrayList<SkyblockLocation> locations;
    public SkyblockLocation location;

    public PlayerScoreboardState state;

    public int lastPickedUpCoins;

    public List<ItemStack> pendingSwords;

    public boolean search;

    public SkyblockPlayer(Player player){
        this.player = player;
        tradedPlayers = new ArrayList<>();
        aoteSpeed = false;
        tradeAccepted = false;
        activePet = null;
        convertPetToItem = false;
        lastPickedUp = null;
        lastPickedUpCoins = 0;
        cooldowns = new HashMap<>();

        padName = "";
        brokenBlock = null;
        state = PlayerScoreboardState.DEFAULT;

        search = false;

        locations = new ArrayList<>();
        location = null;

        pendingSwords = new ArrayList<>();

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        itemMeta.setDisplayName(ChatColor.DARK_GRAY + "[Lvl 0] None");
        lore.add("T3ST");
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        itemInHand = item;

        oldItemInHand = item;

        stats = new HashMap<SkyblockStats, Integer>();
        inventories = new HashMap<String, Inventory>();
        loadDefaultStats();
        loadCooldowns();
        lastInventory = player.getInventory();
        ferocityCooldown = false;
    }

    private void loadCooldowns(){
        setCooldown("ember_rod", 0);
        setCooldown("iron_punch", 0);
        setCooldown("grappling_hook", 0);
        setCooldown("midas_staff", 0);
        setCooldown("leaping_sword", 0);
        setCooldown("silk_edge_sword", 0);
    }

    private void loadDefaultStats(){
        setStat(SkyblockStats.HEALTH, Config.getPlayerStatLvl(this.player, SkyblockStats.HEALTH));
        setStat(SkyblockStats.MAX_HEALTH, Config.getPlayerStatLvl(this.player, SkyblockStats.MAX_HEALTH));
        setStat(SkyblockStats.DEFENSE, Config.getPlayerStatLvl(this.player, SkyblockStats.DEFENSE));
        setStat(SkyblockStats.SPEED, Config.getPlayerStatLvl(this.player, SkyblockStats.SPEED));
        setStat(SkyblockStats.STRENGTH, Config.getPlayerStatLvl(this.player, SkyblockStats.STRENGTH));
        setStat(SkyblockStats.MANA, Config.getPlayerStatLvl(this.player, SkyblockStats.MANA));
        setStat(SkyblockStats.MAX_MANA, Config.getPlayerStatLvl(this.player, SkyblockStats.MAX_MANA));
        setStat(SkyblockStats.CRIT_CHANCE, Config.getPlayerStatLvl(this.player, SkyblockStats.CRIT_CHANCE));
        setStat(SkyblockStats.CRIT_DAMAGE, Config.getPlayerStatLvl(this.player, SkyblockStats.CRIT_DAMAGE));
        setStat(SkyblockStats.ABILITY_DAMAGE, Config.getPlayerStatLvl(this.player, SkyblockStats.ABILITY_DAMAGE));
        setStat(SkyblockStats.MAGIC_FIND, Config.getPlayerStatLvl(this.player, SkyblockStats.MAGIC_FIND));
        setStat(SkyblockStats.SEA_CREATURE_CHANCE, Config.getPlayerStatLvl(this.player, SkyblockStats.SEA_CREATURE_CHANCE));
        setStat(SkyblockStats.MINING_FORTUNE, Config.getPlayerStatLvl(this.player, SkyblockStats.MINING_FORTUNE));
        setStat(SkyblockStats.PET_LUCK, Config.getPlayerStatLvl(this.player, SkyblockStats.PET_LUCK));
        setStat(SkyblockStats.TRUE_DEFENSE, Config.getPlayerStatLvl(this.player, SkyblockStats.TRUE_DEFENSE));
        setStat(SkyblockStats.FEROCITY, Config.getPlayerStatLvl(this.player, SkyblockStats.FEROCITY));
        setStat(SkyblockStats.MINING_FORTUNE, Config.getPlayerStatLvl(this.player, SkyblockStats.MINING_FORTUNE));
        setStat(SkyblockStats.FARMING_FORTUNE, Config.getPlayerStatLvl(this.player, SkyblockStats.FARMING_FORTUNE));
        setStat(SkyblockStats.FORAGING_FORTUNE, Config.getPlayerStatLvl(this.player, SkyblockStats.FORAGING_FORTUNE));
        setStat(SkyblockStats.DAMAGE, Config.getPlayerStatLvl(this.player, SkyblockStats.DAMAGE));
        setStat(SkyblockStats.ATTACK_SPEED, Config.getPlayerStatLvl(this.player, SkyblockStats.ATTACK_SPEED));
        setStat(SkyblockStats.MINING_SPEED, Config.getPlayerStatLvl(this.player, SkyblockStats.MINING_SPEED));
    }

    public void saveStats() throws IOException {
        Config.setStat(player, SkyblockStats.HEALTH, getStat(SkyblockStats.MAX_HEALTH));
        Config.setStat(player, SkyblockStats.MAX_HEALTH, getStat(SkyblockStats.MAX_HEALTH));
        Config.setStat(player, SkyblockStats.DEFENSE, getStat(SkyblockStats.DEFENSE));
        Config.setStat(player, SkyblockStats.SPEED, getStat(SkyblockStats.SPEED));
        Config.setStat(player, SkyblockStats.STRENGTH, getStat(SkyblockStats.STRENGTH));
        Config.setStat(player, SkyblockStats.MANA, getStat(SkyblockStats.MAX_MANA));
        Config.setStat(player, SkyblockStats.MAX_MANA, getStat(SkyblockStats.MAX_MANA));
        Config.setStat(player, SkyblockStats.CRIT_CHANCE, getStat(SkyblockStats.CRIT_CHANCE));
        Config.setStat(player, SkyblockStats.CRIT_DAMAGE, getStat(SkyblockStats.CRIT_DAMAGE));
        Config.setStat(player, SkyblockStats.ABILITY_DAMAGE, getStat(SkyblockStats.ABILITY_DAMAGE));
        Config.setStat(player, SkyblockStats.MAGIC_FIND, getStat(SkyblockStats.MAGIC_FIND));
        Config.setStat(player, SkyblockStats.SEA_CREATURE_CHANCE, getStat(SkyblockStats.SEA_CREATURE_CHANCE));
        Config.setStat(player, SkyblockStats.MINING_FORTUNE, getStat(SkyblockStats.MINING_FORTUNE));
        Config.setStat(player, SkyblockStats.PET_LUCK, getStat(SkyblockStats.PET_LUCK));
        Config.setStat(player, SkyblockStats.TRUE_DEFENSE, getStat(SkyblockStats.TRUE_DEFENSE));
        Config.setStat(player, SkyblockStats.FEROCITY, getStat(SkyblockStats.FEROCITY));
        Config.setStat(player, SkyblockStats.MINING_FORTUNE, getStat(SkyblockStats.MINING_FORTUNE));
        Config.setStat(player, SkyblockStats.FARMING_FORTUNE, getStat(SkyblockStats.FARMING_FORTUNE));
        Config.setStat(player, SkyblockStats.FORAGING_FORTUNE, getStat(SkyblockStats.FORAGING_FORTUNE));
        Config.setStat(player, SkyblockStats.DAMAGE, getStat(SkyblockStats.DAMAGE));
        Config.setStat(player, SkyblockStats.ATTACK_SPEED, getStat(SkyblockStats.ATTACK_SPEED));
        Config.setStat(player, SkyblockStats.MINING_SPEED, getStat(SkyblockStats.MINING_SPEED));
    }

    public void addItem(ItemStack itemStack){
        List<Integer> freeSlots = new ArrayList<>();
        for (int i = 0; i < player.getInventory().getSize(); i++){
            if (player.getInventory().getItem(i) == null){
                freeSlots.add(i);
                player.getInventory().addItem(itemStack);
                break;
            }
        }
        if (freeSlots.size() == 0){
            player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
        }
    }

    public void setStat(SkyblockStats s, Integer i){
        stats.put(s, i);
    }

    public HashMap<SkyblockStats, Integer> getStats(){
        return stats;
    }

    public Integer getStat(SkyblockStats statName){return stats.get(statName);}

    public void setCooldown(String s, int i){
        cooldowns.put(s, i);
    }

    public Integer getCooldown(String s){
        return cooldowns.get(s);
    }

    public void setInventory(String s, Inventory i){
        if (inventories.containsKey(s)){
            inventories.remove(s);
        }
        inventories.put(s, i);
    }

    public HashMap<String, Inventory> getInventories(){
        return inventories;
    }

    public Inventory getInventory(String inventoryName){ return inventories.get(inventoryName); }

    public Player getBukkitPlayer(){
        return player;
    }
}
