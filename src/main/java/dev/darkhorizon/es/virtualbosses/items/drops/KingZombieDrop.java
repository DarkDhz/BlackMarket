package dev.darkhorizon.es.virtualbosses.items.drops;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KingZombieDrop {

    private static KingZombieDrop INSTANCE = null;

    private KingZombieDrop() {
        //TODO Singleton for only 1 object instance
    }

    public static KingZombieDrop getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(KingZombieDrop.class) {
                if (INSTANCE == null) {
                    INSTANCE = new KingZombieDrop();
                }
            }
        }
    }

    public ItemStack getBossHead() {
        ItemStack item = new ItemStack(Material.GOLD_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lCorona del ReyZombie");
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§6» §fRecompensa por matar al Rey Zombie.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        item.setItemMeta(meta);
        return item;
    }
}
