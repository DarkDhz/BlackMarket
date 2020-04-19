package dev.darkhorizon.es.virtualbosses.commands;

import dev.darkhorizon.es.virtualbosses.config.Lang;
import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.gui.MainGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import sun.launcher.resources.launcher;

public class BlackMarket implements CommandExecutor {

    private static Lang lang = Lang.getInstance();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            return true;
        } else {
            manageCommand(commandSender, strings);
        }
        return true;
    }

    private void manageCommand(CommandSender launcher, String[] args) {
        if (args.length == 0) {
            launcher.sendMessage(lang.global_prefix + " Indica el jugador al que quieres abrir el menu");
            return;
        }
        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                return;
            }
            GUI gui = new MainGUI();
            gui.generateInventory(target);
        }
    }
}
