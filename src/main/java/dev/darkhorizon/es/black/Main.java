package dev.darkhorizon.es.black;

import dev.darkhorizon.es.black.Data.sql.DataHandler;
import dev.darkhorizon.es.black.Data.sql.SQLite;
import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.commands.BlackMarket;
import dev.darkhorizon.es.black.commands.VirtualBoss;
import dev.darkhorizon.es.black.config.FileManager;
import dev.darkhorizon.es.black.events.entity.EntityDeath;
import dev.darkhorizon.es.black.events.entity.GeneralEvents;
import dev.darkhorizon.es.black.events.InventoryEvents;
import dev.darkhorizon.es.black.events.PlayerEvents;
import dev.darkhorizon.es.black.events.entity.EntityDamageByEntity;
import dev.darkhorizon.es.black.events.entity.EntitySpawn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static DataHandler dataHandler;
    @Override
    public void onEnable() {
        super.onEnable();
        this.createFolder();
        FileManager.initFiles();
        this.initCommands();
        this.initEvents();
        this.initDatabase();

    }

    public static DataHandler getDataBase() {
        return dataHandler;
    }


    private void initDatabase() {
        dataHandler = new SQLite();
    }

    private void createFolder() {
        File userdata = new File(getDataFolder() + File.separator + "database");
        if (!userdata.exists()) {
            userdata.mkdirs();
        }
    }

    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);
        pm.registerEvents(new GeneralEvents(), this);
        pm.registerEvents(new EntityDamageByEntity(), this);
        pm.registerEvents(new EntitySpawn(), this);
        pm.registerEvents(new EntityDeath(), this);
    }


    private void initCommands() {
        getCommand("blackmarket").setExecutor(new BlackMarket());
        getCommand("virtualboss").setExecutor(new VirtualBoss());
    }

    @Override
    public void onDisable() {
        this.clearEntities();
    }

    private void clearEntities() {
        while (!TempData.getInstance().getEntities().isEmpty()) {
            try {
                for (LivingEntity e : TempData.getInstance().getEntities().values()) {
                    TempData.getInstance().getEntities().remove(e.getUniqueId());
                    e.remove();
                }
            } catch (Exception ignored) {
            }
        }
    }
}
