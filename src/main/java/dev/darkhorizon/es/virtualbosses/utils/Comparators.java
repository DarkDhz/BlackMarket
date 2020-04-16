package dev.darkhorizon.es.virtualbosses.utils;

import org.bukkit.inventory.ItemStack;

public class Comparators {

    /**
     * Method to compare to verify that 2 items are the same
     * @param item Main Item
     * @param toCompare Item to compare
     * @return true if equals, else otherwise
     */
    public static boolean validateItem(ItemStack item, ItemStack toCompare) {
        if (item.getType() != toCompare.getType()) {
            return false;
        }
        if (!item.hasItemMeta()) {
            return false;
        }
        return item.getItemMeta().getDisplayName().equals(toCompare.getItemMeta().getDisplayName());
    }


    /**
     * Method to compare to verify that 2 items are the same
     * @param item Main Item
     * @param toCompare Item to compare
     * @param quantity Quantity of item
     * @return true if equals, else otherwise
     */
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
