package org.domi;

import net.citizensnpcs.api.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.domi.commands.DomiNPCTypeCommand;
import org.domi.events.EventListener;
import org.domi.function.commands.PDCManager;

public class DomiNPCManagerAPI extends JavaPlugin {
    private static DomiNPCManagerAPI plugin;
    private final CommandManager commands = new CommandManager();
    private PDCManager pdcManager;

    public static DomiNPCManagerAPI getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Bukkit.getPluginCommand("dominpc").setExecutor(new DomiNPCTypeCommand());
        Bukkit.getPluginCommand("dominpc").setTabCompleter(new DomiNPCTypeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        
    }
}
