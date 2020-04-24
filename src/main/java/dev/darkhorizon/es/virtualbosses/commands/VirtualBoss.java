package dev.darkhorizon.es.virtualbosses.commands;

import dev.darkhorizon.es.virtualbosses.api.json.FancyMessage;
import dev.darkhorizon.es.virtualbosses.bosses.entities.*;
import dev.darkhorizon.es.virtualbosses.data.temp.TempData;
import dev.darkhorizon.es.virtualbosses.Main;
import dev.darkhorizon.es.virtualbosses.bosses.CustomBoss;
import dev.darkhorizon.es.virtualbosses.config.Lang;
import dev.darkhorizon.es.virtualbosses.config.Perms;
import dev.darkhorizon.es.virtualbosses.gui.global.BossList;
import dev.darkhorizon.es.virtualbosses.gui.GUI;
import dev.darkhorizon.es.virtualbosses.items.GiveItems;
import dev.darkhorizon.es.virtualbosses.utils.BossUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VirtualBoss implements CommandExecutor {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final TempData temp_data = TempData.getInstance();
    private static final Lang lang = Lang.getInstance();
    private static final Perms perms = Perms.getInstance();


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            this.manageCommand((Player) commandSender, strings);
        } else {
            commandSender.sendMessage(lang.global_only_players);
        }
        return true;
    }

    private void manageCommand(Player launcher, String[] args) {
        if (args.length == 0) {
            launcher.sendMessage("§5");
            launcher.sendMessage("§aComandos de Bosses:");
            launcher.sendMessage("§6");
            launcher.sendMessage("§6- §f/boss help");
            new FancyMessage("§6- §f/boss go").command("/warp mazmorra")
                    .tooltip("§6Clic para ir a la mazmorra").send(launcher);
            List<String> tool = new ArrayList<>();
            tool.add("§6- §fProximo Spawn: §d" + BossUtils.getNextSpawn(Calendar.getInstance().getTime()));
            tool.add("§2");
            tool.add("§6Clic para verlo vía comando");
            new FancyMessage("§6- §f/boss time").command("/vb time")
                    .tooltip(tool).send(launcher);
            tool = new ArrayList<>();
            tool.add("§fBosses actuales: ");
            tool.add("§6- " + ExplosiveCreeper.name);
            tool.add("§6- " + ZombieKing.name);
            tool.add("§6- " + ArmoredGolem.name);
            tool.add("§6- " + DamnedSkeleton.name);
            tool.add("§6- " + Invocator.name);
            tool.add("§2");
            tool.add("§6Clic para ver el menu");
            new FancyMessage("§6- §f/boss list").command("/vb list")
                    .tooltip(tool).send(launcher);
            if (launcher.hasPermission(perms.vb_spawn)) {
                new FancyMessage("§c- §f/boss spawn <mob>").command("/vb spawn")
                        .tooltip("§6Clic para ver la los bosses disponibles").send(launcher);
            }
            if (launcher.hasPermission(perms.vb_kill)) {
                new FancyMessage("§c- §f/boss killall").command("/vb killall")
                        .tooltip("§6Clic para eliminar §f" + temp_data.getEntities().size() + " §6entidades").send(launcher);
            }
            launcher.sendMessage("§7");
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help") ||
                    args[0].equalsIgnoreCase("ayuda")) {
                launcher.performCommand("virtualbosses");
                return;
            }
            if (args[0].equalsIgnoreCase("go") ||
                    args[0].equalsIgnoreCase("tp")) {
                launcher.performCommand("warp mazmorra");
                return;
            }
            if (args[0].equalsIgnoreCase("time")) {
                launcher.sendMessage(lang.info_time.replaceAll("%time",
                        BossUtils.getNextSpawn(Calendar.getInstance().getTime())));
                return;
            }
            if (args[0].equalsIgnoreCase("list") ||
                    args[0].equalsIgnoreCase("tiempo")) {
                GUI gui = BossList.getInstance();
                gui.open(launcher);

                return;
            }
            if (args[0].equalsIgnoreCase("killall") && launcher.hasPermission(perms.vb_kill)) {
                int count = 0;
                while (!temp_data.getEntities().isEmpty() && count < 1000) {
                    try {
                        for (LivingEntity e : temp_data.getEntities().values()) {
                            temp_data.getEntities().remove(e.getUniqueId());
                            if (temp_data.getBoss_damagers().get(e.getUniqueId()) != null) {
                                temp_data.getBoss_damagers().remove(e.getUniqueId());
                            }
                            e.remove();
                            count++;
                        }
                    } catch (Exception ignored) {
                    }

                }
                launcher.sendMessage(lang.global_prefix + " Se han eliminado §c" + count + "§f entidades");
                return;
            }
            if (args[0].equalsIgnoreCase("spawn") && launcher.hasPermission(perms.vb_spawn)) {
                launcher.sendMessage("§1");
                launcher.sendMessage("§aBosses Disponibles:");
                launcher.sendMessage("§2");
                new FancyMessage("§c- §f/boss spawn creeper").command("/vb spawn creeper")
                        .tooltip("§6Clic para spawnear Creeper").send(launcher);
                new FancyMessage("§c- §f/boss spawn reyzombie").command("/vb spawn reyzombie")
                        .tooltip("§6Clic para spawnear Rey Zombie").send(launcher);
                new FancyMessage("§c- §f/boss spawn invocator").command("/vb spawn invocator")
                        .tooltip("§6Clic para spawnear Invocador").send(launcher);
                new FancyMessage("§c- §f/boss spawn golem").command("/vb spawn golem")
                        .tooltip("§6Clic para spawnear Golem").send(launcher);
                new FancyMessage("§c- §f/boss spawn skeleton").command("/vb spawn skeleton")
                        .tooltip("§6Clic para spawnear Damned Skeleton").send(launcher);
                launcher.sendMessage("§3");
                return;
            }
            new FancyMessage(lang.global_prefix + " Comando invalido, clic para ver los disponibles").command("/boss")
                    .tooltip("§6Clic para ver la ayuda de bosses").send(launcher);

        } else {
            if (args[0].equals("spawn")) {
                if (args[1].equalsIgnoreCase("creeper") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<ExplosiveCreeper> boss = new ExplosiveCreeper(launcher.getLocation());
                    launcher.sendMessage(lang.global_prefix + " Has creado un Boss " + ExplosiveCreeper.name + " §fen tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("reyzombie") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<ZombieKing> boss = new ZombieKing(launcher.getLocation());
                    launcher.sendMessage(lang.global_prefix + " Has creado un Boss " + ZombieKing.name + " §fen tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("skeleton") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<DamnedSkeleton> boss = new DamnedSkeleton(launcher.getLocation());
                    launcher.sendMessage(lang.global_prefix + " Has creado un Boss " + DamnedSkeleton.name + " §fen tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("invocator") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<Invocator> boss = new Invocator(launcher.getLocation());
                    launcher.sendMessage(lang.global_prefix + " Has creado un Boss " + Invocator.name + " §fen tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("golem") && launcher.hasPermission(perms.vb_spawn)) {
                    CustomBoss<ArmoredGolem> boss = new ArmoredGolem(launcher.getLocation());
                    launcher.sendMessage(lang.global_prefix + " Has creado un Boss " + ArmoredGolem.name + " §fen tu localizacion.");
                    return;
                }
                if (args[1].equalsIgnoreCase("test") && launcher.hasPermission(perms.vb_spawn)) {
                    launcher.getInventory().addItem(GiveItems.getInstance().getSuperPocion());
                    return;
                }
                new FancyMessage(lang.global_prefix + " Mob invalido, clic para ver los disponibles.").command("/boss spawn")
                        .tooltip("§6Clic para ver la ayuda de boss spawn").send(launcher);
                return;
            }
            new FancyMessage(lang.global_prefix + " Comando invalido, clic para ver los disponibles.").command("/boss")
                    .tooltip("§6Clic para ver la ayuda de bosses").send(launcher);
        }
    }
}
