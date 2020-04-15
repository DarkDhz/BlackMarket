package dev.darkhorizon.es.black.config;

public class Perms {

    private FileManager fm = FileManager.getInstance();
    private static Perms INSTANCE = null;

    private Perms() {
        //TODO Singleton for only 1 object instance
    }

    public static Perms getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(Perms.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Perms();
                }
            }
        }
    }

    public String vb_spawn = "virtual.boss.spawn";
    public String vb_kill = "virtual.boss.kill";
}
