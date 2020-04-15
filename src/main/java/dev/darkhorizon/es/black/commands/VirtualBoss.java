package dev.darkhorizon.es.black.commands;

import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomBoss;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.bosses.ZombieKing;
import dev.darkhorizon.es.black.gui.BossList;
import dev.darkhorizon.es.black.gui.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class VirtualBoss implements CommandExecutor {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            this.manageCommand((Player) commandSender, strings);
        } else {
            commandSender.sendMessage("¡Este commando solo se puede ejecutar via usuario!");
        }
        return true;
    }

    private void manageCommand(Player launcher, String[] args) {
        if (args.length == 0) {
            launcher.sendMessage("/virtualboss time");
            launcher.sendMessage("/virtualboss list");
            if (launcher.isOp()) {
                launcher.sendMessage("/virtualboss spawn mob");
                launcher.sendMessage("/virtualboss killall");
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("time")) {
                launcher.sendMessage("UNIMPLEMENTED");
                return;
            }
            if (args[0].equalsIgnoreCase("list")) {
                GUI gui = new BossList();
                gui.generateInventory(launcher);
                return;
            }
            if (args[0].equalsIgnoreCase("killall") && launcher.hasPermission("virtual.boss.kill")) {
                int count = 0;
                launcher.sendMessage("" + temp_data.getEntities().keySet());
                for (LivingEntity entity : temp_data.getEntities().values()) {
                    if (!entity.isDead() && entity != null) {
                        launcher.sendMessage("Eliminando " + entity.getUniqueId());
                        entity.damage(100000);
                        count++;
                    } else {
                        launcher.sendMessage("Error con la entidad " + entity.getUniqueId());
                    }
                }
                launcher.sendMessage("§cSe han eliminado " + count + " entidades");
                return;
            }
            if (args[0].equalsIgnoreCase("spawn") && launcher.hasPermission("virtual.boss.spawn")) {
                launcher.sendMessage("Bosses Disponibles");
                launcher.sendMessage("/virtualboss spawn creeper");
                launcher.sendMessage("/virtualboss spawn reyzombie");
                return;
            }

        } else {
            if (args[0].equals("spawn")) {
                if (args[1].equalsIgnoreCase("creeper") && launcher.hasPermission("virtual.boss.spawn")) {
                    CustomBoss<CustomCreeper> boss = new CustomCreeper(launcher.getLocation());
                    launcher.sendMessage("Has creado un Boss Creeper en tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("reyzombie") && launcher.hasPermission("virtual.boss.spawn")) {
                    CustomBoss<ZombieKing> boss = new ZombieKing(launcher.getLocation());
                    launcher.sendMessage("Has creado un Boss Creeper en tu localizacion.");
                    return;
                }
            }
        }
    }
}
