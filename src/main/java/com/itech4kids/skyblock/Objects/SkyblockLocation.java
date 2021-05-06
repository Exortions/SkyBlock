package com.itech4kids.skyblock.Objects;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class SkyblockLocation {

    public Location pos1;
    public Location pos2;
    public String name;
    public int weight;
    public ChatColor color;

    public SkyblockLocation(Location loc1, Location loc2, String name, ChatColor nameColor, int weight){
        pos1 = loc1;
        pos2 = loc2;
        this.name = name;
        color = nameColor;
        this.weight = weight;
    }
}
