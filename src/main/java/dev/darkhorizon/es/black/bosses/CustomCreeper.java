package dev.darkhorizon.es.black.bosses;

import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class CustomCreeper implements CustomBoss{

    private static final Main plugin = Main.getPlugin(Main.class);

    private int id = 0;
    private double health = 200;

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

        // CALL EVENT

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

    public static void spawnRandomTNT(Entity entity) {
        Random random = new Random();
        for (int i = 0; i < 1; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            if (random.nextBoolean()) {
                x = -x;
            }
            if (random.nextBoolean()) {
                y = -y;
            }
            TNTPrimed tnt = entity.getWorld().spawn(entity.getLocation().add(x, y, 10), TNTPrimed.class);
            tnt.setFuseTicks(100);
        }
    }

    public static void pushPlayers(Entity entity) {
        for (Entity player : entity.getNearbyEntities(10,10, 10)) {
            if (player instanceof Player) {
                Vector unitVector = new Vector(player.getLocation().getDirection().getX(), player.getLocation().getDirection().getY(), player.getLocation().getDirection().getZ() + 10);
                player.setVelocity(unitVector);
            }
        }


    }

}
