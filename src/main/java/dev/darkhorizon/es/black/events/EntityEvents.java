package dev.darkhorizon.es.black.events;

import dev.darkhorizon.es.black.Data.DPList;
import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.events.boss.BossDeath;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import dev.darkhorizon.es.black.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;


public class EntityEvents implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onExplode(EntityExplodeEvent event) {

        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER &&  entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
        if (entity.getType() == EntityType.PRIMED_TNT){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPrime(ExplosionPrimeEvent event) {
        Entity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBossSpawn(BossSpawn event) {
        final LivingEntity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) { manageCreeperSpawn(entity);}



    }

    private void manageCreeperSpawn(final LivingEntity entity) {
        TempData.damagers.put("" + entity.getUniqueId(), new DPList());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                }
                sendMessage(entity);
                Random random = new Random();
                if (random.nextInt(10) == 3) {
                    CustomCreeper.updateTarget((Creeper) entity);
                }
                int result = random.nextInt(100);
                if (result < 10) {
                    CustomCreeper.spawnMinions(entity);
                }
                if (result > 10 && result < 21) {
                    CustomCreeper.spawnRandomTNT(entity);
                }
            }
        }.runTaskTimer(plugin, 40, 3*20);
        return;

    }

    @EventHandler
    public void onBossDeath(BossDeath event) {
        TempData.damagers.remove("" + event.getEntity().getUniqueId());
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        //CREEPER
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) {
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("suicide_minion")) {
            event.getDrops().clear();
            if (entity.getKiller() != null && entity instanceof Player) {
                Random random = new Random();
                int i = random.nextInt(100);
                if (i <= 5){
                    event.getDrops().add(new ItemStack(Material.TNT));
                }
            }
            return;
        }
    }


    private void sendMessage(LivingEntity entity) {
        for (Entity player : entity.getNearbyEntities(10,10, 10)) {
            if (player instanceof Player) {

                String msg = entity.getCustomName() + " §8» §7Vida restante: §6" + Math.round(entity.getHealth())
                        + "§7/§6" + Math.round(entity.getMaxHealth());
                ActionBar.sendMessage((Player) player, msg);

            }
        }
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event) {
        System.out.println(event.getFinalDamage());
        if (manageCreeper(event)) {
            event.setCancelled(true);
        }
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
            TempData.damagers.get("" + target.getUniqueId()).addDamage("" + launcher.getUniqueId(), event.getFinalDamage());
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        //System.out.println(event.getDamage(EntityDamageEvent.DamageModifier.ARMOR)/10);
        //System.out.println(target.getHealth());
        if (target.hasMetadata("CustomCreeper") && damager instanceof Player) {
            try {
                Vector unitVector = new Vector(damager.getLocation().getDirection().getX() * -3, 2,damager.getLocation().getDirection().getZ() * -3);

                ((Player) damager).setVelocity(unitVector);
            } catch (Exception ignored) { }
            return false;
        }

        if (damager.getType() == EntityType.PRIMED_TNT && target.getType() == EntityType.CREEPER && target.hasMetadata("CustomCreeper")) {
            return true;
        }
        if (damager.hasMetadata("suicide_minion") && target instanceof Player) {
            damager.damage(10000);

            damager.getWorld().playEffect(damager.getLocation(), Effect.EXPLOSION, null);
            damager.getWorld().playSound(damager.getLocation(), Sound.EXPLODE, 1, 0);
            target.setVelocity((target.getLocation().getDirection().multiply(-1).add(new Vector(0, 0.5, 0))));
            Random random = new Random();

            target.damage(random.nextInt(3) + 4);
            return false;
        }
        return false;
    }

}
