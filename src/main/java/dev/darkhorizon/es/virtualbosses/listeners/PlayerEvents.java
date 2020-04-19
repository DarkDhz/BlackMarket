package dev.darkhorizon.es.virtualbosses.listeners;

import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.items.GiveItems;
import dev.darkhorizon.es.virtualbosses.utils.Comparators;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEvents implements Listener {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static GiveItems giveItems = GiveItems.getInstance();

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
    @EventHandler
    public void onPotion(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (Comparators.validateItem(item, giveItems.getSuperPocion())) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 10*60*20, 4), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10*60*20, 1), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5*60*20, 1), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 2*60*20, 0), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10*60*20, 1), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 10*60*20, 0), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 5*60*20, 0), true);
        }
    }

}
