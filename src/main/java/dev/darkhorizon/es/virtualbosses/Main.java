package dev.darkhorizon.es.virtualbosses;

import dev.darkhorizon.es.virtualbosses.config.Lang;
import dev.darkhorizon.es.virtualbosses.data.sql.DataHandler;
import dev.darkhorizon.es.virtualbosses.data.sql.SQLite;
import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.commands.BlackMarket;
import dev.darkhorizon.es.virtualbosses.commands.VirtualBoss;
import dev.darkhorizon.es.virtualbosses.config.FileManager;
import dev.darkhorizon.es.virtualbosses.listeners.entity.EntityDeath;
import dev.darkhorizon.es.virtualbosses.listeners.entity.GeneralEvents;
import dev.darkhorizon.es.virtualbosses.listeners.InventoryEvents;
import dev.darkhorizon.es.virtualbosses.listeners.PlayerEvents;
import dev.darkhorizon.es.virtualbosses.listeners.entity.EntityDamageByEntity;
import dev.darkhorizon.es.virtualbosses.listeners.entity.EntitySpawn;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class Main extends JavaPlugin {

    private static DataHandler dataHandler;

    @Override
    public void onEnable() {
        super.onEnable();
        if (!this.createFolder()) {
            this.getPluginLoader().disablePlugin(this);
            System.err.println("Can't create virtualBosses database file!");
            return;
        }
        FileManager.initFiles();
        this.initCommands();
        this.initEvents();
        this.initDatabase();
        this.initThread();

    }

    public static DataHandler getDataBase() {
        return dataHandler;
    }

    private void initDatabase() {
        dataHandler = new SQLite();
    }

    private boolean createFolder() {
        File userdata = new File(getDataFolder() + File.separator + "database");
        if (!userdata.exists()) {
            return userdata.mkdirs();
        }
        return true;
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

    private void initThread() {
        final Plugin plugin = this;
        new BukkitRunnable() {
            boolean first = true;

            public void run() {
                if (!first) {
                    clearEntities();
                    BossUtils.randomSpawn();
                } else {
                    first = false;
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage("§1");
                        Bukkit.broadcastMessage(Lang.getInstance().info_spawn_time.replaceAll("%time", "10 minutos"));
                        Bukkit.broadcastMessage("§2");
                    }
                }.runTaskLater(plugin, 110*60*20);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage("§1");
                        Bukkit.broadcastMessage(Lang.getInstance().info_spawn_time.replaceAll("%time", "5 minutos"));
                        Bukkit.broadcastMessage("§2");
                    }
                }.runTaskLater(plugin, 115*60*20);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage("§1");
                        Bukkit.broadcastMessage(Lang.getInstance().info_spawn_time.replaceAll("%time", "2 minutos"));
                        Bukkit.broadcastMessage("§2");
                    }
                }.runTaskLater(plugin, 118*60*20);

            }
        }.runTaskTimer(this, 0, 2*60*60*20);
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
                    if (TempData.getInstance().getBoss_damagers().get(e.getUniqueId()) != null) {
                        TempData.getInstance().getBoss_damagers().remove(e.getUniqueId());
                    }
                    e.remove();
                }
            } catch (Exception ignored) {
            }
        }
    }

}
