package dev.darkhorizon.es.black.gui;

import dev.darkhorizon.es.black.items.CommonItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BossList implements GUI{

    private final CommonItems common_items = CommonItems.getInstance();

    public static String title = "§c§l»§r §fBosses de la Mazmorra";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 3*9, title);
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, common_items.getSeparator(14));
            }
        }
        p.openInventory(inv);
    }


}
