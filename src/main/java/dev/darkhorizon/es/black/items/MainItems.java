package dev.darkhorizon.es.black.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainItems {
    private static MainItems INSTANCE = null;

    private MainItems() {
        //TODO Singleton for only 1 object instance
    }

    public static MainItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(MainItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainItems();
                }
            }
        }
    }

    public ItemStack getHelp() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cAyuda");
        List<String> lore = new ArrayList<String>();
        lore.add("§0");
        lore.add("§fEste es el menú de creación de items");
        lore.add("§fcon poderes que nunca antes hubieras imaginado.");
        lore.add("§1");
        lore.add("§aComo conseguir estos items:");
        lore.add("§fPara ello necesitaras mucho trabajo,");
        lore.add("§ftendras que eliminar bosses, ganar koth");
        lore.add("§fahorrar dinero y cumplir unos ciertos objetivos.");
        lore.add("§2");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getSeparator() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1 , (short) 15);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§1");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getItemList() {
        ItemStack item = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aItems Disponibles");
        List<String> lore = new ArrayList<String>();
        lore.add("§fClic para ver la lista de items");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }
}
