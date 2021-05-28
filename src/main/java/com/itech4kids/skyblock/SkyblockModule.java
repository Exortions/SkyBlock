package com.itech4kids.skyblock;
// Created in a text editor, imports aren't there, bugs are expected.

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class SkyblockModule {

    private final String name;

    public SkyblockModule(String name) {
        this.name = name;
    }

    public final String getModuleName() {
        return name;
    }

    public final void registerListener(Listener listener) {
        Bukkit.getServer().getPluginManager().registerEvents(listener, Main.getMain());
    }

    public void onEnable() {
      
    }
    public void onDisable() {
      
    }
  
    public void onLoad() {
    
    }
}
