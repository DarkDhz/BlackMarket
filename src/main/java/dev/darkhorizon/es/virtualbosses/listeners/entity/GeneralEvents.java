package dev.darkhorizon.es.virtualbosses.listeners.entity;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;


public class GeneralEvents implements Listener {

    /**
     * Method to manage EntityExplodeEvent
     * @param event The event
     */
    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        Entity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
        if (entity.getType() == EntityType.PRIMED_TNT){
            event.setCancelled(true);
        }
    }

    /**
     * Method to manage ExplosionPrimeEvent
     * @param event The event
     */
    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
    }

}
