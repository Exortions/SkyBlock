package com.itech4kids.skyblock.Util;

import com.itech4kids.skyblock.Enums.SkillType;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SkillsManager {

    public static int getNextLvl(int i){
        switch (i+1){
            case 1:
              return 50;
            case 2:
                return 125;
            case 3:
                return 200;
            case 4:
                return 300;
            case 5:
                return 500;
            case 6:
                return 750;
            case 7:
                return 1000;
            case 8:
                return 1500;
            case 9:
                return 2000;
            case 10:
                return 3500;
            case 11:
                return 5000;
            case 12:
                return 7500;
            case 13:
                return 10000;
            case 14:
                return 15000;
            case 15:
                return 20000;
            case 16:
                return 30000;
            case 17:
                return 50000;
            case 18:
                return 75000;
            case 19:
                return 100000;
            case 20:
                return 200000;
            case 21:
                return 300000;
            case 22:
                return 400000;
            case 23:
                return 500000;
            case 24:
                return 600000;
            case 25:
                return 700000;
            case 26:
                return 800000;
            case 27:
                return 900000;
            case 28:
                return 1000000;
            case 29:
                return 1100000;
            case 30:
                return 1200000;
            case 31:
                return 1300000;
            case 32:
                return 1400000;
            case 33:
                return 1500000;
            case 34:
                return 1600000;
            case 35:
                return 1700000;
            case 36:
                return 1800000;
            case 37:
                return 1900000;
            case 38:
                return 2000000;
            case 39:
                return 2100000;
            case 40:
                return 2200000;
            case 41:
                return 2300000;
            case 42:
                return 2400000;
            case 43:
                return 2500000;
            case 44:
                return 2600000;
            case 45:
                return 2750000;
            case 46:
                return 2900000;
            case 47:
                return 3100000;
            case 48:
                return 3400000;
            case 49:
                return 3700000;
            case 50:
                return 4000000;
            case 51:
                return 4300000;
            case 52:
                return 4600000;
            case 53:
                return 4900000;
            case 54:
                return 5200000;
            case 55:
                return 5500000;
            case 56:
                return 5800000;
            case 57:
                return 6100000;
            case 58:
                return 6400000;
            case 59:
                return 6700000;
            case 60:
                return 7000000;
        }
        return 0;
    }

    public static int getCoinRewards(int lvl){
        switch (lvl){
            case 1:
                return 25;
            case 2:
                return 50;
            case 3:
                return 100;
            case 4:
                return 200;
            case 5:
                return 300;
            case 6:
                return 400;
            case 7:
                return 500;
            case 8:
                return 600;
            case 9:
                return 700;
            case 10:
                return 800;
            case 11:
                return 900;
            case 12:
                return 1000;
            case 13:
                return 1100;
            case 14:
                return 1200;
            case 15:
                return 1300;
            case 16:
                return 1400;
            case 17:
                return 1500;
            case 18:
                return 1600;
            case 19:
                return 1800;
            case 20:
                return 2000;
            case 21:
                return 2200;
            case 22:
                return 2400;
            case 23:
                return 2600;
            case 24:
                return 2800;
            case 25:
                return 3000;
            case 26:
                return 3500;
            case 27:
                return 4000;
            case 28:
                return 5000;
            case 29:
                return 6000;
            case 30:
                return 7500;
            case 31:
                return 10000;
            case 32:
                return 12500;
            case 33:
                return 15000;
            case 34:
                return 17500;
            case 35:
                return 20000;
            case 36:
                return 25000;
            case 37:
                return 30000;
            case 38:
                return 35000;
            case 39:
                return 40000;
            case 40:
                return 45000;
            case 41:
                return 50000;
            case 42:
                return 60000;
            case 43:
                return 70000;
            case 44:
                return 80000;
            case 45:
                return 90000;
            case 46:
                return 100000;
            case 47:
                return 125000;
            case 48:
                return 150000;
            case 49:
                return 175000;
            case 50:
                return 200000;

        }
        return 0;
    }

    public static List<String> getRewards(SkillType type, int lvl){
        List<String> rewards = new ArrayList<>();
        DecimalFormat format = new DecimalFormat("#,###");
        format.setGroupingUsed(true);
        switch (type){
            case COMBAT:
                rewards.add(ChatColor.YELLOW + "Warrior " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.RED + "+4% " + ChatColor.WHITE + "extra");
                rewards.add(ChatColor.WHITE + "damage to mobs");
                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.BLUE + "0.5☣ Crit Chance");
                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case FORAGING:
                rewards.add(ChatColor.YELLOW + "Logger " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + "4☘ " + ChatColor.WHITE + "Foraging");
                rewards.add(ChatColor.WHITE + "Fortune");
                if (lvl < 14){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "1❁ Strength");
                }else if (lvl < 26){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "2❁ Strength");
                }else {
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "3❁ Strength");
                }

                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case FARMING:
                rewards.add(ChatColor.YELLOW + "Farmhand " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + "4☘ " + "Farming");
                rewards.add(ChatColor.WHITE + "Fortune");
                if (lvl < 14){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "2❤ Health");
                }else if (lvl < 20){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "3❤ Health");
                }else if (lvl < 26){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "4❤ Health");
                }else{
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "5❤ Health");
                }

                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case FISHING:
                rewards.add(ChatColor.YELLOW + "Treasure Hunter " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.AQUA + "+0.2% " + ChatColor.WHITE + "chance");
                rewards.add(ChatColor.WHITE + "of catching treasure when");
                rewards.add(ChatColor.WHITE + "fishing");
                if (lvl < 14){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "2❤ Health");
                }else if (lvl < 20){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "3❤ Health");
                }else if (lvl < 26){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "4❤ Health");
                }else{
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "5❤ Health");
                }

                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case ENCHANTING:
                rewards.add(ChatColor.YELLOW + "Conjourer " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.AQUA + "+4% " + ChatColor.WHITE + "more");
                rewards.add(ChatColor.WHITE + "EXP from any source");
                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.RED + "0.5✹ Ability Damage");


                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case ALCHEMY:
                rewards.add(ChatColor.YELLOW + "Brewer " + lvl);
                rewards.add(ChatColor.WHITE + "Grants a " + ChatColor.AQUA + "+1% " + ChatColor.WHITE + "effect");
                rewards.add(ChatColor.WHITE + "duration on potions you");
                rewards.add(ChatColor.WHITE + "brew");
                if (lvl < 14){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.AQUA + "1✎ Intelligence");
                }else {
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.AQUA + "2✎ Intelligence");
                }

                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case TAMING:
                rewards.add(ChatColor.YELLOW + "Zoologist " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.LIGHT_PURPLE + "+1% " + ChatColor.WHITE + "extra");
                rewards.add(ChatColor.WHITE + "pet exp");
                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.LIGHT_PURPLE + "0.5♣ Pet Luck");
                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
            case MINING:
                rewards.add(ChatColor.YELLOW + "Spelunker " + lvl);
                rewards.add(ChatColor.WHITE + "Grants " + ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + "4☘ " + "Mining");
                rewards.add(ChatColor.WHITE + "Fortune");
                if (lvl < 14){
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + "1❈ Defense");
                }else {
                    rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + "2❈ Defense");
                }

                rewards.add(ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + format.format(getCoinRewards(lvl)) + " Coins");
                break;
        }
        return rewards;
    }

}
