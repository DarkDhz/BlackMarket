package dev.darkhorizon.es.virtualbosses.gui.global;

import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.items.BossItems;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BossList extends GUI {

    private final static CommonItems commonItems = CommonItems.getInstance();
    private final static BossItems bossItems = BossItems.getInstance();

    private static BossList INSTANCE = null;

    private BossList() {
        super(36, "§c§l»§r §fBosses de la Mazmorra");
        inv = Bukkit.createInventory(null, super.size, super.title);

        inv.setItem(10, bossItems.getCreeper());
        inv.setItem(12, bossItems.getKing());
        inv.setItem(14, bossItems.getInvocator());
        inv.setItem(16, bossItems.getDamned());
        inv.setItem(22, bossItems.getGolem());

        for (int i = 0; i < 36; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, commonItems.getSeparator(14));
            }
        }
    }

    public static BossList getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (BossList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BossList();
                }
            }
        }
    }

    @Override
    public boolean manageClick(Player target, ItemStack clicked, String title, int slot) {
        return super.title.contains(title);
    }

    @Override
    public void manageClose(Player target, String title) {
        //NOTHING
    }
}
