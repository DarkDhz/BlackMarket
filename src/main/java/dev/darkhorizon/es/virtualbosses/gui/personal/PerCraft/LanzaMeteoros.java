package dev.darkhorizon.es.virtualbosses.gui.personal.PerCraft;

import dev.darkhorizon.es.virtualbosses.gui.PersonalGUI;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import dev.darkhorizon.es.virtualbosses.items.GiveItems;
import dev.darkhorizon.es.virtualbosses.items.PerCraft.LMItems;
import dev.darkhorizon.es.virtualbosses.utils.Comparators;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class LanzaMeteoros extends PersonalGUI {

    private final static CommonItems commonItems = CommonItems.getInstance();
    private final static GiveItems giveItems = GiveItems.getInstance();
    private final static LMItems lm_items = LMItems.getInstance();

    public LanzaMeteoros(Player target) {
        super(45, "§0§l✦ §cLanza Meteoros §0§l✦", target.getUniqueId());

        inv = Bukkit.createInventory(target, 45, title);

        inv.setItem(1, lm_items.getTiroCertero(target));
        inv.setItem(2, lm_items.getEssencia(target));
        inv.setItem(3, lm_items.getKoth(target));
        inv.setItem(4, lm_items.getString(target));
        inv.setItem(5, lm_items.getStick(target));
        inv.setItem(6, commonItems.getEstrellaCaida(8));
        inv.setItem(7, commonItems.getLibroMaestro(5));


        inv.setItem(0, commonItems.getSeparator(15));
        for (int i = 8; i < 19; i++) {
            inv.setItem(i, commonItems.getSeparator(15));
        }
        for (int i = 26; i < 45; i++) {
            inv.setItem(i, commonItems.getSeparator(15));
        }
        inv.setItem(40, commonItems.getCraft());
    }


    @Override
    public boolean manageClick(Player target, ItemStack clicked, String title, int slot) {
        if (!Comparators.compareTitle(super.title, title)) {
            return false;
        }
        if (slot > 54 || (slot > 18 && slot < 26)) {
            return false;
        }
        if (Comparators.validateItem(clicked, commonItems.getCraft())) {
            target.getInventory().addItem(giveItems.getLanzaMeteoros());
            target.closeInventory();
            target.playSound(target.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            return true;
        }
        return true;
    }

    @Override
    public void manageClose(Player target, String title) {
        if (!Comparators.compareTitle(super.title, title)) {
            return;
        }
        for (int i = 19; i < 26; i++) {
            if (inv.getItem(i) != null) {
                target.getInventory().addItem(inv.getItem(i));
            }
        }
        super.remove(target);
    }
}
