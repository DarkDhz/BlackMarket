package dev.darkhorizon.es.virtualbosses.commands;

import dev.darkhorizon.es.virtualbosses.config.Lang;
import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.gui.MainGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class BlackMarket implements CommandExecutor {

    private static Lang lang = Lang.getInstance();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            this.manageCommand((Player) commandSender, strings);
        } else {
            commandSender.sendMessage(lang.global_only_players);
        }
        return true;
    }

    private void manageCommand(Player launcher, String[] args) {
        GUI gui = new MainGUI();
        gui.generateInventory(launcher);
    }
}
