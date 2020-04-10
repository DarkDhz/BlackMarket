package dev.darkhorizon.es.black.gui;

import dev.darkhorizon.es.black.items.MainItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainGUI implements GUI{

    private MainItems items = MainItems.getInstance();

    public static String title = "§c§l»§r §0Mercado Espacial";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 3*9, title);

        inv.setItem(10, items.getHelp());
        inv.setItem(16, items.getItemList());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, items.getSeparator());
            }
        }
        p.openInventory(inv);
    }

}
