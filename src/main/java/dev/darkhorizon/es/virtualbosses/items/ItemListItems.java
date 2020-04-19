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
        lore.add("§7Empuje por flecha II");
        lore.add("§7Empuje por golpe II");
        lore.add("§7Infinidad I");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cVampiro I");
        lore.add("§cRayos II");
        lore.add("§cParalyze I");
        lore.add("§3");
        lore.add("§fCaracteristicas especiales:");
        lore.add(" §6§l» §fEfectividad doble: §a1%");
        lore.add(" §6§l» §fDaño a mobs: §a+10%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getMataDioses() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §dMata Dioses §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Afilado XIII");
        lore.add("§7Irrompible VIII");
        lore.add("§7Aspecto ígneo VII");
        lore.add("§7Saqueo IV");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cVampiro I");
        lore.add("§cAerial III");
        lore.add("§cCarga I");
        lore.add("§cThunder II");
        lore.add("§3");
        lore.add("§fCaracteristicas especiales:");
        lore.add(" §6§l» §fEfectividad doble: §a1%");
        lore.add(" §6§l» §fGolpe critico: §a10%");
        lore.add(" §6§l» §fDaño a mobs: §a+10%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getArmaduraEspacialHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §6Casco Espacial §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Protección XII");
        lore.add("§7Irrompible IX");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cLlamas II");
        lore.add("§cBerserker II");
        lore.add("§cVision I");
        lore.add("§3");
        lore.add("§fSet completo:");
        lore.add(" §6§l» §fReducción de daño: §a10%");
        lore.add(" §6§l» §fBloqueo: §a5%");
        lore.add(" §6§l» §fInversion de daño: §a2%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getArmaduraEspacialChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §6Peto Espacial §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Protección XII");
        lore.add("§7Irrompible IX");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cLlamas II");
        lore.add("§cBerserker II");
        lore.add("§3");
        lore.add("§fSet completo:");
        lore.add(" §6§l» §fReducción de daño: §a10%");
        lore.add(" §6§l» §fBloqueo: §a5%");
        lore.add(" §6§l» §fInversion de daño: §a2%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getArmaduraEspacialLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §6Pantalones Espaciales §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Protección XII");
        lore.add("§7Irrompible IX");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cLlamas II");
        lore.add("§cBerserker II");
        lore.add("§3");
        lore.add("§fSet completo:");
        lore.add(" §6§l» §fReducción de daño: §a10%");
        lore.add(" §6§l» §fBloqueo: §a5%");
        lore.add(" §6§l» §fInversion de daño: §a2%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getArmaduraEspacialBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §6Botas Espaciales §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§7Protección XII");
        lore.add("§7Irrompible IX");
        lore.add("§2");
        lore.add("§fEncantamientos especiales:");
        lore.add("§cLlamas II");
        lore.add("§cBerserker II");
        lore.add("§3");
        lore.add("§fSet completo:");
        lore.add(" §6§l» §fReducción de daño: §a10%");
        lore.add(" §6§l» §fBloqueo: §a5%");
        lore.add(" §6§l» §fInversion de daño: §a2%");
        lore.add("§4");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }

    public ItemStack getSuperPocion() {
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§0§l✦ §fSuper Poción §0§l✦");
        List<String> lore = new ArrayList<String>();
        lore.add("§2");
        lore.add("§fEfectos:");
        lore.add("§6- §710 corazones extra por 5 minutos");
        lore.add("§6- §7Fuerza II (10min)");
        lore.add("§6- §7Velocidad II (10min)");
        lore.add("§6- §7Regeneración I (2min)");
        lore.add("§6- §7Resistencia II (5min)");
        lore.add("§6- §7Saturación I (5min)");
        lore.add("§6- §7Resistencia al fuego I (10min)");
        lore.add("§3");
        lore.add("§a¡Clic para ver el intercambio!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        return item;
    }
}
