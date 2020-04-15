package dev.darkhorizon.es.black.events.entity;

import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;


public class GeneralEvents implements Listener {

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

    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
    }

}
