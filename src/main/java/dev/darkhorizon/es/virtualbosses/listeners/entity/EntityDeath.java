package dev.darkhorizon.es.virtualbosses.listeners.entity;

import dev.darkhorizon.es.virtualbosses.bosses.entities.*;
import dev.darkhorizon.es.virtualbosses.data.temp.DPList;
import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossDeath;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EntityDeath implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    /**
     * Method to manage EntityDeathEvent
     * @param event The event
     */
    @EventHandler
    public void onEntityDeath(@NotNull EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.VOID) {
            return;
        }
        if (entity.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.CUSTOM) {
            return;
        }
        //CREEPER
        if (entity.hasMetadata("secundary")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("CustomCreeper") || entity.hasMetadata("ZombieKing")
                || entity.hasMetadata("ArmoredGolem")
                || entity.hasMetadata("DamnedSkeleton")
                || entity.hasMetadata("Invocator")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        // CREEPER MINIONS
        if (entity.hasMetadata("suicide_minion")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            /*if (entity.getKiller() != null && entity instanceof Player) {
                Random random = new Random();
                int i = random.nextInt(100);
                if (i <= 5){
                    event.getDrops().add(new ItemStack(Material.TNT));
                }
            }*/
            return;
        }
        if (entity.hasMetadata("creeper_protector")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }

        // ZOMBIE MINIONS
        if (entity.hasMetadata("ProtectorSoldier")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("BanditSoldier")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("NormalSoldier")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("SkeletonSoldier")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        // INVOCATOR MINIONS
        if (entity.hasMetadata("invocator_blazes")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("invocator_guardians")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("invocator_wither")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }

    }

    /**
     * Method to manage BossDeath event
     * @param event The event
     */
    @EventHandler
    public void onBossDeath(@NotNull BossDeath event) {
        DPList dp = temp_data.getBoss_damagers().get(event.getEntity().getUniqueId());
        if (dp == null) {
            return;
        }
        String name = "unset";
        if (event.getEntity().hasMetadata("CustomCreeper")) {
            name = ExplosiveCreeper.name;
        }
        if (event.getEntity().hasMetadata("ZombieKing")) {
            name = ZombieKing.name;
        }
        if (event.getEntity().hasMetadata("ArmoredGolem")) {
            name = ArmoredGolem.name;
        }
        if (event.getEntity().hasMetadata("DamnedSkeleton")) {
            name = DamnedSkeleton.name;
        }
        if (event.getEntity().hasMetadata("Invocator")) {
            name = Invocator.name;
        }
        if (name.equals("unset")) {
            return;
        }
        BossUtils.manageTopShow(dp, event.getEntity().getUniqueId(), name);
    }

}
