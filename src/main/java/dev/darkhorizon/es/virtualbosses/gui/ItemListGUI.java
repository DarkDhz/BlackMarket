package dev.darkhorizon.es.virtualbosses.gui;

import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import dev.darkhorizon.es.virtualbosses.items.ItemListItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ItemListGUI implements GUI{

    private final static ItemListItems items = ItemListItems.getInstance();
    private final static CommonItems commonItems = CommonItems.getInstance();

    public static String title = "§c§l»§r §fIntercambios Disponibles";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 27, title);

        inv.setItem(10, items.getMataDioses());
        inv.setItem(11, items.getLanzaMeteoros());
        inv.setItem(12, items.getArmaduraEspacialHelmet());
        inv.setItem(13, items.getArmaduraEspacialChestPlate());
        inv.setItem(14, items.getArmaduraEspacialLeggings());
        inv.setItem(15, items.getArmaduraEspacialBoots());
        inv.setItem(16, items.getSuperPocion());

        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, commonItems.getSeparator(15));
            }
        }

        p.openInventory(inv);
    }
}
