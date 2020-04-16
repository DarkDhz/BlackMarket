package dev.darkhorizon.es.virtualbosses.config;

import dev.darkhorizon.es.virtualbosses.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileManager {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static FileManager INSTANCE = null;

    private FileManager() {
        //TODO Singleton for only 1 object instance
        //this.initFiles();
    }

    public static FileManager getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(FileManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FileManager();
                }
            }
        }
    }

    public static void initFiles() {
        initConfigConfig();
    }

    private static File config;
    private static FileConfiguration configConfig;

    private static void initConfigConfig() {
        config = new File(plugin.getDataFolder(), "config.yml");
        if (!config.exists()) {
            config.getParentFile().mkdirs();
            plugin.saveResource("config.yml", false);
        }

        configConfig = new YamlConfiguration();
        try {
            FileInputStream fileinputstream = new FileInputStream(config);
            configConfig.load(new InputStreamReader(fileinputstream, StandardCharsets.UTF_8));
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return configConfig;
    }
}
