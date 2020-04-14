package dev.darkhorizon.es.black.events;

import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import dev.darkhorizon.es.black.utils.ActionBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.scheduler.BukkitRunnable;


public class EntityEvents implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        Entity entity = event.getEntity();
        System.out.println(entity.getCustomName());
        if (entity.getType() == EntityType.CREEPER &&  entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
        if (entity.getType() == EntityType.PRIMED_TNT){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) {
            CustomCreeper.pushPlayers(entity);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBossSpawn(BossSpawn event) {
        final LivingEntity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) {
            new BukkitRunnable() {
                public void run() {
                    if (entity.isDead()) {
                        cancel();
                    }
                    sendMessage(entity);
                }
            }.runTaskTimerAsynchronously(plugin, 40, 2*20);
        }
    }

    private void sendMessage(LivingEntity entity) {
        for (Entity player : entity.getNearbyEntities(10,10, 10)) {
            if (player instanceof Player) {
                String msg = "La vida del boss es: " + entity.getHealth();
                ActionBar.sendActionBar((Player) player, msg, 10);
            }
        }
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        LivingEntity target = (LivingEntity) event.getEntity();
        System.out.println(event.getDamage(EntityDamageEvent.DamageModifier.ARMOR)/10);
        System.out.println(target.getHealth());
        if (damager.getType() == EntityType.PRIMED_TNT && target.getType() == EntityType.CREEPER && target.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
    }
}
