package dev.darkhorizon.es.black.bosses;

import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class ZombieKing implements CustomBoss<ZombieKing> {

    private static final Main plugin = Main.getPlugin(Main.class);

    public static String name = "§a§lRey Zombie";

    public ZombieKing(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }

        final Zombie entity = loc.getWorld().spawn(loc, Zombie.class);
        entity.setCustomName(name);
        entity.setBaby(false);
        entity.setVillager(false);
        entity.setCustomNameVisible(true);
        entity.setMetadata("ZombieKing", new FixedMetadataValue(plugin, "customzombie"));
        entity.getEquipment().setHelmet(generateHelmet());
        entity.getEquipment().setChestplate(generateChestPlate());
        entity.getEquipment().setLeggings(generateLeggings());
        entity.getEquipment().setBoots(generateBoots());
        entity.getEquipment().setItemInHand(generateWeapon());
        entity.setMaxHealth(300);
        entity.setHealth(300);
        // CALL EVENT
        /*Horse mount = loc.getWorld().spawn(loc, Horse.class);
        mount.setAdult();
        mount.setVariant(Horse.Variant.UNDEAD_HORSE);
        mount.setTamed(true);
        mount.setBreed(true);
        mount.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
        mount.setPassenger(entity);
        mount.setMetadata("ZombieHorse", new FixedMetadataValue(plugin, "customhorse"));*/
        TempData.entities.add(entity);
        //TempData.entities.add(mount);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));

    }

    private ItemStack generateWeapon() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 12);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        return item;
    }

    private ItemStack generateHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    private ItemStack generateChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    private ItemStack generateLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    private ItemStack generateBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    public static void updateTarget(Zombie entity) {
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                entity.setTarget((LivingEntity) en);
                break;
            }
        }
    }
}
