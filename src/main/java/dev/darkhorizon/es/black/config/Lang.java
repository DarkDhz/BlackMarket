package dev.darkhorizon.es.black.config;

import dev.darkhorizon.es.black.utils.ConfigUtils;

public class Lang {

    private FileManager fm = FileManager.getInstance();
    private static Lang INSTANCE = null;

    private Lang() {
        //TODO Singleton for only 1 object instance
    }

    public static Lang getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(Lang.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Lang();
                }
            }
        }
    }

    public String global_prefix = fm.getConfig().getString("lang.global.prefix").replaceAll("&", "§");
    public String global_only_players = ConfigUtils.simpleReplace("lang.global.only_players", global_prefix, fm.getConfig());

    //public String cmd_blackmarket_usage = "Usage: /blackmarket";

}
