package dev.darkhorizon.es.black.events;

import dev.darkhorizon.es.black.gui.BossList;
import dev.darkhorizon.es.black.gui.GUI;
import dev.darkhorizon.es.black.gui.ItemListGUI;
import dev.darkhorizon.es.black.gui.MainGUI;
import dev.darkhorizon.es.black.gui.PerCraft.LanzaMeteoros;
import dev.darkhorizon.es.black.items.CommonItems;
import dev.darkhorizon.es.black.items.GiveItems;
import dev.darkhorizon.es.black.items.ItemListItems;
import dev.darkhorizon.es.black.items.MainItems;
import dev.darkhorizon.es.black.utils.Comparations;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEvents implements Listener {

    private final MainItems main_items = MainItems.getInstance();
    private final ItemListItems list_items = ItemListItems.getInstance();
    private final CommonItems common_items = CommonItems.getInstance();
    private final GiveItems give_items = GiveItems.getInstance();

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inv = event.getInventory();
        if (inv.getTitle().contains(LanzaMeteoros.title)) {
            for (int i = 20; i < 26; i++) {
                if (inv.getItem(i) != null) {
                    player.getInventory().addItem(inv.getItem(i));
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        if (inv.getTitle().contains(MainGUI.title)) {
            event.setCancelled(true);
            if (Comparations.validateItem(item, main_items.getItemList())) {
                GUI gui = new ItemListGUI();
                gui.generateInventory(player);
            }
            return;
        }
        if (inv.getTitle().contains(ItemListGUI.title)) {
            event.setCancelled(true);
            if (Comparations.validateItem(item, list_items.getLanzaMeteoros())) {
                GUI gui = new LanzaMeteoros();
                gui.generateInventory(player);
            }
            return;
        }
        if (inv.getTitle().contains(LanzaMeteoros.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            if (Comparations.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getLanzaMeteoros());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
            event.setCancelled(true);
            return;
        }
        if (inv.getTitle().contains(BossList.title)) {
            event.setCancelled(true);
            return;
        }
    }


}
