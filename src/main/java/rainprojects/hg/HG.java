package rainprojects.hg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;
import rainprojects.hg.commands.CommandInterface;
import rainprojects.hg.grupos.GrupoManager;
import rainprojects.hg.timer.SchedulerGame;

import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.stream.Collectors;

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
    private void loadAllCommands() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages("rainprojects.hg")
                .addScanners(new SubTypesScanner(false))
                .addClassLoaders(getClass().getClassLoader()) // útil no Bukkit
        );
        Set<Class<? extends CommandExecutor>> execs = reflections.getSubTypesOf(CommandExecutor.class);
        Set<Class<? extends CommandInterface>> custom = reflections.getSubTypesOf(CommandInterface.class);
        Set<Class<?>> both = execs.stream()
                .filter(custom::contains)
                .filter(c -> !Modifier.isAbstract(c.getModifiers()) && !c.isInterface())
                .filter(c -> {
                    try {
                        c.getDeclaredConstructor();
                        return true;
                    } catch (NoSuchMethodException e) {
                        return false;
                    }
                })
                .collect(Collectors.toSet());

        for (Class<?> clazz : both) {
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                CommandExecutor executor = (CommandExecutor) instance;
                CommandInterface info = (CommandInterface) instance;
                String commandName = info.getCommandName();

                PluginCommand cmd = getCommand(commandName);
                if (cmd == null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED +
                            "Comando nao encontrado no plugin.yml: " + commandName +
                            " (" + clazz.getSimpleName() + ")");
                    continue;
                }

                cmd.setExecutor(executor);

                // Se tiver tab-completer na sua interface:
                if (executor instanceof TabCompleter) {
                    cmd.setTabCompleter((TabCompleter) executor);
                }

                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN +
                        "Comando registrado: " + commandName + " -> " + clazz.getSimpleName());
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED +
                        "Falha ao instanciar " + clazz.getName() + ": " + e.getMessage());
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
        loadAllCommands();
    }

    @Override
    public void onDisable() {
        GrupoManager.save();
    }

    public static HG getInstance() {
        return instance;
    }
}
