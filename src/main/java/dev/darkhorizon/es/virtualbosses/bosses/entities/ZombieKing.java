package dev.darkhorizon.es.virtualbosses.bosses.entities;

import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.bosses.CustomBoss;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossSpawn;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class ZombieKing implements CustomBoss<ZombieKing> {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    public static String name = "§c§lRey Zombie";
    public static int health = 400;

    public ZombieKing(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }

        Zombie entity = loc.getWorld().spawn(loc, Zombie.class);
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
        entity.setMaxHealth(health);
        entity.setHealth(health);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 3), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 4), true);
        temp_data.getEntities().put(entity.getUniqueId(), entity);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));

    }

    private ItemStack generateWeapon() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 12);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
        return item;
    }

    private ItemStack generateHelmet() {
        ItemStack item = new ItemStack(Material.GOLD_HELMET);
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

    public static void playSkill(LivingEntity entity) {
        Random random = new Random();
        if (random.nextInt(10) == 3) {
            BossUtils.updateTarget((Zombie) entity);
        }
        int result = random.nextInt(120);
        if (result < 10) {
            ZombieKing.generateArchers(entity);
        }
        if (result > 9 && result < 20) {
            ZombieKing.generateSoldiers(entity);
        }
        if (result > 19 && result < 30) {
            ZombieKing.generateBandits(entity);
        }
        if (result > 29 && result < 40) {
            ZombieKing.generateProtector(entity);
        }

    }

    public static void generateArchers(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 10);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Arqueros a Caballo");
        for (int i = 0; i < count; i++) {
            generateArchersEntity(entity.getLocation());
        }
    }

    private static void generateArchersEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);
        Skeleton minion = new_loc.getWorld().spawn(new_loc,Skeleton.class);
        minion.setSkeletonType(Skeleton.SkeletonType.NORMAL);
        minion.setMaxHealth(80);
        minion.setHealth(80);
        minion.setCustomName("§a§lArquero del Rey Zombie");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2), true);
        minion.setMetadata("SkeletonSoldier", new FixedMetadataValue(plugin, "skeletonsoldier"));
        minion.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        minion.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        minion.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        minion.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        ItemStack weapon = new ItemStack(Material.BOW);
        weapon.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        weapon.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        weapon.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
        weapon.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
        Horse mount = new_loc.getWorld().spawn(new_loc, Horse.class);
        mount.setAdult();
        mount.setVariant(Horse.Variant.SKELETON_HORSE);
        mount.setTamed(true);
        mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        mount.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
        mount.setPassenger(minion);
        mount.setMetadata("secundary", new FixedMetadataValue(plugin, "secundary"));
        temp_data.getEntities().put(minion.getUniqueId(), minion);
        temp_data.getEntities().put(mount.getUniqueId(), mount);
    }

    public static void generateSoldiers(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 8);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Infanteria");
        for (int i = 0; i < count; i++) {
            generateSoldiersEntity(entity.getLocation());
        }
    }

    private static void generateSoldiersEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);

        Zombie minion = new_loc.getWorld().spawn(new_loc, Zombie.class);
        minion.setBaby(false);
        minion.setVillager(false);
        minion.setMaxHealth(60);
        minion.setHealth(60);
        minion.setCustomName("§a§lSoldado del Rey Zombie");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3), true);
        minion.setMetadata("NormalSoldier", new FixedMetadataValue(plugin, "normalsoldier"));
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setHelmet(helmet);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setChestplate(chestplate);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setLeggings(leggings);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setBoots(boots);
        ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
        weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 9);
        weapon.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        minion.getEquipment().setItemInHand(weapon);

        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }

    public static void generateBandits(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 10);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Bandidos");
        for (int i = 0; i < count; i++) {
            generateBanditsEntity(entity.getLocation());
        }
    }

    private static void generateBanditsEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);

        Zombie minion = new_loc.getWorld().spawn(new_loc, Zombie.class);
        minion.setMaxHealth(40);
        minion.setHealth(40);
        minion.setBaby(true);
        minion.setVillager(false);
        minion.setCustomName("§a§lBandido del Reino");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4), true);
        minion.setMetadata("BanditSoldier", new FixedMetadataValue(plugin, "banditsoldier"));
        ItemStack helmet = new ItemStack(Material.CHAINMAIL_HELMET);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setHelmet(helmet);
        ItemStack chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setChestplate(chestplate);
        ItemStack leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setLeggings(leggings);
        ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setBoots(boots);
        ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
        weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        weapon.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setItemInHand(weapon);

        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }

    public static void generateProtector(LivingEntity entity) {
        int count = BossUtils.getMinionCount(entity, 2);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "GuardaEspaldas del Rey");
        for (int i = 0; i < count; i++) {
            generateProtectorEntity(entity.getLocation());
        }
    }

    private static void generateProtectorEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);

        Zombie minion = new_loc.getWorld().spawn(new_loc, Zombie.class);
        minion.setBaby(false);
        minion.setVillager(true);
        minion.setMaxHealth(100);
        minion.setHealth(100);
        minion.setCustomName("§a§lGuardaEspaldas del Rey Zombie");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 3), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), true);
        minion.setMetadata("ProtectorSoldier", new FixedMetadataValue(plugin, "protectorsoldier"));
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        helmet.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setHelmet(helmet);
        ItemStack chestplate = new ItemStack(Material.GOLD_CHESTPLATE);
        chestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        chestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setChestplate(chestplate);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        leggings.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setLeggings(leggings);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        boots.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setBoots(boots);
        ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
        weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 11);
        weapon.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        minion.getEquipment().setItemInHand(weapon);

        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }


}
