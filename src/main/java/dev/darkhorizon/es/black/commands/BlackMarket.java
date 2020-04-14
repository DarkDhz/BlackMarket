package dev.darkhorizon.es.black.commands;

import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.Main;
import dev.darkhorizon.es.black.bosses.CustomCreeper;
import dev.darkhorizon.es.black.gui.GUI;
import dev.darkhorizon.es.black.gui.MainGUI;
import dev.darkhorizon.es.black.items.GiveItems;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;

public class BlackMarket implements CommandExecutor {

    private static final Main plugin = Main.getPlugin(Main.class);
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
        }
    }
}
