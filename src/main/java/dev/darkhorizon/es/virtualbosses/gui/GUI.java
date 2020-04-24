package dev.darkhorizon.es.virtualbosses.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class GUI {

    protected Inventory inv = null;
    protected int size = 0;
    protected String title = null;

    protected GUI(int size, String title) {
        this.size = size;
        this.title = title;
    }

    public Inventory getInventory() {
        return inv;
    }

    public ItemStack[] getItems() {
        return inv.getContents();
    }

    public ItemStack getItemAt(int slot) {
        return inv.getItem(slot);
    }

    public int getSize() {
        return this.size;
    }

    public String getTitle() {
        return this.title;
    }

    public void open(Player target) {
        target.openInventory(inv);
    }

    public abstract boolean manageClick(Player target, ItemStack clicked, String title, int slot);

    public abstract void manageClose(Player target, String title);



}

