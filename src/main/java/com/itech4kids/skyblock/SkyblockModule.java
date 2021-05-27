package com.itech4kids.skyblock;
// Created in a text editor, imports aren't there, bugs are expected.

public abstract class SandboxModule {

    private final String name;

    public SandboxModule(String name) {
        this.name = name;
    }

    public final String getModuleName() {
        return name;
    }

    public final void registerCommand(SkyblockCommand command) {
        Main.getMain().registerCommand(command);
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
