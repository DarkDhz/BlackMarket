package dev.darkhorizon.es.black.utils;

import dev.darkhorizon.es.black.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionBar {

    private static final Main plugin = Main.getPlugin(Main.class);

    public static void sendActionBar(Player player, String message) {
        if (!player.isOnline())
            return;
        try {
            Object packet;
            String nmsver = Bukkit.getServer().getClass().getPackage().getName();
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Class<?> packetPlayOutChatClass = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            Class<?> packetClass = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            Class<?> chatSerializerClass = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
            Class<?> iChatBaseComponentClass = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
            Method m3 = chatSerializerClass.getDeclaredMethod("a", new Class[] { String.class });
            Object cbc = iChatBaseComponentClass.cast(m3.invoke(chatSerializerClass, new Object[] { "{\"text\": \"" + message + "\"}" }));
            packet = packetPlayOutChatClass.getConstructor(new Class[] { iChatBaseComponentClass, byte.class }).newInstance(new Object[] { cbc, Byte.valueOf((byte)2) });
            Method craftPlayerHandleMethod = craftPlayerClass.getDeclaredMethod("getHandle", new Class[0]);
            Object craftPlayerHandle = craftPlayerHandleMethod.invoke(craftPlayer, new Object[0]);
            Field playerConnectionField = craftPlayerHandle.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(craftPlayerHandle);
            Method sendPacketMethod = playerConnection.getClass().getDeclaredMethod("sendPacket", new Class[] { packetClass });
            sendPacketMethod.invoke(playerConnection, new Object[] { packet });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);
        if (duration >= 0)
            (new BukkitRunnable() {
                public void run() {
                    sendActionBar(player, "");
                }
            }).runTaskLater(plugin, (duration + 1));
        while (duration > 40) {
            duration -= 40;
            (new BukkitRunnable() {
                public void run() {
                    sendActionBar(player, message);
                }
            }).runTaskLater(plugin, duration);
        }
    }
}
