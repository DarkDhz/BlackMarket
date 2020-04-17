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
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Invocator implements CustomBoss<Invocator> {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();


    public static String name = "§d§lInvocador del Hielo";
    public static int health = 300;

    /**
     * Method to generate the boss
     * @param loc Where the boss is generated
     */
    public Invocator(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }
        Witch entity = loc.getWorld().spawn(loc, Witch.class);
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 5), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4), true);
        entity.setMetadata("Invocator", new FixedMetadataValue(plugin, "invocator"));
        entity.getEquipment().setHelmet(generateHelmet());
        entity.getEquipment().setChestplate(generateChestPlate());
        entity.getEquipment().setLeggings(generateLeggings());
        entity.getEquipment().setBoots(generateBoots());
        entity.getEquipment().setItemInHand(generateWeapon());
        entity.setMaxHealth(health);
        entity.setHealth(health);
        // CALL EVENT

        temp_data.getEntities().put(entity.getUniqueId(), entity);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));
    }

    /**
     * Method to get Boss Weapon
     * @return The item
     */
    @NotNull
    private ItemStack generateWeapon() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 12);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 4);
        return item;
    }

    /**
     * Method to get Boss Helmet
     * @return The item
     */
    @NotNull
    private ItemStack generateHelmet() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    /**
     * Method to get Boss ChestPlate
     * @return The item
     */
    @NotNull
    private ItemStack generateChestPlate() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    /**
     * Method to get Boss Leggings
     * @return The item
     */
    @NotNull
    private ItemStack generateLeggings() {
        ItemStack item = new ItemStack(Material.DIAMOND_LEGGINGS);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    /**
     * Method to get Boss Boots
     * @return The item
     */
    @NotNull
    private ItemStack generateBoots() {
        ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
        item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 7);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
        return item;
    }

    /**
     * Method to manage Boss skills
     * @param entity The boss
     */
    public static void playSkill(LivingEntity entity) {
        if (BossUtils.getChance(30)) {
            BossUtils.updateTarget((Witch) entity);
        }
        if (BossUtils.getChance(8)) {
            generateBlazes(entity);
            return;
        }
        if (BossUtils.getChance(8)) {
            generateSlimes(entity);
            return;
        }
        if (BossUtils.getChance(8)) {
            generateGuardians(entity);
            return;
        }
        if (BossUtils.getChance(8)) {
            generateWithers(entity);
            return;
        }
    }

    /**
     * Method to generate blazes
     * @param entity Boss entity
     */
    private static void generateBlazes(Entity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 8);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Invocar Blazes");
        for (int i = 0; i < count; i++) {
            generateBlazesEntity(entity.getLocation());
        }
    }

    private static void generateBlazesEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);
        Blaze minion = new_loc.getWorld().spawn(new_loc, Blaze.class);
        minion.setMaxHealth(80);
        minion.setHealth(80);
        minion.setCustomName("§a§lBlaze Asesino");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5), true);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2), true);
        minion.setMetadata("invocator_blazes", new FixedMetadataValue(plugin, "minion"));
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
        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }

    private static void generateSlimes(Entity entity) {

    }

    private static void generateGuardians(Entity entity) {

    }

    private static void generateWithers(Entity entity) {

    }

}
