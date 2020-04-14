package dev.darkhorizon.es.black.gui;

import dev.darkhorizon.es.black.items.ItemListItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ItemListGUI implements GUI{

    private final ItemListItems items = ItemListItems.getInstance();

    public static String title = "§c§l»§r §fItems Disponibles";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 3*9, title);

        inv.setItem(10, items.getLanzaMeteoros());

        p.openInventory(inv);
    }
}
