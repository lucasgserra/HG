package rainprojects.hg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import rainprojects.hg.grupos.GrupoManager;
import rainprojects.hg.timer.SchedulerGame;

import java.util.Set;

public final class HG extends JavaPlugin {

    private static HG instance;

    private void loadAllListeners() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages("rainprojects.hg")
                .addScanners(new SubTypesScanner(false))
        );

        Set<Class<? extends Listener>> listeners = reflections.getSubTypesOf(Listener.class);

        for (Class<? extends Listener> listenerClass : listeners) {
            try {
                Listener listener = listenerClass.newInstance();
                Bukkit.getPluginManager().registerEvents(listener, this);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Listener registrado: " + listenerClass.getSimpleName());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(false);
        saveDefaultConfig();

        GrupoManager.load();

        SchedulerGame.init();
        loadAllListeners();
    }

    @Override
    public void onDisable() {
        GrupoManager.save();
    }

    public static HG getInstance() {
        return instance;
    }
}
