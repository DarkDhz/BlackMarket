package dev.darkhorizon.es.virtualbosses.listeners.entity;

import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    /**
     * Method to manage EntityDamageByEntityEvent
     * @param event The event
     */
    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if (BossUtils.manageCreeperDamage(event)) {
            event.setCancelled(true);
            return;
        }
        if (BossUtils.manageKingDamage(event)) {
            event.setCancelled(true);
            return;
        }
    }


}
