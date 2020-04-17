package dev.darkhorizon.es.virtualbosses.listeners.entity;

import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossSpawn;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class EntitySpawn implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    /**
     * Method to manage BossSpawn event
     * @param event The event
     */
    @EventHandler
    public void onBossSpawn(@NotNull BossSpawn event) {
        final LivingEntity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) { BossUtils.manageCreeperSpawn(entity); return;}
        if (entity.hasMetadata("ZombieKing")) { BossUtils.manageKingSpawn(entity); return;}
        if (entity.hasMetadata("ArmoredGolem")) { BossUtils.manageGolemSpawn(entity); return;}
        if (entity.hasMetadata("DamnedSkeleton")) { BossUtils.manageDamnedSpawn(entity); return;}
        if (entity.hasMetadata("Invocator")) { BossUtils.manageInvocatorSpawn(entity); return;}


    }







}
