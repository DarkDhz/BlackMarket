package dev.darkhorizon.es.black.utils;

import dev.darkhorizon.es.black.data.temp.DPList;
import dev.darkhorizon.es.black.data.temp.DamagePlayer;
import dev.darkhorizon.es.black.data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomBoss;
import dev.darkhorizon.es.black.bosses.entities.*;
import dev.darkhorizon.es.black.config.FileManager;
import dev.darkhorizon.es.black.listeners.boss.BossDeath;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class BossUtils {

    private final static TempData tempData = TempData.getInstance();
    private final static FileManager fm = FileManager.getInstance();
    private static final Main plugin = Main.getPlugin(Main.class);

    public static void notifyPlayers(Entity entity, String skill) {
        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= 50) {
                player.sendMessage("§6§lBOSS §8|| §e" + entity.getCustomName() + " §7ha usado: §d" + skill);
            }
        }
    }

    public static void updateTarget(Creature entity) {
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                entity.setTarget((LivingEntity) en);
                break;
            }
        }
    }

    public static List<Player> getNearPlayers(Entity entity, int dis) {
        List<Player> list = new ArrayList<>();
        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= dis) {
                list.add(player);
            }
        }
        return list;
    }

    public static Location getValidLocation(Location loc, int max_x, int max_z) {
        Random random = new Random();
        int x = random.nextInt(max_x);
        int z = random.nextInt(max_z);
        if (random.nextBoolean()) {
            x = -x;
        }
        if (random.nextBoolean()) {
            z = -z;
        }

        Block block = loc.getWorld().getBlockAt(loc.add(x, 0, z));
        while (block.getType() != Material.AIR) {
            x = random.nextInt(max_x);
            z = random.nextInt(max_z);
            if (random.nextBoolean()) {
                x = -x;
            }
            if (random.nextBoolean()) {
                z = -z;
            }
            block = loc.getWorld().getBlockAt(loc.add(x, 0, z));
        }
        return new Location(loc.getWorld(), loc.getBlockX()+x, loc.getBlockY(), loc.getBlockZ() + z);
    }

    public static int getMinionCount(Entity entity, int max) {
        Random random = new Random();

        int count = 0;

        Location loc = entity.getLocation();
        List<Player> near = BossUtils.getNearPlayers(entity, 15);
        if (near.size() == 0) { return -1; }
        count += near.size();
        count += random.nextInt(5);
        if (count > max) {
            count = max;
        }
        return count;
    }

    //TODO
    public static String getNextSpawn(Date initial) {
        return "UNDONE";
    }

    public static void randomSpawn() {
        tempData.setLastSpawn(Calendar.getInstance().getTime());

        Location loc = new Location(Bukkit.getWorld(fm.getConfig().getString("spawn.world")),
                fm.getConfig().getInt("spawn.loc.x") ,
                fm.getConfig().getInt("spawn.loc.y"),
                fm.getConfig().getInt("spawn.loc.z") );
        Random random = new Random();
        int boss = random.nextInt(4);
        if (boss == 0) {
            CustomBoss<ExplosiveCreeper> mob = new ExplosiveCreeper(loc);
        } else if (boss == 1) {
            CustomBoss<ZombieKing> mob = new ZombieKing(loc);
        } else if (boss == 2) {
            CustomBoss<ArmoredGolem> mob = new ArmoredGolem(loc);
        } else if (boss == 3) {
            CustomBoss<DamnedSkeleton> mob = new DamnedSkeleton(loc);
        }else if (boss == 4) {
            CustomBoss<Invocator> mob = new Invocator(loc);
        }

    }

    private static void sendMessage(LivingEntity entity) {
        for (Entity player : entity.getNearbyEntities(10,10, 10)) {
            if (player instanceof Player) {

                String msg = entity.getCustomName() + " §8» §7Vida restante: §6" + Math.round(entity.getHealth())
                        + "§7/§6" + Math.round(entity.getMaxHealth());
                ActionBar.sendMessage((Player) player, msg);

            }
        }
    }

    public static void manageKingSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                System.out.println("DEBUG 2");
                sendMessage(entity);
                ZombieKing.playSkill(entity);

            }
        }.runTaskTimer(plugin, 0, 3*20);
    }

    public static void manageCreeperSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                ExplosiveCreeper.attack(entity);
                ExplosiveCreeper.playSkill(entity);
            }
        }.runTaskTimer(plugin, 0, 3*20);
    }

    public static void manageGolemSpawn(LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    public static void manageDamnedSpawn(LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    public static void manageInvocatorSpawn(LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    public static void manageTopShow(DPList dp, UUID uuid, String name) {
        Bukkit.broadcastMessage("§4");
        Bukkit.broadcastMessage("§8§m---------§8[§r §e" + name + "§7 ha muerto §8]§r§8§m---------");
        Bukkit.broadcastMessage("§7Top 5 Asesinos:");

        if (dp.getSorted().size() > 5) {
            for (int i = 0; i <= 5; i++) {
                DamagePlayer winner = tempData.getBoss_damagers().get(uuid).getSorted().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        } else {
            for (int i = 0; i < dp.getSorted().size(); i++) {
                DamagePlayer winner = tempData.getBoss_damagers().get(uuid).getSorted().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        }
        tempData.getBoss_damagers().remove(uuid);
    }
    
}
