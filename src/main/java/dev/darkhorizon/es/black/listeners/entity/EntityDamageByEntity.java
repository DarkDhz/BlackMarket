package dev.darkhorizon.es.black.listeners.entity;

import dev.darkhorizon.es.black.data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.Random;

public class EntityDamageByEntity implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        if (manageCreeper(event)) {
            event.setCancelled(true);
        }
        if (manageKing(event)) {
            event.setCancelled(true);
        }
    }

    private boolean manageKing(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return false;
        }
        LivingEntity target = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Arrow && target.hasMetadata("ZombieKing")) {
            Arrow arr = (Arrow) event.getDamager();
            if (!(arr.getShooter() instanceof Player)) {
                return false;
            }
            Player launcher = (Player) arr.getShooter();
            temp_data.getBoss_damagers().get(target.getUniqueId()).addDamage("" + launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("ZombieKing") && damager instanceof Player) {
            temp_data.getBoss_damagers().get(target.getUniqueId()).addDamage("" + damager.getUniqueId(), event.getFinalDamage());
            return false;
        }
        return false;
    }

    private boolean manageCreeper(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof LivingEntity)) {
            return false;
        }
        LivingEntity target = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Arrow && target.hasMetadata("CustomCreeper")) {
            Arrow arr = (Arrow) event.getDamager();
            if (!(arr.getShooter() instanceof Player)) {
                return false;
            }
            Player launcher = (Player) arr.getShooter();
            temp_data.getBoss_damagers().get(target.getUniqueId()).addDamage("" + launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("CustomCreeper") && damager instanceof Player) {
            try {
                temp_data.getBoss_damagers().get(target.getUniqueId()).addDamage("" + damager.getUniqueId(), event.getFinalDamage());
                Vector unitVector = new Vector(damager.getLocation().getDirection().getX() * -3, 2,damager.getLocation().getDirection().getZ() * -3);
                damager.setVelocity(unitVector);
            } catch (Exception ignored) { }
            return false;
        }

        if (damager.getType() == EntityType.PRIMED_TNT && target.getType() == EntityType.CREEPER && target.hasMetadata("CustomCreeper")) {
            return true;
        }
        if (damager.hasMetadata("suicide_minion") && target instanceof Player) {
            damager.remove();

            damager.getWorld().playEffect(damager.getLocation(), Effect.EXPLOSION, null);
            damager.getWorld().playSound(damager.getLocation(), Sound.EXPLODE, 1, 0);
            target.setVelocity((target.getLocation().getDirection().multiply(-1).add(new Vector(0, 0.5, 0))));
            Random random = new Random();

            target.damage(random.nextInt(15) + 10);

            return false;
        }
        return false;
    }
}
