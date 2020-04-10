package dev.darkhorizon.es.black;

import dev.darkhorizon.es.black.commands.BlackMarket;
import dev.darkhorizon.es.black.events.InventoryEvents;
import dev.darkhorizon.es.black.events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.initCommands();
        this.initEvents();
    }

    @Override
    public void onDisable() {
    }

    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);
    }

    private void initCommands() {
        getCommand("blackmarket").setExecutor(new BlackMarket());
    }
}
