package dev.darkhorizon.es.virtualbosses.gui.global;

import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import dev.darkhorizon.es.virtualbosses.items.MainItems;
import dev.darkhorizon.es.virtualbosses.utils.Comparators;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainGUI extends GUI {

    private final static MainItems mainItems = MainItems.getInstance();
    private final static CommonItems commonItems = CommonItems.getInstance();

    private static MainGUI INSTANCE = null;

    public static MainGUI getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (MainGUI.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainGUI();
                }
            }
        }
    }

    private MainGUI() {
        super(27, "§c§l»§r §0Mercado Espacial");
        inv = Bukkit.createInventory(null, super.size, super.title);

        inv.setItem(10, mainItems.getHelp());
        inv.setItem(16, mainItems.getItemList());
        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, commonItems.getSeparator(15));
            }
        }
    }


    @Override
    public boolean manageClick(Player target, ItemStack clicked, String title, int slot) {
        if (!Comparators.compareTitle(super.title, title)) {
            return false;
        }
        if (Comparators.validateItem(clicked, mainItems.getItemList())) {
            GUI gui = ItemListGUI.getInstance();
            gui.open(target);
            return true;
        }
        return true;
    }

    @Override
    public void manageClose(Player target, String title) {

    }
}
