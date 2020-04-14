package dev.darkhorizon.es.black.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DPList {
    List<DamagePlayer> list;

    public DPList() {
        list = new ArrayList<DamagePlayer>();
    }

    public void addDamage(String uuid, double damage) {
        if (!containsUUID(uuid)) {
            list.add(new DamagePlayer(uuid, damage));
        } else {
            getByUUID(uuid).addDamage(damage);
        }
    }

    public boolean containsUUID(String uuid) {
        for (DamagePlayer check : list) {
            if (check.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    public DamagePlayer getByUUID(String uuid) {
        for (DamagePlayer check : list) {
            if (check.getUuid().equals(uuid)) {
                return check;
            }
        }
        return null;
    }

    public List<DamagePlayer> getTop3() {
        Collections.sort(list);
        return list;

    }
    public double getDamage(String uuid) {
        return getByUUID(uuid).getDamage();
    }
}
