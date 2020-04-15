package dev.darkhorizon.es.black.Data.temp;

import org.jetbrains.annotations.NotNull;

public class DamagePlayer implements Comparable{

    private final String uuid;
    private double damage;

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


    public int compareTo(DamagePlayer comp) {
        double compareDamage = comp.getDamage();
        return (int) (this.damage-compareDamage);
    }

    public int compareTo(@NotNull Object comp) {
        if (comp instanceof DamagePlayer) {
            double compareDamage = ((DamagePlayer) comp).getDamage();
            return (int) (compareDamage-this.damage);
        }
        return 0;
    }
}
