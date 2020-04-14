package dev.darkhorizon.es.black.events;

import dev.darkhorizon.es.black.Data.DPList;
import dev.darkhorizon.es.black.Data.DamagePlayer;
import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.bosses.ZombieKing;
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
import java.util.UUID;


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
        if (entity.hasMetadata("ZombieKing")) { manageKingSpawn(entity);}

    }

    private void manageKingSpawn(final LivingEntity entity) {
        TempData.damagers.put("" + entity.getUniqueId(), new DPList());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                CustomCreeper.attack(entity);
                Random random = new Random();
                if (random.nextInt(10) == 3) {
                    ZombieKing.updateTarget((Zombie) entity);
                }
            }
        }.runTaskTimer(plugin, 0, 3*20);
        return;
    }


    private void manageCreeperSpawn(final LivingEntity entity) {
        TempData.damagers.put("" + entity.getUniqueId(), new DPList());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                CustomCreeper.attack(entity);
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
                if (result > 20 && result < 25) {
                    CustomCreeper.useNuke(entity);
                }
                if (result > 25 && result < 36) {
                    CustomCreeper.spawnProtectors(entity);
                }
            }
        }.runTaskTimer(plugin, 0, 3*20);
        return;

    }

    @EventHandler
    public void onBossDeath(BossDeath event) {
        String name = "unset";
        if (event.getEntity().hasMetadata("CustomCreeper")) {
            name = CustomCreeper.name;
        }
        if (event.getEntity().hasMetadata("ZombieKing")) {
            name = ZombieKing.name;
        }
        Bukkit.broadcastMessage("§4");
        Bukkit.broadcastMessage("§8§m---------§8[§r §e" + name + "§7 ha muerto §8]§r§8§m---------");
        Bukkit.broadcastMessage("§7Top 5 Asesinos:");
        DPList dp = TempData.damagers.get("" + event.getEntity().getUniqueId());
        if (dp.getTop3().size() > 5) {
            for (int i = 0; i <= 5; i++) {
                DamagePlayer winner = TempData.damagers.get("" + event.getEntity().getUniqueId()).getTop3().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        } else {
            for (int i = 0; i < dp.getTop3().size(); i++) {
                DamagePlayer winner = TempData.damagers.get("" + event.getEntity().getUniqueId()).getTop3().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        }
        TempData.damagers.remove("" + event.getEntity().getUniqueId());
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        //CREEPER
        if (entity.hasMetadata("CustomCreeper") || entity.hasMetadata("ZombieKing")) {
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
            TempData.damagers.get("" + target.getUniqueId()).addDamage("" + launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("ZombieKing") && damager instanceof Player) {
            TempData.damagers.get("" + target.getUniqueId()).addDamage("" + damager.getUniqueId(), event.getFinalDamage());
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
            TempData.damagers.get("" + target.getUniqueId()).addDamage("" + launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("CustomCreeper") && damager instanceof Player) {
            try {
                TempData.damagers.get("" + target.getUniqueId()).addDamage("" + damager.getUniqueId(), event.getFinalDamage());
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

            target.damage(random.nextInt(15) + 10);

            return false;
        }
        return false;
    }

}
