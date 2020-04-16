package dev.darkhorizon.es.virtualbosses.utils;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {

    /**
     * Method to replace simple characters
     * @param path YML path of the msg
     * @param prefix Plugin prefix
     * @param file File where is the path
     * @return Replaced String
     */
    public static String simpleReplace(String path, String prefix, FileConfiguration file) {
        return file.getString(path).replaceAll("&", "§").replaceAll("%prefix", prefix);
    }
}
