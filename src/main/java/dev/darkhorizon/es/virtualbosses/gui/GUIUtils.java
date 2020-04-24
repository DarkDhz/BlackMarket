package dev.darkhorizon.es.virtualbosses.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GUIUtils {

    public static HashMap<UUID, PersonalGUI> viewers = new HashMap<>();

    public static boolean isViewer(Player player) {
        return viewers.containsKey(player.getUniqueId());
    }

    public static void removeViewer(Player player) {
        viewers.remove(player.getUniqueId());
    }

    public static void removeViewer(UUID uuid) {
        viewers.remove(uuid);
    }

    public static void addViewer(Player player, PersonalGUI gui) {
        if (!isViewer(player)) {
            viewers.put(player.getUniqueId(), gui);
        } else {
            viewers.replace(player.getUniqueId(), gui);
        }
    }

    public static PersonalGUI getGUI(Player player) {
        return viewers.get(player.getUniqueId());
    }



}
