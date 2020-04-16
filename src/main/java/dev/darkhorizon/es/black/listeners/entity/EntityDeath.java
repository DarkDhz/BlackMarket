package dev.darkhorizon.es.black.listeners.entity;

import dev.darkhorizon.es.black.bosses.entities.*;
import dev.darkhorizon.es.black.data.temp.DPList;
import dev.darkhorizon.es.black.data.temp.DamagePlayer;
import dev.darkhorizon.es.black.data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.listeners.boss.BossDeath;
import dev.darkhorizon.es.black.utils.BossUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;

public class EntityDeath implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        //CREEPER
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
            if (entity.getKiller() != null && entity instanceof Player) {
                Random random = new Random();
                int i = random.nextInt(100);
                if (i <= 5){
                    event.getDrops().add(new ItemStack(Material.TNT));
                }
            }
            return;
        }
        // ZOMBIE MINIONS
        if (entity.hasMetadata("ProtectorSoldier")) {
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("BanditSoldier")) {
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("NormalSoldier")) {
            event.getDrops().clear();
            return;
        }
        if (entity.hasMetadata("SkeletonSoldier")) {
            event.getDrops().clear();
            return;
        }
    }

    @EventHandler
    public void onBossDeath(BossDeath event) {
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
