package dev.darkhorizon.es.virtualbosses.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemListItems {
    private static ItemListItems INSTANCE = null;

    private ItemListItems() {
        //TODO Singleton for only 1 object instance
    }

    public static ItemListItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(ItemListItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemListItems();
                }
            }
        }
    }

    public ItemStack getLanzaMeteoros() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §cLanza Meteoros §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Poder XII");
        lore.add("§7Irrompible VIII");
        lore.add("§7Llamas VI");
        lore.add("§7Infinidad I");
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
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 12, true);
        meta.addEnchant(Enchantment.DURABILITY, 8, true);
        meta.addEnchant(Enchantment.ARROW_FIRE, 6, true);
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }
}
