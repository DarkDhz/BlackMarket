package dev.darkhorizon.es.black.gui;

import dev.darkhorizon.es.black.items.BossItems;
import dev.darkhorizon.es.black.items.CommonItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BossList implements GUI{

    private final CommonItems common_items = CommonItems.getInstance();
    private final BossItems boss_items = BossItems.getInstance();

    public static String title = "§c§l»§r §fBosses de la Mazmorra";

    public void generateInventory(Player p) {
        Inventory inv = Bukkit.createInventory(p, 36, title);

        inv.setItem(10, boss_items.getCreeper());
        inv.setItem(12, boss_items.getKing());
        inv.setItem(14, boss_items.getInvocator());
        inv.setItem(16, boss_items.getDamned());
        inv.setItem(22, boss_items.getGolem());


        for (int i = 0; i < 36; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, common_items.getSeparator(14));
            }
        }
        p.openInventory(inv);
    }


}
