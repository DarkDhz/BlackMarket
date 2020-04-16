package dev.darkhorizon.es.virtualbosses.items;

import dev.darkhorizon.es.virtualbosses.bosses.entities.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BossItems {

    private static BossItems INSTANCE = null;

    private BossItems() {
        //TODO Singleton for only 1 object instance
    }

    public static BossItems getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized(BossItems.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BossItems();
                }
            }
        }
    }

    public ItemStack getCreeper() {
        ItemStack item = new ItemStack(Material.TNT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ExplosiveCreeper.name);
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§fVida: §d" + ExplosiveCreeper.health);
        lore.add("§2");
        lore.add("§fSkills: ");
        lore.add("§6- §fMinions §8(Zombies Bomba)");
        lore.add("§6- §fNuke §8(Te atrae y explotas)");
        lore.add("§6- §fProtectores §8(Silverfish Asesinos)");
        lore.add("§6- §fRandom TNT §8(Cae TNT del Cielo)");
        lore.add("§3");
        lore.add("§fDrops: ");
        lore.add("§6- §fUndone");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getKing() {
        ItemStack item = new ItemStack(Material.GOLD_HELMET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ZombieKing.name);
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§fVida: §d" + ZombieKing.health);
        lore.add("§2");
        lore.add("§fSkills: ");
        lore.add("§6- §fArqueros §8(Esqueletos a caballo)");
        lore.add("§6- §fSoldados §8(Zombis armados)");
        lore.add("§6- §fBandidos §8(MiniZombies Asesinos)");
        lore.add("§6- §fSuperSoldado §8(La mano derecha del rey)");
        lore.add("§3");
        lore.add("§fDrops: ");
        lore.add("§6- §fCorona del Rey (TOP 1)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getInvocator() {
        ItemStack item = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Invocator.name);
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§fVida: §d" + Invocator.health);
        lore.add("§2");
        lore.add("§fSkills: ");
        lore.add("§6- §fSlime §8(Invoca un slime gigante)");
        lore.add("§6- §fBlazes §8(Invoca blazes)");
        lore.add("§6- §fGuardianes §8(Invoca guardianes)");
        lore.add("§6- §fArañas de Cueva §8(Invoca arañas de cueva)");
        lore.add("§6- §fPigman §8(Invoca zombies pigman)");
        lore.add("§6- §fCuración §8(Recuper 5% de la vida)");
        lore.add("§3");
        lore.add("§fDrops: ");
        lore.add("§6- §fUndone");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getDamned() {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DamnedSkeleton.name);
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§fVida: §d" + DamnedSkeleton.health);
        lore.add("§2");
        lore.add("§fSkills: ");
        lore.add("§6- §fFuria Oscura §8(Efecto muy dañino)");
        lore.add("§6- §fInvocar Ejercito §8(Aparecen sus esbirros)");
        lore.add("§3");
        lore.add("§fDrops: ");
        lore.add("§6- §fUndone");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getGolem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ArmoredGolem.name);
        List<String> lore = new ArrayList<>();
        lore.add("§1");
        lore.add("§fVida: §d" + ArmoredGolem.health);
        lore.add("§2");
        lore.add("§fSkills: ");
        lore.add("§6- §fA la calle §8(Te empuja a tomar por culo)");
        lore.add("§6- §fInvocar Ayudantes §8(Aparecen sus esbirros)");
        lore.add("§6- §fMega Daño §8(Golpe critico muy efectivo)");
        lore.add("§3");
        lore.add("§fDrops: ");
        lore.add("§6- §fUndone");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.DURABILITY, 0, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

}
