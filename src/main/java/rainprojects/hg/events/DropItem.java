package rainprojects.hg.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import rainprojects.hg.timer.GameState;
import rainprojects.hg.timer.SchedulerGame;

public class BreakBlock implements Listener {

    @EventHandler
    void event(BlockBreakEvent event) {
        if (SchedulerGame.getGameState().equals(GameState.STARTING)) event.setCancelled(true);
    }

}
