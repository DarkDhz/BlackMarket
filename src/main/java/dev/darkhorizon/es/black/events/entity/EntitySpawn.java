package dev.darkhorizon.es.black.events.entity;

import dev.darkhorizon.es.black.Data.temp.DPList;
import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.bosses.ZombieKing;
import dev.darkhorizon.es.black.events.boss.BossDeath;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import dev.darkhorizon.es.black.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class EntitySpawn implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    @EventHandler
    public void onBossSpawn(BossSpawn event) {
        final LivingEntity entity = event.getEntity();
        if (entity.getType() == EntityType.CREEPER && entity.hasMetadata("CustomCreeper")) { manageCreeperSpawn(entity);}
        if (entity.hasMetadata("ZombieKing")) { manageKingSpawn(entity);}

    }

    private void manageKingSpawn(final LivingEntity entity) {
        temp_data.getBoss_damagers().put(entity.getUniqueId(), new DPList());
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
    }


    private void manageCreeperSpawn(final LivingEntity entity) {
        temp_data.getBoss_damagers().put(entity.getUniqueId(), new DPList());
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
}
