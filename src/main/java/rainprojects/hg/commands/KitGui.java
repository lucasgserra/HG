package rainprojects.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rainprojects.hg.inventories.KitGuiInventory;

public class KitGui implements CommandExecutor, CommandInterface{

    public static String commandName = "kit";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return true;
        }
        if (commandName.contains(command.getName())) {
            KitGuiInventory.gui(((Player) commandSender).getPlayer());
        }
        return false;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
