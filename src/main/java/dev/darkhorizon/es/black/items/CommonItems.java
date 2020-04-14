package dev.darkhorizon.es.black.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommonItems {

    private static CommonItems INSTANCE = null;

    private CommonItems() {
        //TODO Singleton for only 1 object instance
    }

    public static CommonItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(CommonItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CommonItems();
                }
            }
        }
    }

    public ItemStack getSeparator() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1 , (short) 15);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§1");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getCraft() {
        ItemStack item = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cProceder al intercambio");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§fAsegurate de tener todos los items");
        lore.add("§fy los desafios completados.");
        lore.add("§2");
        lore.add("§a¡Clic para intercambiar¡");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getEstrellaCaida(int quantity) {
        ItemStack item = new ItemStack(Material.NETHER_STAR, quantity);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§l* §fEstrella Caída §a§l*");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getLibroMaestro(int quantity) {
        ItemStack item = new ItemStack(Material.BOOK, quantity);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§5§l✦ §fLibro Maestro §5§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

}
