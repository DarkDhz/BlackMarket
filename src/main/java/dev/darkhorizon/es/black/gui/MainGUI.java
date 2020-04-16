package dev.darkhorizon.es.black.gui;

import dev.darkhorizon.es.black.items.CommonItems;
import dev.darkhorizon.es.black.items.MainItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MainGUI implements GUI{

    private final MainItems main_items = MainItems.getInstance();
    private final CommonItems common_items = CommonItems.getInstance();

    public static String title = "§c§l»§r §0Mercado Espacial";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 27, title);

        inv.setItem(10, main_items.getHelp());
        inv.setItem(16, main_items.getItemList());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, common_items.getSeparator(15));
            }
        }
        p.openInventory(inv);
    }

}
