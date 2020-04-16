package dev.darkhorizon.es.virtualbosses.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GiveItems {

    private static GiveItems INSTANCE = null;

    private GiveItems() {
        //TODO Singleton for only 1 object instance
    }

    public static GiveItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(GiveItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GiveItems();
                }
            }
        }
    }

    public ItemStack getLanzaMeteoros() {
        ItemStack item = new ItemStack(Material.BOW);
        item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 12);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 8);
        item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 6);
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §cLanza Meteoros §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§2");
        lore.add("§fEncantamientos Especiales:");
        lore.add("§cMultitiro II");
        lore.add("§cRayos II");
        lore.add("§3");
        lore.add("§fCaracteristicas Especiales:");
        lore.add("§c§l• §7Efectividad Doble: §a1%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el crafteo!");
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
