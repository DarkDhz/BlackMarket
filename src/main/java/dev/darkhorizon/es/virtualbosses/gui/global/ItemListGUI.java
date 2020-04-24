package dev.darkhorizon.es.virtualbosses.gui.global;

import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.gui.PersonalGUI;
import dev.darkhorizon.es.virtualbosses.gui.personal.PerCraft.*;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import dev.darkhorizon.es.virtualbosses.items.ItemListItems;
import dev.darkhorizon.es.virtualbosses.utils.Comparators;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemListGUI extends GUI {

    private final static ItemListItems items = ItemListItems.getInstance();
    private final static CommonItems commonItems = CommonItems.getInstance();

    private static ItemListGUI INSTANCE = null;


    private ItemListGUI() {
        super(27, "§c§l»§r §fIntercambios Disponibles");
        inv = Bukkit.createInventory(null, super.size, super.title);

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
    }

    public static ItemListGUI getInstance() {
        if (INSTANCE == null) {createInstance();}
        return INSTANCE;
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (ItemListGUI.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemListGUI();
                }
            }
        }
    }

    @Override
    public boolean manageClick(Player target, ItemStack clicked, String title, int slot) {
        if (!Comparators.compareTitle(super.title, title)) {
            return false;
        }
        if (Comparators.validateItem(clicked, items.getLanzaMeteoros())) {
            PersonalGUI gui = new LanzaMeteoros(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getSuperPocion())) {
            PersonalGUI gui = new SuperPocion(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getMataDioses())) {
            PersonalGUI gui = new MataDioses(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getArmaduraEspacialHelmet())) {
            PersonalGUI gui = new EspacialHelmet(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getArmaduraEspacialChestPlate())) {
            PersonalGUI gui = new EspacialChestPlate(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getArmaduraEspacialLeggings())) {
            PersonalGUI gui = new EspacialLeggings(target);
            gui.open(target);
            return true;
        }
        if (Comparators.validateItem(clicked, items.getArmaduraEspacialBoots())) {
            PersonalGUI gui = new EspacialBoots(target);
            gui.open(target);
            return true;
        }
        return true;
    }

    @Override
    public void manageClose(Player target, String title) {

    }
}
