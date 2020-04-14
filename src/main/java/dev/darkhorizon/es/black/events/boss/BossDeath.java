package dev.darkhorizon.es.black.events.boss;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BossDeath extends Event {

    private final LivingEntity entity;


    private static final HandlerList HANDLERS = new HandlerList();

    public BossDeath(LivingEntity e) {
        this.entity = e;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }
}