package com.itech4kids.skyblock;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.itech4kids.skyblock.Commands.ItemBrowser.Boots.BootsCategoryCommand;
import com.itech4kids.skyblock.Commands.ItemBrowser.Chestplate.ChestplateCategoryCommand;
import com.itech4kids.skyblock.Commands.ItemBrowser.Helmet.HelmetCategoryCommand;
import com.itech4kids.skyblock.Commands.ItemBrowser.Leggings.LeggingsCategoryCommand;
import com.itech4kids.skyblock.Commands.ItemBrowser.Sword.SwordCategoryCommand;
import com.itech4kids.skyblock.Commands.ItemBrowser.Sword.SwordCategoryCommandPage2;
import com.itech4kids.skyblock.Commands.*;
import com.itech4kids.skyblock.Commands.Merchants.MinerMerchantCommand;
import com.itech4kids.skyblock.CustomMobs.Dragon.SkyblockDragon;
import com.itech4kids.skyblock.CustomMobs.Enderman.SkyblockEnderman;
import com.itech4kids.skyblock.CustomMobs.Zombie.SkyblockZombie;
import com.itech4kids.skyblock.Listeners.*;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Objects.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import net.citizensnpcs.trait.SkinTrait;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Main extends JavaPlugin implements Listener {

    public HashMap<String, SkyblockPlayer> players;
    private static Main main;
    public static ArrayList<ArmorStand> damage_indicator;

    public SkinTrait minerSkin;

    @Override
    public void onEnable(){
        main = this;
        players = new HashMap<String, SkyblockPlayer>();
        damage_indicator = new ArrayList<ArmorStand>();
        registerListeners();
        registerCommands();
        registerEntity("Enderman", 58, SkyblockEnderman.class);
        registerEntity("Dragon", 63, SkyblockDragon.class);
        registerEntity("Zombie", 54, SkyblockZombie.class);
        new Config(this);
        ItemHandler.init();
    }


    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new Eventlistener(), this);
        Bukkit.getPluginManager().registerEvents(new SkyblockMenuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SkillGainListeners(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new AbilityListener(), this);
        Bukkit.getPluginManager().registerEvents(new MerchantListener(), this);
        Bukkit.getPluginManager().registerEvents(new CollectionsListener(), this);
    }

    public void registerCommands(){
        getCommand("sbmenu").setExecutor(new SkyblockMenuCommand());
        getCommand("setPlayerStat").setExecutor(new PlayerStatCommand());
        getCommand("spawncm").setExecutor(new SpawnCustomMobCommand());
        getCommand("todolist").setExecutor(new TodoListCommand());
        getCommand("spawnskyblocknpc").setExecutor(new CreateSkyblockNPCCommand());
        getCommand("minermerchant").setExecutor(new MinerMerchantCommand());
        getCommand("itembrowser").setExecutor(new ItemBrowserCommand());
        getCommand("swordcategory").setExecutor(new SwordCategoryCommand());
        getCommand("swordcategory2").setExecutor(new SwordCategoryCommandPage2());
        getCommand("helmetcategory").setExecutor(new HelmetCategoryCommand());
        getCommand("chestplatecategory").setExecutor(new ChestplateCategoryCommand());
        getCommand("leggingscategory").setExecutor(new LeggingsCategoryCommand());
        getCommand("bootscategory").setExecutor(new BootsCategoryCommand());
        getCommand("item").setExecutor(new ItemCommand());
        getCommand("collections").setExecutor(new CollectionsCommand());
    }

    public void registerCustomMobs(){

    }

    public static void registerEntity(String name, int id, Class<? extends EntityInsentient> customClass) {
        try {
            List<Map<?, ?>> dataMaps = new ArrayList<>();
            for (Field f : EntityTypes.class.getDeclaredFields()) {
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())) {
                    f.setAccessible(true);
                    dataMaps.add((Map<?, ?>) f.get(null));
                }
            }
            ((Map<Class<? extends EntityInsentient>, String>) dataMaps.get(1)).put(customClass, name);
            ((Map<Class<? extends EntityInsentient>, Integer>) dataMaps.get(3)).put(customClass, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Main getMain(){return main;}

    public SkyblockPlayer getPlayer(String s){return players.get(s);}

    private int displayScoreBoard(int scoreNum, Objective objective, Player player) {
        int hours=java.util.Calendar.getInstance().getTime().getHours();
        int minutes=java.util.Calendar.getInstance().getTime().getMinutes();
        SkyblockPlayer activePlayer = getPlayer(player.getName());
        LocalDate l_date = LocalDate.now();
        String dateString = l_date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));

        Score score = objective.getScore(org.bukkit.ChatColor.GRAY + "" + dateString + " " + "Skyblock");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.GRAY + "   ");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + " Spring 10th");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.GRAY + " " + hours + ":" + minutes + "pm " + ChatColor.YELLOW + "☀");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + " ⏣ " + ChatColor.GREEN + "Hub Island");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + " ");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + "Purse: " + ChatColor.GOLD + Config.getPurseCoins(activePlayer.getBukkitPlayer()));
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + "Bits: " + ChatColor.AQUA + Config.getBits(player));
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.WHITE + "  ");
        score.setScore(scoreNum--);
        score = objective.getScore(org.bukkit.ChatColor.YELLOW + "www.hypixel.net");
        score.setScore(scoreNum--);
        return scoreNum;
    }

    public void updateScoreBoard() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective objective = board.registerNewObjective("Skyblock", "Display ");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
            objective.setDisplayName(org.bukkit.ChatColor.YELLOW + "" + ChatColor.BOLD + "SKYBLOCK");
            int scoreNum = 10;

            scoreNum = displayScoreBoard(scoreNum, objective, p);

            p.setScoreboard(board);
        }
    }

    public void onJoin(final Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel(); // this cancels it when they leave
                }else if (player.isOnline()){
                    SkyblockPlayer skyblockPlayer = getPlayer(player.getName());
                    IChatBaseComponent icbc;

                    if (skyblockPlayer.getStat(SkyblockStats.DEFENSE) <= 0) {
                        icbc = IChatBaseComponent.ChatSerializer.a(
                                "{\"text\":\"" + "§c" + skyblockPlayer.getStat(SkyblockStats.HEALTH) + "/" + skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) + "❤" +
                                        "    §b" + skyblockPlayer.getStat(SkyblockStats.MANA) + "/" +
                                        skyblockPlayer.getStat(SkyblockStats.MAX_MANA) + "✎ Mana" + "\"}");
                    }else{
                        icbc = IChatBaseComponent.ChatSerializer.a(
                                "{\"text\":\"" + "§c" + skyblockPlayer.getStat(SkyblockStats.HEALTH) + "/" + skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) + "❤   §a" +
                                        skyblockPlayer.getStat(SkyblockStats.DEFENSE) + "❈ Defense    §b" + skyblockPlayer.getStat(SkyblockStats.MANA) + "/" +
                                        skyblockPlayer.getStat(SkyblockStats.MAX_MANA) + "✎ Mana" + "\"}");
                    }
                    ActionBarAPI.sendActionBar(player, icbc.getText());
                    updateMaxHealth(skyblockPlayer);

                }
            }
        }.runTaskTimer(this, 5L, 40);
    }

    public void updateBoard(final Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    cancel();
                }else if (player.isOnline()){
                    updateScoreBoard();
                    SkyblockPlayer skyblockPlayer = getPlayer(player.getName());
                    if (skyblockPlayer.getStat(SkyblockStats.MANA) <= skyblockPlayer.getStat(SkyblockStats.MAX_MANA) - 6) {
                        skyblockPlayer.setStat(SkyblockStats.MANA, skyblockPlayer.getStat(SkyblockStats.MANA) + 6);
                    }else{
                        skyblockPlayer.setStat(SkyblockStats.MANA, skyblockPlayer.getStat(SkyblockStats.MAX_MANA));
                    }

                    if (skyblockPlayer.getStat(SkyblockStats.HEALTH) <= skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH) - 3) {
                        updateHealth(skyblockPlayer, 3);
                    }else{
                        updateHealth(skyblockPlayer, 0);
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH));
                    }
                }
            }
        }.runTaskTimer(this, 5L, 50);
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<> ();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }

    public void updateMaxHealth(SkyblockPlayer skyblockPlayer) {
        Player player = skyblockPlayer.getBukkitPlayer();

        int hp = skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH);

        if (hp > 0 && hp <= 100){
            player.setMaxHealth(20);
        }else if (hp > 101 && hp <= 200){
            player.setMaxHealth(22);
        }else if (hp > 201 && hp <= 300){
            player.setMaxHealth(24);
        }else if (hp > 301 && hp <= 400){
            player.setMaxHealth(26);
        }else if (hp > 401 && hp <= 500){
            player.setMaxHealth(28);
        }else if (hp > 501 && hp <= 600){
            player.setMaxHealth(30);
        }else if (hp > 601 && hp <= 700){
            player.setMaxHealth(32);
        }else if (hp > 701 && hp <= 800){
            player.setMaxHealth(34);
        }else if (hp > 801 && hp <= 900){
            player.setMaxHealth(36);
        }else if (hp > 901 && hp <= 1000){
            player.setMaxHealth(38);
        }else if (hp > 1001){
            player.setMaxHealth(40);
        }

        if (skyblockPlayer.getStat(SkyblockStats.HEALTH) <= 0){
            player.setHealth(0);
        }
    }

    public int sbHealthtoVanilla(int hp) {

        int i = 0;

        if (hp > 0 && hp <= 100){
            i = 20;
        }else if (hp > 101 && hp <= 200){
            i = 22;
        }else if (hp > 201 && hp <= 300){
            i = 24;
        }else if (hp > 301 && hp <= 400){
            i = 26;
        }else if (hp > 401 && hp <= 500){
            i = 28;
        }else if (hp > 501 && hp <= 600){
            i = 30;
        }else if (hp > 601 && hp <= 700){
            i = 32;
        }else if (hp > 701 && hp <= 800){
            i = 34;
        }else if (hp > 801 && hp <= 900){
            i = 36;
        }else if (hp > 901 && hp <= 1000){
            i = 38;
        }else if (hp > 1001){
            i = 40;
        }
        return i;
    }

    public void updateHealth(SkyblockPlayer skyblockPlayer, int i){
        Player player = skyblockPlayer.getBukkitPlayer();

        int hp = skyblockPlayer.getStat(SkyblockStats.HEALTH);
        int mhp = skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH);

        if (player.getHealth() <= player.getMaxHealth()){
            player.setHealth(player.getHealth() + i/(mhp/player.getMaxHealth()));
        }
        skyblockPlayer.setStat(SkyblockStats.HEALTH, hp + i);
    }
}
