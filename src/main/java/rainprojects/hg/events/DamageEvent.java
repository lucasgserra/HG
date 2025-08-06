package rainprojects.hg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import rainprojects.hg.timer.GameState;
import rainprojects.hg.timer.SchedulerGame;

public class DamageEvent implements Listener {

    @EventHandler
    void event(EntityDamageByEntityEvent event) {
        if (SchedulerGame.getGameState().equals(GameState.STARTING)) event.setCancelled(true);
    }

    @EventHandler
    void event2(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (SchedulerGame.getGameState().equals(GameState.STARTING)) event.setCancelled(true);
        }
    }

}
