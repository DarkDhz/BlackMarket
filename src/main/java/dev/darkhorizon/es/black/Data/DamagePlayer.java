package dev.darkhorizon.es.black.Data;

public class DamagePlayer {

    String uuid;
    double damage;

    public DamagePlayer(String id, double damage) {
        this.uuid = id;
        this.damage = damage;
    }

    public void addDamage(double damage) {
        this.damage += damage;
    }

    public double getDamage() {
        return damage;
    }

    public String getUuid() {
        return uuid;
    }
}
