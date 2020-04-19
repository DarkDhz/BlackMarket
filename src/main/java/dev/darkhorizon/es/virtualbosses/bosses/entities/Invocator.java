package dev.darkhorizon.es.virtualbosses.bosses.entities;

import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.bosses.CustomBoss;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossSpawn;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2), true);
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
        if (BossUtils.getChance(10)) {
            potionSkill(entity);
            return;
        }
        if (BossUtils.getChance(5)) {
            potionSkill(entity);
            return;
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
     * Skill that regenerate witch life
     * @param entity
     */
    private static void regenerate(LivingEntity entity) {
        if ((entity.getHealth()*0.05) >= health) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Regeneración");
        entity.setHealth((entity.getHealth()*0.05));
    }

    /**
     * Skill that cause negative effects to players
     * @param entity Boss entity
     */
    private static void potionSkill(Entity entity) {
        List<Player> players = BossUtils.getNearPlayers(entity, 20);
        if (players.size() == 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Conjunto de Pociones");
        for (Player p : players) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10*20, 2), false);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*20, 2), false);
            p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10*20, 2), true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 30*20, 2), true);
            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*20, 2), true);
            p.playSound(p.getLocation(), Sound.SPLASH, 10, 1);
        }
    }

    /**
     * Method to generate blazes
     * @param entity Boss entity
     */
    private static void generateBlazes(Entity entity) {
        if (temp_data.getEntities().size() > Main.max_entities) {
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

    /**
     * Method to spawn Blazes Minion
     * @param loc Initial Location
     */
    private static void generateBlazesEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);
        Blaze minion = new_loc.getWorld().spawn(new_loc, Blaze.class);
        minion.setMaxHealth(120);
        minion.setHealth(120);
        minion.setCustomName("§a§lBlaze Asesino");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5), true);
        minion.setMetadata("invocator_blazes", new FixedMetadataValue(plugin, "minion"));
        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }

    private static void generateSlimes(Entity entity) {

    }

    /**
     * Method to generate guardians
     * @param entity Boss entity
     */
    private static void generateGuardians(Entity entity) {
        if (temp_data.getEntities().size() > Main.max_entities) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 8);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Invocar Guardianes");
        for (int i = 0; i < count; i++) {
            generateGuardiansEntity(entity.getLocation());
        }
    }

    /**
     * Method to spawn Guardian Minion
     * @param loc Initial Location
     */
    private static void generateGuardiansEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);
        Guardian minion = new_loc.getWorld().spawn(new_loc, Guardian.class);
        minion.setMaxHealth(120);
        minion.setHealth(120);
        minion.setCustomName("§a§lGuardian del mar profundo");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5), true);
        minion.setMetadata("invocator_guardians", new FixedMetadataValue(plugin, "minion"));
        temp_data.getEntities().put(minion.getUniqueId(), minion);

    }

    /**
     * Method to generate wither
     * @param entity Boss entity
     */
    private static void generateWithers(Entity entity) {
        if (temp_data.getEntities().size() > Main.max_entities) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 8);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Invocar Esqueletos Malditos");
        for (int i = 0; i < count; i++) {
            generateWithersEntity(entity.getLocation());
        }
    }

    /**
     * Method to spawn Wither Minion
     * @param loc Initial Location
     */
    private static void generateWithersEntity(Location loc ) {
        Location new_loc = BossUtils.getValidLocation(loc, 5, 5);
        Skeleton minion = new_loc.getWorld().spawn(new_loc, Skeleton.class);
        minion.setMaxHealth(150);
        minion.setHealth(150);
        minion.setSkeletonType(Skeleton.SkeletonType.WITHER);
        ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD);
        weapon.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
        minion.getEquipment().setItemInHand(weapon);
        minion.setCustomName("§a§lEsqueleto Maldito");
        minion.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 5), true);
        minion.setMetadata("invocator_wither", new FixedMetadataValue(plugin, "minion"));
        minion.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        minion.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        minion.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        minion.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        temp_data.getEntities().put(minion.getUniqueId(), minion);
    }

}
