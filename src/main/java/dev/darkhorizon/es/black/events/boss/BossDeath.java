package dev.darkhorizon.es.black.events.boss;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BossDeath extends Event {

    private final Entity entity;


    private static final HandlerList HANDLERS = new HandlerList();

    public BossDeath(Entity e) {
        this.entity = e;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Entity getEntity() {
        return this.entity;
    }
}