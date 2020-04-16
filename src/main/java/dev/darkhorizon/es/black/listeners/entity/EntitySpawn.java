package dev.darkhorizon.es.black.listeners.entity;

import dev.darkhorizon.es.black.data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.listeners.boss.BossSpawn;
import dev.darkhorizon.es.black.utils.BossUtils;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntitySpawn implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    @EventHandler
    public void onBossSpawn(BossSpawn event) {
        final LivingEntity entity = event.getEntity();
        if (entity.hasMetadata("CustomCreeper")) { BossUtils.manageCreeperSpawn(entity); return;}
        if (entity.hasMetadata("ZombieKing")) { BossUtils.manageKingSpawn(entity); return;}
        if (entity.hasMetadata("ArmoredGolem")) { BossUtils.manageGolemSpawn(entity); return;}
        if (entity.hasMetadata("DamnedSkeleton")) { BossUtils.manageDamnedSpawn(entity); return;}
        if (entity.hasMetadata("Invocator")) { BossUtils.manageInvocatorSpawn(entity); return;}


    }







}
