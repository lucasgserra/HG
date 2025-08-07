package rainprojects.hg.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import rainprojects.hg.grupos.GrupoManager;
import rainprojects.hg.grupos.GruposEnum;

public class ScoreboardManager {

    public static void setupTab(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective sidebar = scoreboard.registerNewObjective("sidebar", "dummy");
        sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
        sidebar.setDisplayName(ChatColor.GOLD + "HG");

        sidebar.getScore(ChatColor.GRAY + "HG - TESTE").setScore(1);

        for (Player target : Bukkit.getOnlinePlayers()) {
            String teamName = getPlayerTeamName(target);

            Team team = scoreboard.getTeam(teamName);
            if (team == null) {
                team = scoreboard.registerNewTeam(teamName);

                String prefix = "";
                if (GrupoManager.group(player.getName()).getWeight() >= GruposEnum.VIP.getWeight()) {
                    prefix =GrupoManager.group(player.getName()).getPrefix();
                } else {
                    prefix = "§7";
                }
                team.setPrefix(prefix);
            }
            team.addEntry(target.getName());
        }

        player.setScoreboard(scoreboard);
    }
    private static String getPlayerTeamName(Player player) {
        int grupoPeso = GrupoManager.group(player.getName()).getWeight();

        int pesoInvertido = 999 - grupoPeso;

        if (grupoPeso >= GruposEnum.TRIAL.getWeight()) {
            return String.format("G%03d_ST", pesoInvertido);
        } else if (grupoPeso >= GruposEnum.VIP.getWeight()) {
            return String.format("G%03d_VIP", pesoInvertido);
        } else {
            return String.format("M%02d_R%02d", 100, pesoInvertido);
        }

    }

}
