package dev.darkhorizon.es.virtualbosses.gui.PerCraft;

import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EspacialChestPlate implements GUI {

    private final CommonItems citems = CommonItems.getInstance();

    public static String title = "§0§l✦ §6Peto Espacial §0§l✦";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 45, title);

        inv.setItem(0, citems.getSeparator(15));
        for (int i = 8; i < 19; i++) {
            inv.setItem(i, citems.getSeparator(15));
        }
        for (int i = 26; i < 45; i++) {
            inv.setItem(i, citems.getSeparator(15));
        }

        inv.setItem(40, citems.getCraft());

        p.openInventory(inv);
    }
}
