package dev.darkhorizon.es.black.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.darkhorizon.es.black.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.lang.reflect.InvocationTargetException;

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
