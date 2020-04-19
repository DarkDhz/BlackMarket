package dev.darkhorizon.es.virtualbosses.listeners;

import dev.darkhorizon.es.virtualbosses.gui.BossList;
import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.gui.ItemListGUI;
import dev.darkhorizon.es.virtualbosses.gui.MainGUI;
import dev.darkhorizon.es.virtualbosses.gui.PerCraft.*;
import dev.darkhorizon.es.virtualbosses.items.CommonItems;
import dev.darkhorizon.es.virtualbosses.items.GiveItems;
import dev.darkhorizon.es.virtualbosses.items.ItemListItems;
import dev.darkhorizon.es.virtualbosses.items.MainItems;
import dev.darkhorizon.es.virtualbosses.utils.Comparators;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryEvents implements Listener {

    private final MainItems main_items = MainItems.getInstance();
    private final ItemListItems list_items = ItemListItems.getInstance();
    private final CommonItems common_items = CommonItems.getInstance();
    private final GiveItems give_items = GiveItems.getInstance();

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {
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
    public void onClick(@NotNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        if (inv.getTitle().contains(MainGUI.title)) {
            event.setCancelled(true);
            if (Comparators.validateItem(item, main_items.getItemList())) {
                GUI gui = new ItemListGUI();
                gui.generateInventory(player);
            }
            return;
        }
        if (inv.getTitle().contains(ItemListGUI.title)) {
            if (this.manageList(player, item)) {
                event.setCancelled(true);
            }
            return;
        }
        if (inv.getTitle().contains(LanzaMeteoros.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getLanzaMeteoros());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
            return;
        }
        if (inv.getTitle().contains(SuperPocion.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getSuperPocion());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(MataDioses.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getMataDioses());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(EspacialHelmet.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getArmaduraEspacialHelmet());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(EspacialChestPlate.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getArmaduraEspacialChestPlate());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(EspacialLeggings.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getArmaduraEspacialLeggings());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(EspacialBoots.title)) {
            if (event.getSlot() > 54 || (event.getSlot() > 19 && event.getSlot() < 26)) {
                return;
            }
            event.setCancelled(true);
            if (Comparators.validateItem(item, common_items.getCraft())) {
                player.getInventory().addItem(give_items.getArmaduraEspacialBoots());
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 10F, 3F);
            }
        }
        if (inv.getTitle().contains(BossList.title)) {
            event.setCancelled(true);
            return;
        }
    }

    private boolean manageList(Player target, ItemStack item) {
        if (Comparators.validateItem(item, list_items.getLanzaMeteoros())) {
            GUI gui = new LanzaMeteoros();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getSuperPocion())) {
            GUI gui = new SuperPocion();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getMataDioses())) {
            GUI gui = new MataDioses();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getArmaduraEspacialHelmet())) {
            GUI gui = new EspacialHelmet();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getArmaduraEspacialChestPlate())) {
            GUI gui = new EspacialChestPlate();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getArmaduraEspacialLeggings())) {
            GUI gui = new EspacialLeggings();
            gui.generateInventory(target);
            return true;
        }
        if (Comparators.validateItem(item, list_items.getArmaduraEspacialBoots())) {
            GUI gui = new EspacialBoots();
            gui.generateInventory(target);
            return true;
        }
        return true;
    }


}
