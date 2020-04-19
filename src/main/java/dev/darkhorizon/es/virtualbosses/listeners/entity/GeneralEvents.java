package dev.darkhorizon.es.virtualbosses.listeners.entity;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;


public class GeneralEvents implements Listener {

    /**
     * Method to manage EntityExplodeEvent
     * @param event The event
     */
    @EventHandler
    public void onExplode(@NotNull EntityExplodeEvent event) {

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
    public void onPrime(@NotNull ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onRegain(EntityRegainHealthEvent event) {
        if (event.getEntity().hasMetadata("Invocator")) {
            if (event.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.MAGIC)) {
                event.setCancelled(true);
            }
        }
    }

}
