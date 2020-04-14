package dev.darkhorizon.es.black.bosses;

import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class CustomCreeper implements CustomBoss{

    private static final Main plugin = Main.getPlugin(Main.class);


    public CustomCreeper(Location loc) {
        if (!loc.getWorld().isChunkLoaded(loc.getChunk())) {
            loc.getWorld().loadChunk(loc.getChunk());
        }
        final Creeper entity = loc.getWorld().spawn(loc, Creeper.class);
        entity.setPowered(true);
        entity.setCustomName("§a§lEl Explosiones");
        entity.setCustomNameVisible(true);
        entity.setMetadata("CustomCreeper", new FixedMetadataValue(plugin, "customcreeper"));
        entity.getEquipment().setHelmet(generateHelmet());
        entity.getEquipment().setChestplate(generateChestPlate());
        entity.getEquipment().setLeggings(generateLeggins());
        entity.getEquipment().setBoots(generateBoots());
        entity.setMaxHealth(300);
        entity.setHealth(300);
        // CALL EVENT

        TempData.entities.add(entity);
        Bukkit.getPluginManager().callEvent(new BossSpawn(entity));
        //run(entity);
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

    private ItemStack generateLeggins() {
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
        notifyPlayers(entity, "Lluvia Explosiva");
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

    public static void updateTarget(Creeper entity) {
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                entity.setTarget((LivingEntity) en);
                break;
            }
        }
    }

    public static void spawnMinions(LivingEntity entity) {
        Random random = new Random();
        notifyPlayers(entity, "Minions Suicidas");
        int count = 0;
        for (Entity player : entity.getNearbyEntities(10,10, 10)) {
            if (player instanceof Player) {
                count++;
            }
        }
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

    public static void useNuke(LivingEntity entity) {

        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {

            }
        }
    }

    private static void notifyPlayers(Entity entity, String skill) {
        for (Entity player : entity.getNearbyEntities(20,10, 20)) {
            if (player instanceof Player) {
                ((Player) player).sendMessage("§6§lBOSS §8|| §e" + entity.getCustomName() + " §7ha usado: §d" + skill);
            }
        }
    }

    private static void generateEntity(Location loc) {
        Random random = new Random();
        int x = random.nextInt(10);
        int z = random.nextInt(10);
        if (random.nextBoolean()) {
            x = -x;
        }
        if (random.nextBoolean()) {
            z = -z;
        }
        Zombie minion = loc.getWorld().spawn(loc.add(x, 0, z), Zombie.class);
        minion.setBaby(true);
        minion.setCustomName("§a§lSuicida");
        minion.setHealth(10);
        minion.setVillager(false);
        minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3), true);
        minion.getEquipment().setHelmet(new ItemStack(Material.TNT));
        minion.setMetadata("suicide_minion", new FixedMetadataValue(plugin, "suicide_minion"));
        TempData.entities.add(minion);
    }



}
