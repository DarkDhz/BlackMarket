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
        meta.setDisplayName("§a§lCorona del Rey Zombie");
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§6» §fRecompensa por matar al Rey Zombie.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 10, true);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKingSword() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 4);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lEspada del Rey Zombie");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKingChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 8);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 6);
        item.addUnsafeEnchantment(Enchantment.THORNS, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§a§lArmadura del Rey Zombie");
        item.setItemMeta(meta);
        return item;
    }
}
