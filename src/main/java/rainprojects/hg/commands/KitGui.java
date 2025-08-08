package rainprojects.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class KitGui implements CommandExecutor {

    public static Collection<String> commandName = Arrays.asList("kit", "kits");

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        if (commandName.contains(command.getName())) {
            //GUI
        }
        return false;
    }
}
