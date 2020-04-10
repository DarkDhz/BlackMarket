package dev.darkhorizon.es.black.commands;

import dev.darkhorizon.es.black.gui.GUI;
import dev.darkhorizon.es.black.gui.MainGUI;
import dev.darkhorizon.es.black.items.GiveItems;
import dev.darkhorizon.es.black.items.ItemListItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlackMarket implements CommandExecutor {

    private GiveItems items = GiveItems.getInstance();

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
            GUI gui = new MainGUI();
            gui.generateInventory(launcher);
        } else {
            launcher.getInventory().addItem(items.getLanzaMeteoros());
        }
    }
}
