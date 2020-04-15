package dev.darkhorizon.es.black.commands;

import dev.darkhorizon.es.black.Data.temp.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.entities.CustomBoss;
import dev.darkhorizon.es.black.bosses.entities.CustomCreeper;
import dev.darkhorizon.es.black.bosses.entities.ZombieKing;
import dev.darkhorizon.es.black.config.Lang;
import dev.darkhorizon.es.black.config.Perms;
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
    private static final Lang lang = Lang.getInstance();
    private static final Perms perms = Perms.getInstance();


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
            if (args[0].equalsIgnoreCase("killall") && launcher.hasPermission(perms.vb_kill)) {
                int count = 0;
                while (!temp_data.getEntities().isEmpty() && count < 100) {
                    try {
                        for (LivingEntity e : temp_data.getEntities().values()) {
                            temp_data.getEntities().remove(e.getUniqueId());
                            e.remove();
                            count++;
                        }
                    } catch (Exception ignored) {
                    }

                }
                launcher.sendMessage("§cSe han eliminado " + count + " entidades");
                return;
            }
            if (args[0].equalsIgnoreCase("spawn") && launcher.hasPermission(perms.vb_spawn)) {
                launcher.sendMessage("Bosses Disponibles");
                launcher.sendMessage("/virtualboss spawn creeper");
                launcher.sendMessage("/virtualboss spawn reyzombie");
                return;
            }

        } else {
            if (args[0].equals("spawn")) {
                if (args[1].equalsIgnoreCase("creeper") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<CustomCreeper> boss = new CustomCreeper(launcher.getLocation());
                    launcher.sendMessage("Has creado un Boss Creeper en tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("reyzombie") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<ZombieKing> boss = new ZombieKing(launcher.getLocation());
                    launcher.sendMessage("Has creado un Boss Creeper en tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("test") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomCreeper.generateEntity(launcher.getLocation());
                    CustomCreeper.generateEntity(launcher.getLocation());
                    CustomCreeper.generateEntity(launcher.getLocation());
                    CustomCreeper.generateEntity(launcher.getLocation());
                    CustomCreeper.generateEntity(launcher.getLocation());
                    launcher.sendMessage("Has realizado el test");
                    return;
                }
            }
        }
    }
}
