package dev.darkhorizon.es.black.bosses.entities;

import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import dev.darkhorizon.es.black.utils.BossUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CustomCreeper implements CustomBoss<CustomCreeper> {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();


    public static String name = "§a§lEl Explosiones";

    public CustomCreeper(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }
        final Creeper entity = loc.getWorld().spawn(loc, Creeper.class);
        entity.setPowered(true);
        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.setMetadata("CustomCreeper", new FixedMetadataValue(plugin, "customcreeper"));
        entity.getEquipment().setHelmet(generateHelmet());
        entity.getEquipment().setChestplate(generateChestPlate());
        entity.getEquipment().setLeggings(generateLeggings());
        entity.getEquipment().setBoots(generateBoots());
        entity.setMaxHealth(300);
        entity.setHealth(300);
        // CALL EVENT

        temp_data.getEntities().put(entity.getUniqueId(), entity);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));
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
        Random random = new Random();
        BossUtils.notifyPlayers(entity, "Lluvia Explosiva");
        for (int i = 0; i < 5; i++) {

            int x = random.nextInt(10);
            int z = random.nextInt(10);
            if (random.nextBoolean()) {
                x = -x;
            }
            if (random.nextBoolean()) {
                z = -z;
            }
            TNTPrimed tnt = entity.getWorld().spawn(entity.getLocation().add(x, 10, z), TNTPrimed.class);
            tnt.setFuseTicks(100);
        }

    }


    public static void spawnMinions(LivingEntity entity) {
        Random random = new Random();

        int count = 0;

        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= 15) {
                count++;
            }
        }

        if (count == 0) {
            return;
        }
        BossUtils.notifyPlayers(entity, "Minions Suicidas");
        count += random.nextInt(5);
        if (random.nextBoolean()) {
            count = -count;
            if (count <= 0) {
                count = 2;
            }
        }
        if (count > 15) {
            count = 15;
        }
        for (int i = 0; i < count; i++) {
            generateEntity(entity.getLocation());
        }
    }

    public static void useNuke(final LivingEntity entity) {
        BossUtils.notifyPlayers(entity, "Nuke");
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                en.teleport(entity.getLocation());
            }
        }
        TNTPrimed tnt = entity.getWorld().spawn(entity.getLocation(), TNTPrimed.class);
        tnt.setFuseTicks(20);
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() { public void run() {
            for (Entity en : entity.getNearbyEntities(5, 5, 5)) {
                if (en instanceof Player) {
                    ((Player) en).damage(15);
                }
            }

        } }, 20 );
    }

    public static void spawnProtectors(LivingEntity entity) {
        BossUtils.notifyPlayers(entity, "Protectores");
    }

    public static void generateEntity(Location loc) {
        Random random = new Random();
        int x = random.nextInt(10);
        int z = random.nextInt(10);
        if (random.nextBoolean()) {
            x = -x;
        }
        if (random.nextBoolean()) {
            z = -z;
        }
        Chicken chicken = loc.getWorld().spawn(loc.add(x, 0, z), Chicken.class);
        Zombie minion = loc.getWorld().spawn(loc.add(x, 0, z), Zombie.class);
        minion.setBaby(true);
        minion.setCustomName("§a§lSuicida");
        chicken.setPassenger(minion);
        chicken.setAdult();
        minion.setVillager(false);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        minion.getEquipment().setHelmet(new ItemStack(Material.TNT));
        minion.setMetadata("suicide_minion", new FixedMetadataValue(plugin, "suicide_minion"));
        temp_data.getEntities().put(minion.getUniqueId(), minion);
        temp_data.getEntities().put(chicken.getUniqueId(), chicken);
    }

    public static void attack(Entity entity) {
        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= 2) {
                player.damage(30, entity);
            }
        }
    }



}
