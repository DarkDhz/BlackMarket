package dev.darkhorizon.es.black.utils;

import org.bukkit.inventory.ItemStack;

public class Comparations {

    public static boolean validateItem(ItemStack item, ItemStack toCompare) {
        if (item.getType() != toCompare.getType()) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        return item.getItemMeta().getDisplayName().equals(toCompare.getItemMeta().getDisplayName());
    }

    public static boolean validateItem(ItemStack item, ItemStack toCompare, int quantity) {
        if (item.getType() != toCompare.getType()) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        if (!item.getItemMeta().getDisplayName().equals(toCompare.getItemMeta().getDisplayName())) {
            return false;
        }
        return item.getAmount() >= quantity;
    }
}
