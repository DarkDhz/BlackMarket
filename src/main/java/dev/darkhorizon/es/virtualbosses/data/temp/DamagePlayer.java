package dev.darkhorizon.es.virtualbosses.data.temp;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class DamagePlayer implements Comparable{

    private final UUID uuid;
    private double damage;

    public DamagePlayer(UUID id, double damage) {
        this.uuid = id;
        this.damage = damage;
    }

    public void addDamage(double damage) {
        this.damage += damage;
    }

    public double getDamage() {
        return damage;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int compareTo(@NotNull Object comp) {
        if (comp instanceof DamagePlayer) {
            double compareDamage = ((DamagePlayer) comp).getDamage();
            return (int) (compareDamage-this.damage);
        }
        return 0;
    }
}
