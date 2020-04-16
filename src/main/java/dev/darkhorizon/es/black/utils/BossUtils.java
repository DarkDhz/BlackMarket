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

    /**
     * Method to notify players near to the boss
     * @param entity The Boss
     * @param skill The Skill Name
     */
    public static void notifyPlayers(Entity entity, String skill) {
        Location loc = entity.getLocation();
        for (Player player : entity.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(loc);
            if (distance <= 50) {
                player.sendMessage("§6§lBOSS §8|| §e" + entity.getCustomName() + " §7ha usado: §d" + skill);
            }
        }
    }

    /**
     * Method to update the entity target
     * @param entity The Boss
     */
    public static void updateTarget(Creature entity) {
        for (Entity en : entity.getNearbyEntities(20, 5, 20)) {
            if (en instanceof Player) {
                entity.setTarget((LivingEntity) en);
                break;
            }
        }
    }

    /**
     * Method to get all nearly boss players
     * @param entity The Boss
     * @param dis Distance between boss and player
     * @return List of all players that accomplish the requirements
     */
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

    /**
     * Method to get a valid random location to spawn entity
     * @param loc Initial location
     * @param max_x Max X distance from Boss
     * @param max_z Max Z distance from BoSS
     * @return Valid Location
     */
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

    /**
     * Method to get Random entity number
     * @param entity The Boss
     * @param max Max number of entities
     * @return Number of entities to spawn
     */
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

    /**
     * Method to select the next spawned boss
     */
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

    /**
     * Method to send to near players the ActionBar with boss info
     * @param entity The Boss
     */
    private static void sendMessage(LivingEntity entity) {
        List<Player> near = getNearPlayers(entity, 50);
        if (near.isEmpty()) {
            return;
        }
        String msg = entity.getCustomName() + " §8» §7Vida restante: §6" + Math.round(entity.getHealth())
                + "§7/§6" + Math.round(entity.getMaxHealth());
        for (Player player : near) {
            ActionBar.sendMessage((Player) player, msg);
        }
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
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

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
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

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageGolemSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageDamnedSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageInvocatorSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
    }

    /**
     * Method to show the Top Damagers in global chat
     * @param dp List of players that damaged the boss
     * @param uuid Boss UUID
     * @param name Boss name
     */
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
