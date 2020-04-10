package dev.darkhorizon.es.black.events;

import dev.darkhorizon.es.black.gui.GUI;
import dev.darkhorizon.es.black.gui.ItemListGUI;
import dev.darkhorizon.es.black.gui.MainGUI;
import dev.darkhorizon.es.black.items.MainItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {

    private MainItems mitems = MainItems.getInstance();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        if (inv.getTitle().contains(MainGUI.title)) {
            event.setCancelled(true);
            if (validateItem(item, mitems.getItemList())) {
                GUI gui = new ItemListGUI();
                gui.generateInventory(player);
            }
        }
    }

    private boolean validateItem(ItemStack item, ItemStack toCompare) {
        if (item.getType() != toCompare.getType()) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        return item.getItemMeta().getDisplayName().equals(toCompare.getItemMeta().getDisplayName());
    }
}
