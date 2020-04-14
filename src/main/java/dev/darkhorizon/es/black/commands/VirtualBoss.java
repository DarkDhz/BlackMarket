package dev.darkhorizon.es.black.commands;

import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomBoss;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.gui.GUI;
import dev.darkhorizon.es.black.gui.MainGUI;
import dev.darkhorizon.es.black.items.GiveItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VirtualBoss implements CommandExecutor {

    private static final Main plugin = Main.getPlugin(Main.class);

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
            launcher.sendMessage("USAGE");
        } else if (args.length == 1) {
            if (args[0].equals("creeper") && launcher.hasPermission("virtual.boss.spawn")) {
                CustomBoss boss = new CustomCreeper(launcher.getLocation());
                launcher.sendMessage("Has creado un Boss Creeper en tu localizacion.");
            }
        }
    }
}
