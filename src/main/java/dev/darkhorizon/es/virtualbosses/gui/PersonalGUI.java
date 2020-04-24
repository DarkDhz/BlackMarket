package dev.darkhorizon.es.virtualbosses.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public abstract class PersonalGUI extends GUI {

    protected final UUID playerUUID;


    protected PersonalGUI(int size, String title, @NotNull Player target) {
        super(size, title);
        this.playerUUID = target.getUniqueId();
    }

    protected PersonalGUI(int size, String title, UUID uuid) {
        super(size, title);
        this.playerUUID = uuid;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    @Override
    public void open(Player target) {
        GUIUtils.addViewer(target, this);
        target.openInventory(super.inv);
    }

    public void remove() {
        GUIUtils.removeViewer(playerUUID);
    }

    protected void remove(Player target) {
        GUIUtils.removeViewer(target);
    }

    public static PersonalGUI getGUI(Player player) {
        return GUIUtils.getGUI(player);
    }

    public static boolean isViewer(Player player) { return GUIUtils.isViewer(player);}

}
