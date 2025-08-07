package rainprojects.hg.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import rainprojects.hg.grupos.GrupoManager;
import rainprojects.hg.scoreboard.ScoreboardManager;
import rainprojects.hg.timer.GameState;
import rainprojects.hg.timer.SchedulerGame;

public class Join implements Listener {

    @EventHandler
    void join1(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        GrupoManager.createPlayer(player.getName());

        player.getInventory().clear();
        player.getInventory().setItem(4, new ItemStack(Material.PAPER));

        ScoreboardManager.setupTab(player);
        Bukkit.getOnlinePlayers().forEach(ScoreboardManager::setupTab);
    }

    @EventHandler
    void join2(PlayerLoginEvent event) {
        if (!SchedulerGame.getGameState().equals(GameState.STARTING)) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "A partida iniciou!");
        }
    }

}
