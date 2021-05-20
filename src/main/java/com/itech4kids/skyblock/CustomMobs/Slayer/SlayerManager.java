package com.itech4kids.skyblock.CustomMobs.Slayer;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.RevenantBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.SvenBoss;
import com.itech4kids.skyblock.CustomMobs.Slayer.Bosses.TarantulaBoss;
import com.itech4kids.skyblock.Enums.PlayerScoreboardState;
import com.itech4kids.skyblock.Main;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class SlayerManager {

    private HashMap<Integer, SlayerBoss> slayers;
    private int nextId;
    private HashMap<Player, SlayerQuest> quests;

    public SlayerManager(){
        slayers = new HashMap<>();
        nextId = -1;

        quests = new HashMap<>();
    }

    public void registerBoss(SlayerBoss boss){
        nextId++;
        slayers.put(nextId, boss);
        boss.getVanillaEntity().setMetadata("slayerId", new FixedMetadataValue(Main.getMain(), nextId));
    }

    public void unRegisterBoss(Entity entity){
        if (entity != null) {
            HashMap<Integer, SlayerBoss> bosses_2 = new HashMap<>(slayers);
            for (Map.Entry<Integer, SlayerBoss> entry : slayers.entrySet()) {
                if (entry.getValue().getVanillaEntity().getMetadata("slayerId").equals(entity.getMetadata("slayerId"))) {
                    bosses_2.remove(entry.getKey(), entry.getValue());
                    entity.removeMetadata("slayerId", Main.getMain());
                }
            }

            slayers = bosses_2;
        }
    }

    public SlayerBoss getEntity(Entity entity){
        for (Map.Entry<Integer, SlayerBoss> entry : slayers.entrySet()){
            if (entry.getValue().getVanillaEntity().getMetadata("slayerId").equals(entity.getMetadata("slayerId"))){
                return entry.getValue();
            }
        }
        return null;
    }

    public SlayerQuest getQuest(SlayerBoss boss){
        for (Map.Entry<Player, SlayerQuest> entry : quests.entrySet()){
            if (entry.getValue().getBoss().equals(boss)){
                return entry.getValue();
            }
        }
        return null;
    }

    public SlayerQuest getQuest(Player player){
        return quests.get(player);
    }

    public void completeQuest(Player player){
        quests.remove(player);
    }

    public void createQuest(Player player, SlayerType type, int lvl){
        player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 10, 2);
        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);
        switch (type){
            case SVEN:
                quests.put(player, new SlayerQuest(type, player, new SvenBoss(player, lvl)));
                player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SLAYER QUEST STARTED");
                player.sendMessage(ChatColor.DARK_PURPLE + " ☠ " + ChatColor.GRAY + "Slay " + ChatColor.RED + formatter.format(getQuest(player).getNeededExp()) + " Combat XP " + ChatColor.GRAY + "worth of Wolves.");
                break;
            case REVENANT:
                quests.put(player, new SlayerQuest(type, player, new RevenantBoss(player, lvl)));
                player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SLAYER QUEST STARTED");
                player.sendMessage(ChatColor.DARK_PURPLE + " ☠ " + ChatColor.GRAY + "Slay " + ChatColor.RED + formatter.format(getQuest(player).getNeededExp()) + " Combat XP " + ChatColor.GRAY + "worth of Zombies.");
                break;
            case TARANTULA:
                quests.put(player, new SlayerQuest(type, player, new TarantulaBoss(player, lvl)));
                player.sendMessage(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "SLAYER QUEST STARTED");
                player.sendMessage(ChatColor.DARK_PURPLE + " ☠ " + ChatColor.GRAY + "Slay " + ChatColor.RED + formatter.format(getQuest(player).getNeededExp()) + " Combat XP " + ChatColor.GRAY + "worth of Spiders.");
                break;
        }
    }

    public int getNextExpAmount(int i, SlayerType type){
        switch (type){
            case SVEN:
                switch (i + 1){
                    case 2:
                        return 30;
                    case 3:
                        return 250;

                    case 4:
                        return 1500;

                    case 5:
                        return 5000;

                    case 6:
                        return 20000;

                    case 7:
                        return 100000;

                    case 8:
                        return 400000;

                    case 9:
                        return 1000000;

                }

            case TARANTULA:
                switch (i + 1){
                    case 2:
                        return 25;

                    case 3:
                        return 200;

                    case 4:
                        return 1000;

                    case 5:
                        return 5000;

                    case 6:
                        return 20000;

                    case 7:
                        return 100000;

                    case 8:
                        return 400000;

                    case 9:
                        return 1000000;

                }

            case REVENANT:
                switch (i + 1){
                    case 2:
                        return 15;

                    case 3:
                        return 200;

                    case 4:
                        return 1000;

                    case 5:
                        return 5000;

                    case 6:
                        return 20000;

                    case 7:
                        return 100000;

                    case 8:
                        return 400000;

                    case 9:
                        return 1000000;
                }
        }
        return Integer.MAX_VALUE;
    }
}
