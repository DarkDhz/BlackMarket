package dev.darkhorizon.es.black;

import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.bosses.CustomBoss;
import dev.darkhorizon.es.black.commands.BlackMarket;
import dev.darkhorizon.es.black.commands.VirtualBoss;
import dev.darkhorizon.es.black.events.EntityEvents;
import dev.darkhorizon.es.black.events.InventoryEvents;
import dev.darkhorizon.es.black.events.PlayerEvents;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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
        this.clearEntities();
    }

    private void clearEntities() {
        for (Entity entity : TempData.entities) {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).damage(100000);
            }
        }
    }


    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);
        pm.registerEvents(new EntityEvents(), this);
    }


    private void initCommands() {
        getCommand("blackmarket").setExecutor(new BlackMarket());
        getCommand("virtualboss").setExecutor(new VirtualBoss());
    }
}
