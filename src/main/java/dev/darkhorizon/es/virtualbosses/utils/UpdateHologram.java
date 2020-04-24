package dev.darkhorizon.es.virtualbosses.utils;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;
import dev.darkhorizon.es.virtualbosses.Main;

import java.util.Calendar;

public class UpdateHologram {

    private final static Main plugin = Main.getPlugin(Main.class);


    public static void initPalaceholders() {
        HologramsAPI.registerPlaceholder(plugin, "{boss-time}", 1.0, new PlaceholderReplacer() {
            @Override
            public String update() {
                return BossUtils.getNextSpawn(Calendar.getInstance().getTime());
            }
        });
    }
}
