package dev.darkhorizon.es.virtualbosses.bosses.entities;

import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.bosses.CustomBoss;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossSpawn;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class ExplosiveCreeper implements CustomBoss<ExplosiveCreeper> {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();


    public static String name = "§a§lEl Explosiones";
    public static int health = 300;

    public ExplosiveCreeper(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }
        Creeper entity = loc.getWorld().spawn(loc, Creeper.class);
        entity.setPowered(true);
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2), true);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2), true);
        entity.setMetadata("CustomCreeper", new FixedMetadataValue(plugin, "customcreeper"));
        entity.getEquipment().setHelmet(generateHelmet());
        entity.getEquipment().setChestplate(generateChestPlate());
        entity.getEquipment().setLeggings(generateLeggings());
        entity.getEquipment().setBoots(generateBoots());
        entity.setMaxHealth(health);
        entity.setHealth(health);
        // CALL EVENT

        temp_data.getEntities().put(entity.getUniqueId(), entity);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));
    }

    public static void playSkill(LivingEntity entity) {
        Random random = new Random();
        if (random.nextInt(10) == 3) {
            BossUtils.updateTarget((Creeper) entity);
        }
        int result = random.nextInt(100);
        if (result < 10) {
            ExplosiveCreeper.spawnMinions(entity);
        }
        if (result > 10 && result < 21) {
            ExplosiveCreeper.spawnRandomTNT(entity);
        }
        if (result > 20 && result < 25) {
            ExplosiveCreeper.useNuke(entity);
        }
        if (result > 25 && result < 36) {
            ExplosiveCreeper.spawnProtectors(entity);
        }
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

    public static void spawnRandomTNT(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Lluvia Explosiva");
        for (int i = 0; i < 5; i++) {
            Location new_loc = BossUtils.getValidLocation(entity.getLocation(), 8, 8);
            TNTPrimed tnt = new_loc.getWorld().spawn(new_loc, TNTPrimed.class);
            tnt.setFuseTicks(100);
        }

    }

    public static void spawnMinions(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        int count = BossUtils.getMinionCount(entity, 16);
        if (count < 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Minions Explosivos");
        for (int i = 0; i < count; i++) {
            generateEntity(entity.getLocation());
        }
    }

    public static void useNuke(final LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Nuke");
        List<Player> near = BossUtils.getNearPlayers(entity, 25);
        for (Player target : near) {
            target.teleport(entity.getLocation());
        }
        TNTPrimed tnt = entity.getWorld().spawn(entity.getLocation(), TNTPrimed.class);
        tnt.setFuseTicks(20);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() { public void run() {
            List<Player> near = BossUtils.getNearPlayers(entity, 5);
            for (Player target : near) {
                target.damage(20);
            }
        } }, 20 );
    }

    public static void spawnProtectors(LivingEntity entity) {
        if (temp_data.getEntities().size() > 100) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Protectores");
    }

    public static void generateEntity(Location loc) {
        Location new_loc = BossUtils.getValidLocation(loc, 10, 10);
        Zombie minion = new_loc.getWorld().spawn(new_loc, Zombie.class);
        minion.setBaby(true);
        minion.setCustomName("§a§lSuicida");
        minion.setVillager(false);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        minion.getEquipment().setHelmet(new ItemStack(Material.TNT));
        minion.setMetadata("suicide_minion", new FixedMetadataValue(plugin, "suicide_minion"));
        Chicken chicken = new_loc.getWorld().spawn(new_loc, Chicken.class);
        chicken.setPassenger(minion);
        chicken.setMetadata("secundary", new FixedMetadataValue(plugin, "secundary"));

        chicken.setAdult();
        chicken.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        temp_data.getEntities().put(minion.getUniqueId(), minion);
        temp_data.getEntities().put(chicken.getUniqueId(), chicken);
    }

    public static void attack(Entity entity) {
        Location loc = entity.getLocation();
        List<Player> near = BossUtils.getNearPlayers(entity, 2);
        for (Player target : near) {
            target.damage(30, entity);
        }
    }



}
