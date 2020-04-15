package dev.darkhorizon.es.black.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigUtils {

    public static String simpleReplace(String path, String prefix, FileConfiguration file) {
        return file.getString(path).replaceAll("&", "§").replaceAll("%prefix", prefix);
    }
}
