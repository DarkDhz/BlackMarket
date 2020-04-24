package dev.darkhorizon.es.virtualbosses.listeners;

import dev.darkhorizon.es.virtualbosses.gui.PersonalGUI;
import dev.darkhorizon.es.virtualbosses.gui.global.BossList;
import dev.darkhorizon.es.virtualbosses.gui.global.ItemListGUI;
import dev.darkhorizon.es.virtualbosses.gui.global.MainGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryEvents implements Listener {

    @EventHandler
    public void onClose(@NotNull InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        String title = event.getInventory().getTitle();
        if (title.contains("container")) {
            return;
        }
        if (PersonalGUI.isViewer(player)) {
            PersonalGUI.getGUI(player).manageClose(player, title);
            return;
        }
    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        String title = event.getClickedInventory().getTitle();
        if (title.contains("container")) {
            return;
        }
        int slot = event.getSlot();

        if (BossList.getInstance().manageClick(player, item, title, slot)) {
            event.setCancelled(true);
            return;
        }

        if (ItemListGUI.getInstance().manageClick(player, item, title, slot)) {
            event.setCancelled(true);
            return;
        }
        if (MainGUI.getInstance().manageClick(player, item, title, slot)) {
            event.setCancelled(true);
            return;
        }

        if (PersonalGUI.isViewer(player)) {
            if (PersonalGUI.getGUI(player).manageClick(player, item, title, slot)) {
                event.setCancelled(true);
                return;
            }
        }
        /*
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
        }*/
        
    }



}
