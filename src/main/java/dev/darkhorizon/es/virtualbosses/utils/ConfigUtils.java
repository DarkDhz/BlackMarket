package dev.darkhorizon.es.virtualbosses.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class ConfigUtils {

    /**
     * Method to replace simple characters
     * @param path YML path of the msg
     * @param prefix Plugin prefix
     * @param file File where is the path
     * @return Replaced String
     */
    @NotNull
    public static String simpleReplace(String path, String prefix, @NotNull FileConfiguration file) {
        return file.getString(path).replaceAll("&", "§").replaceAll("%prefix", prefix);
    }
}
