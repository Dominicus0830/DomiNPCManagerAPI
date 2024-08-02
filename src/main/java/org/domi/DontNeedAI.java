package org.domi;

import org.bukkit.plugin.java.JavaPlugin;

public final class DontNeedAI extends JavaPlugin {
    private static DontNeedAI plugin;

    public static DontNeedAI getInstance() {
        return plugin;
    }

    /*@Override
    public void onEnable() {
        plugin = this;
        getLogger().info("DontNeedAI enabled! It sure?!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }*/
}
