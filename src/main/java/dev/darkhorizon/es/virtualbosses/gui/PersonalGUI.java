package dev.darkhorizon.es.virtualbosses.gui;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public abstract class PersonalGUI extends GUI {

    protected final UUID playerUUID;

    public static HashMap<UUID, PersonalGUI> viewers = new HashMap<>();

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
        if (!isViewer(target)) {
            viewers.put(target.getUniqueId(), this);
        } else {
            viewers.replace(target.getUniqueId(), this);
        }
        target.openInventory(super.inv);
    }

    public void remove() {
        viewers.remove(playerUUID);
    }

    protected void remove(Player target) {
        viewers.remove(target.getUniqueId());
    }

    public static PersonalGUI getGUI(Player player) {
        return viewers.get(player.getUniqueId());
    }

    public static boolean isViewer(Player player) { return viewers.containsKey(player.getUniqueId());}



}
