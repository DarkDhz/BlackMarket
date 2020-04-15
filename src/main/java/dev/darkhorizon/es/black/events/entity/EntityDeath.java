package dev.darkhorizon.es.black.events.entity;

import dev.darkhorizon.es.black.Data.temp.DPList;
import dev.darkhorizon.es.black.Data.temp.DamagePlayer;
import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.entities.CustomCreeper;
import dev.darkhorizon.es.black.bosses.entities.ZombieKing;
import dev.darkhorizon.es.black.events.boss.BossDeath;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;

public class EntityDeath implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        //CREEPER
        if (entity.hasMetadata("CustomCreeper") || entity.hasMetadata("ZombieKing")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            return;
        }
        //CREEPER MINION
        if (entity.hasMetadata("suicide_minion")) {
            temp_data.getEntities().remove(entity.getUniqueId());
            event.getDrops().clear();
            if (entity.getKiller() != null && entity instanceof Player) {
                Random random = new Random();
                int i = random.nextInt(100);
                if (i <= 5){
                    event.getDrops().add(new ItemStack(Material.TNT));
                }
            }
            return;
        }
    }

    @EventHandler
    public void onBossDeath(BossDeath event) {
        DPList dp = temp_data.getBoss_damagers().get(event.getEntity().getUniqueId());
        if (dp.getList().size() == 0) {
            return;
        }
        String name = "unset";
        if (event.getEntity().hasMetadata("CustomCreeper")) {
            name = CustomCreeper.name;
        }
        if (event.getEntity().hasMetadata("ZombieKing")) {
            name = ZombieKing.name;
        }
        Bukkit.broadcastMessage("§4");
        Bukkit.broadcastMessage("§8§m---------§8[§r §e" + name + "§7 ha muerto §8]§r§8§m---------");
        Bukkit.broadcastMessage("§7Top 5 Asesinos:");

        if (dp.getSorted().size() > 5) {
            for (int i = 0; i <= 5; i++) {
                DamagePlayer winner = temp_data.getBoss_damagers().get(event.getEntity().getUniqueId()).getSorted().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        } else {
            for (int i = 0; i < dp.getSorted().size(); i++) {
                DamagePlayer winner = temp_data.getBoss_damagers().get(event.getEntity().getUniqueId()).getSorted().get(i);
                UUID id = UUID.fromString(winner.getUuid());
                Bukkit.broadcastMessage("   §6§lTOP " + (i+1) + ". §e" + Bukkit.getPlayer(id).getName() + " §7(§d" +
                        Math.round(winner.getDamage()) + "§7)");
            }
        }
        temp_data.getBoss_damagers().remove(event.getEntity().getUniqueId());
    }

}
