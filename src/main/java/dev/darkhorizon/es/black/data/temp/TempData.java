package dev.darkhorizon.es.black.data.temp;

import org.bukkit.entity.LivingEntity;

import java.util.*;

public class TempData {

    private static TempData INSTANCE = null;

    private Map<UUID, LivingEntity> entities;
    private Map<UUID, Integer> shoot_challenge;
    private Map<UUID, DPList> boss_damagers;
    private Date last_spawn;

    private TempData() {
        entities = new HashMap<UUID, LivingEntity>();
        shoot_challenge = new HashMap<UUID, Integer>();
        boss_damagers = new HashMap<UUID, DPList>();
        Calendar cal = Calendar.getInstance();
        last_spawn = cal.getTime();
        //TODO Singleton for only 1 object instance
    }

    public static TempData getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(TempData.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TempData();
                }
            }
        }
    }

    public Map<UUID, LivingEntity> getEntities() {
        return entities;
    }

    public Map<UUID, Integer> getShoot_challenge() {
        return shoot_challenge;
    }

    public Map<UUID, DPList> getBoss_damagers() {
        return boss_damagers;
    }

    public Date getLastSpawn() {
        return last_spawn;
    }

    public void setLastSpawn(Date last_spawn) {
        this.last_spawn = last_spawn;
    }
}
