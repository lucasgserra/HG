package rainprojects.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rainprojects.hg.grupos.GrupoManager;
import rainprojects.hg.grupos.GruposEnum;
import rainprojects.hg.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCommand implements CommandExecutor, CommandInterface {

    String commandName = "tag";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (!GrupoManager.getGrupo().containsKey(player.getName())) {
            player.sendMessage("§cOcorreu um erro... Tenta novamente mais tarde");
            return false;
        }
        List<GruposEnum> haveGroups = new ArrayList<>();
        GruposEnum currentGrupo = GrupoManager.group(player.getName());
        Arrays.asList(GruposEnum.values()).forEach(grupos -> {
            if(grupos.getWeight() <= currentGrupo.getWeight()) {
                haveGroups.add(grupos);
            }
        });
        StringBuilder sb = new StringBuilder();
        haveGroups.forEach(grupos -> {
            sb.append(grupos.getPrefix() + grupos.toString().toLowerCase() + " ");
        });
        if (args.length == 0) {
            player.sendMessage("§aUse /" + commandName + " ( "+sb.toString()+"§a)");
            return true;
        }
        if (args.length == 1) {
            try {
                String targetTag = args[0];
                if (haveGroups.contains(GruposEnum.valueOf(targetTag.toUpperCase()))) {
                    player.sendMessage("§aAgora voce esta com a tag: " + targetTag);
                    GrupoManager.updatePrefix(player.getName(),
                            GruposEnum.valueOf(targetTag.toUpperCase()).getPrefix());
                    Bukkit.getOnlinePlayers().forEach(on->{
                        ScoreboardManager.setupTab(on);
                    });
                } else {
                    player.sendMessage("§cTag nao existe ou sem permissao!");
                }
            } catch (IllegalArgumentException e) {
                player.sendMessage("§cTag nao existe ou sem permissao!");
            }
        }
        return false;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
