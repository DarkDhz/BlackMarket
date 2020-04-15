package dev.darkhorizon.es.black.utils;

import org.bukkit.Location;
import org.bukkit.entity.*;

public class BossUtils {

    public static void notifyPlayers(Entity entity, String skill) {
        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= 50) {
                player.sendMessage("§6§lBOSS §8|| §e" + entity.getCustomName() + " §7ha usado: §d" + skill);
            }
        }
    }

    public static void updateTarget(Creature entity) {
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                entity.setTarget((LivingEntity) en);
                break;
            }
        }
    }
}
