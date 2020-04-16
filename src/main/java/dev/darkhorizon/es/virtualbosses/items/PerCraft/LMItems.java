package dev.darkhorizon.es.virtualbosses.items.PerCraft;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LMItems {
    private static LMItems INSTANCE = null;

    private LMItems() {
        //TODO Singleton for only 1 object instance
    }

    public static LMItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(LMItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LMItems();
                }
            }
        }
    }

    public ItemStack getTiroCertero(Player player) {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fDesafio: §aTiro Certero");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§fEstado del Desafio: §c5§7/§a10000");
        lore.add("§2");
        lore.add("§fPista: §7Para avanzar en el desafio debes realizar");
        lore.add("§7impactos directos a jugadores al usar el arco.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getEssencia(Player player) {
        ItemStack item = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§fEssencia de §8(§eOsiris§8)");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§7Esta es recompensa por ");
        lore.add("§7matar a §dOsiris");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKoth(Player p) {
        ItemStack item = new ItemStack(Material.GOLD_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§cForjador del Nether");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§7Esta es la recompensa por ");
        lore.add("§7ganar el koth §dNether");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getString(Player p) {
        ItemStack item = new ItemStack(Material.STRING, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aHilo perdido");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§7Se obtiene al matar minions del boss §dOsiris");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getStick(Player p) {
        ItemStack item = new ItemStack(Material.STICK, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aEmpuñadura elemental");
        List<String> lore = new ArrayList<String>();
        lore.add("§1");
        lore.add("§7Se obtiene al matar minions del boss §dOsiris");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }




}
