package dev.darkhorizon.es.virtualbosses.data.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class DPList {
    List<DamagePlayer> list;

    public DPList() {
        list = new ArrayList<DamagePlayer>();
    }

    public void addDamage(UUID uuid, double damage) {
        if (!containsUUID(uuid)) {
            list.add(new DamagePlayer(uuid, damage));
        } else {
            getByUUID(uuid).addDamage(damage);
        }
    }

    public boolean containsUUID(UUID uuid) {
        for (DamagePlayer check : list) {
            if (check.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public DamagePlayer getByUUID(UUID uuid) {
        for (DamagePlayer check : list) {
            if (check.getUuid().equals(uuid)) {
                return check;
            }
        }
        return null;
    }

    public List<DamagePlayer> getSorted() {
        Collections.sort(list);
        return list;

    }

    public List<DamagePlayer> getList() {
        return list;
    }

    public double getDamage(UUID uuid) {
        return getByUUID(uuid).getDamage();
    }
}
