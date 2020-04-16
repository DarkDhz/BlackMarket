package dev.darkhorizon.es.virtualbosses.utils;

import dev.darkhorizon.es.virtualbosses.config.Lang;
import dev.darkhorizon.es.virtualbosses.data.temp.DPList;
import dev.darkhorizon.es.virtualbosses.data.temp.DamagePlayer;
import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.bosses.CustomBoss;
import dev.darkhorizon.es.virtualbosses.bosses.entities.*;
import dev.darkhorizon.es.virtualbosses.config.FileManager;
import dev.darkhorizon.es.virtualbosses.items.drops.KingZombieDrop;
import dev.darkhorizon.es.virtualbosses.listeners.boss.BossDeath;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.text.SimpleDateFormat;
import java.util.*;

public class BossUtils {

    private final static TempData tempData = TempData.getInstance();
    private final static FileManager fm = FileManager.getInstance();
    private final static Main plugin = Main.getPlugin(Main.class);
    private final static Lang lang = Lang.getInstance();
    private final static KingZombieDrop kingZombieDrop = KingZombieDrop.getInstance();

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
    public static String getNextSpawn(Date toCompare) {
        long time = (tempData.getLastSpawn().getTime() + 60*60*1000) - toCompare.getTime();
        return new SimpleDateFormat("hh:mm:ss").format(new Date(time));
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
     * Method to send message when boss spawns
     * @param name Boss Name
     */
    private static void sendSpawnMessage(String name) {
        Bukkit.broadcastMessage("§1");
        Bukkit.broadcastMessage("§2");
        Bukkit.broadcastMessage(lang.info_spawn.replaceAll("%name", name));
        Bukkit.broadcastMessage("§3");
        Bukkit.broadcastMessage("§4");
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageKingSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
        sendSpawnMessage(entity.getCustomName());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
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
        sendSpawnMessage(entity.getCustomName());
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
        sendSpawnMessage(entity.getCustomName());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                //TODO;
            }
        }.runTaskTimer(plugin, 0, 3*20);
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageDamnedSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
        sendSpawnMessage(entity.getCustomName());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                //TODO;
            }
        }.runTaskTimer(plugin, 0, 3*20);
    }

    /**
     * Method to manage Boss Spawn - SKills, Select target, Death event...
     * @param entity The Boss
     */
    public static void manageInvocatorSpawn(final LivingEntity entity) {
        tempData.getBoss_damagers().put(entity.getUniqueId(), new DPList());
        sendSpawnMessage(entity.getCustomName());
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    Bukkit.getPluginManager().callEvent(new BossDeath(entity));
                    cancel();
                    return;
                }
                sendMessage(entity);
                //TODO;
            }
        }.runTaskTimer(plugin, 0, 3*20);

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
        List<DamagePlayer> players = dp.getSorted();
        if (players.size() > 5) {
            for (int i = 0; i <= 5; i++) {
                DamagePlayer winner = players.get(i);
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(winner.getUuid()).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        } else {
            for (int i = 0; i < dp.getSorted().size(); i++) {
                DamagePlayer winner = players.get(i);
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(winner.getUuid()).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        }
        Bukkit.broadcastMessage("§3");
        int count = 0;
        for (DamagePlayer player : players) {
            count++;
            Player target = Bukkit.getPlayer(player.getUuid());
            target.sendMessage(lang.info_final_rank
                    .replaceAll("%top", "" + count)
                    .replaceAll("%damage", "" + Math.round(player.getDamage())));
        }
        Bukkit.broadcastMessage("§2");
        tempData.getBoss_damagers().remove(uuid);
        manageLoot(name, players);

    }

    /**
     * Method to manage all loots
     * @param name Boss name
     * @param dp Sorted list of players
     */
    private static void manageLoot(String name, List<DamagePlayer> dp) {
        if (name.equals(ZombieKing.name)) {
            manageKingLoot(dp);
        } else if (name.equals(ExplosiveCreeper.name)){
            manageCreeperLoot(dp);
        } else if (name.equals(Invocator.name)) {
            manageInvocatorLoot(dp);
        } else if (name.equals(ArmoredGolem.name)) {
            manageGolemLoot(dp);
        } else if (name.equals(DamnedSkeleton.name)) {
            manageDamnedLoot(dp);
        }
    }

    /**
     * Method to manage Boss drops
     * @param dp
     */
    private static void manageKingLoot(List<DamagePlayer> dp) {
        if (dp.get(0) != null) {
            Player winner = Bukkit.getPlayer(dp.get(0).getUuid());
            winner.getInventory().addItem(kingZombieDrop.getBossHead());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give to " + winner.getName() + " rare 1");
        }
        if (dp.get(1) != null) {
            Player winner = Bukkit.getPlayer(dp.get(0).getUuid());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give to " + winner.getName() + " rare 1");

        }
        if (dp.get(2) != null) {
            Player winner = Bukkit.getPlayer(dp.get(0).getUuid());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "crate give to " + winner.getName() + " rare 1");

        }
    }

    private static void manageCreeperLoot(List<DamagePlayer> dp) {
        //TODO
    }

    private static void manageInvocatorLoot(List<DamagePlayer> dp) {
        //TODO
    }

    private static void manageDamnedLoot(List<DamagePlayer> dp) {
        //TODO
    }

    private static void manageGolemLoot(List<DamagePlayer> dp) {
        //TODO
    }

    /**
     * Method to manage boss pvp event
     * @param event The event instance
     * @return If event is cancelled or not
     */
    public static boolean manageKingDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return false;
        }
        LivingEntity target = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Arrow && target.hasMetadata("ZombieKing")) {
            Arrow arr = (Arrow) event.getDamager();
            if (!(arr.getShooter() instanceof Player)) {
                return false;
            }
            Player launcher = (Player) arr.getShooter();
            tempData.getBoss_damagers().get(target.getUniqueId()).addDamage(launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("ZombieKing") && damager instanceof Player) {
            tempData.getBoss_damagers().get(target.getUniqueId()).addDamage(damager.getUniqueId(), event.getFinalDamage());
            return false;
        }
        return false;
    }

    /**
     * Method to manage boss pvp event
     * @param event The event instance
     * @return If event is cancelled or not
     */
    public static boolean manageCreeperDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof LivingEntity)) {
            return false;
        }
        LivingEntity target = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Arrow && target.hasMetadata("CustomCreeper")) {
            Arrow arr = (Arrow) event.getDamager();
            if (!(arr.getShooter() instanceof Player)) {
                return false;
            }
            Player launcher = (Player) arr.getShooter();
            tempData.getBoss_damagers().get(target.getUniqueId()).addDamage(launcher.getUniqueId(), event.getFinalDamage());
            return false;
        } else {
            if (!(event.getDamager() instanceof LivingEntity)) {
                return false;
            }
        }
        LivingEntity damager = (LivingEntity) event.getDamager();

        if (target.hasMetadata("CustomCreeper") && damager instanceof Player) {
            try {
                tempData.getBoss_damagers().get(target.getUniqueId()).addDamage(damager.getUniqueId(), event.getFinalDamage());
                org.bukkit.util.Vector unitVector = new org.bukkit.util.Vector(damager.getLocation().getDirection().getX() * -3, 2,damager.getLocation().getDirection().getZ() * -3);
                damager.setVelocity(unitVector);
            } catch (Exception ignored) { }
            return false;
        }

        if (damager.getType() == EntityType.PRIMED_TNT && target.getType() == EntityType.CREEPER && target.hasMetadata("CustomCreeper")) {
            return true;
        }
        if (damager.hasMetadata("suicide_minion") && target instanceof Player) {
            damager.remove();

            damager.getWorld().playEffect(damager.getLocation(), Effect.EXPLOSION, null);
            damager.getWorld().playSound(damager.getLocation(), Sound.EXPLODE, 1, 0);
            target.setVelocity((target.getLocation().getDirection().multiply(-1).add(new Vector(0, 0.5, 0))));
            Random random = new Random();

            target.damage(random.nextInt(15) + 10);

            return false;
        }
        return false;
    }

}
