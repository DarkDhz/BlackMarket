package dev.darkhorizon.es.black;

import dev.darkhorizon.es.black.Data.TempData;
import dev.darkhorizon.es.black.bosses.CustomBoss;
import dev.darkhorizon.es.black.commands.BlackMarket;
import dev.darkhorizon.es.black.commands.VirtualBoss;
import dev.darkhorizon.es.black.events.EntityEvents;
import dev.darkhorizon.es.black.events.InventoryEvents;
import dev.darkhorizon.es.black.events.PlayerEvents;
import dev.darkhorizon.es.black.events.boss.BossSpawn;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();
        this.initCommands();
        this.initEvents();
    }

    @Override
    public void onDisable() {
    }

    private void initEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);
        pm.registerEvents(new EntityEvents(), this);
    }


    private void initCommands() {
        getCommand("blackmarket").setExecutor(new BlackMarket());
        getCommand("virtualboss").setExecutor(new VirtualBoss());
    }
}
