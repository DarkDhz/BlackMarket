package dev.darkhorizon.es.black.Data;

import dev.darkhorizon.es.black.bosses.CustomBoss;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempData {


    public static List<LivingEntity> entities = new ArrayList<LivingEntity>();
    public static Map<String, Integer> shoot_challenge = new HashMap<String, Integer>();
    public static Map<String, DPList> damagers = new HashMap<String, DPList>();
}
