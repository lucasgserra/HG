package rainprojects.hg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import rainprojects.hg.grupos.GrupoManager;

public class Chat implements Listener {

    @EventHandler
    void chat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        event.setFormat("<" +
                GrupoManager.group(player.getName()).getPrefix() +player.getName()+"§f> " + event.getMessage());
    }

}
