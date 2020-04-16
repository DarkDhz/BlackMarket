package dev.darkhorizon.es.virtualbosses.listeners;

import dev.darkhorizon.es.virtualbosses.Main;
import org.bukkit.event.Listener;

public class PlayerEvents implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);

    /*@EventHandler
    public void interact(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType() == Material.WALL_SIGN) {
                Location loc = event.getClickedBlock().getLocation();
                ProtocolManager pm = ProtocolLibrary.getProtocolManager();
                PacketContainer packet = pm.createPacket(PacketType.Play.Server.UPDATE_SIGN);
                packet.getModifier().writeDefaults();
                packet.getBlockPositionModifier().write(0, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
                packet.getChatComponentArrays().write(0, new WrappedChatComponent[] {
                    WrappedChatComponent.fromText("hola"),
                    WrappedChatComponent.fromText("hola"),
                    WrappedChatComponent.fromText("hola"),
                    WrappedChatComponent.fromText("hola")
                });
                try {
                    pm.sendServerPacket(event.getPlayer(), packet);
                } catch (InvocationTargetException el) {
                    el.printStackTrace();
                }
            }
        }
    }*/

}
