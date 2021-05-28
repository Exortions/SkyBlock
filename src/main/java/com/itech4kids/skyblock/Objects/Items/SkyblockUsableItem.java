package com.itech4kids.skyblock.Objects.Items;

import com.itech4kids.skyblock.Enums.SkyblockStats;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SkyblockUsableItem {

    private HashMap<SkyblockStats, Integer> properties;

    public SkyblockUsableItem(ItemStack itemStack){
        this.properties = new HashMap<>();

        generateStats(itemStack.getItemMeta().getLore());

    }

    public void setProperty(SkyblockStats string, Integer i){
        properties.put(string, i);
    }

    public HashMap<SkyblockStats, Integer> getProperties(){
        return properties;
    }

    private void generateStats(List<String> lore){
        for (String string : lore){

            List<String> list = Arrays.asList(ChatColor.stripColor(string.toLowerCase()).replaceAll("%", "").replaceAll("hp", "").replaceAll(" ", "").split(":+"));

            if (ChatColor.stripColor(string.toLowerCase()).startsWith("strength")){
                setProperty(SkyblockStats.STRENGTH, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("health")){
                setProperty(SkyblockStats.HEALTH, Integer.parseInt(list.get(1)));
                setProperty(SkyblockStats.MAX_HEALTH, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("damage")){
                setProperty(SkyblockStats.DAMAGE, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("crit chance")){
                setProperty(SkyblockStats.CRIT_CHANCE, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("crit damage")){
                setProperty(SkyblockStats.CRIT_DAMAGE, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("defense")){
                setProperty(SkyblockStats.DEFENSE, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("true defense")){
                setProperty(SkyblockStats.TRUE_DEFENSE, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("speed")){
                setProperty(SkyblockStats.SPEED, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("intelligence")){
                setProperty(SkyblockStats.MANA, Integer.parseInt(list.get(1)));
                setProperty(SkyblockStats.MAX_MANA, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("magic find")){
                setProperty(SkyblockStats.STRENGTH, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("sea creature chance")){
                setProperty(SkyblockStats.MAGIC_FIND, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("pet luck")){
                setProperty(SkyblockStats.PET_LUCK, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("ferocity")){
                setProperty(SkyblockStats.FEROCITY, Integer.parseInt(list.get(1)));

            }else if (ChatColor.stripColor(string.toLowerCase()).startsWith("ability damage")){
                setProperty(SkyblockStats.ABILITY_DAMAGE, Integer.parseInt(list.get(1)));
            }
        }
    }

}
