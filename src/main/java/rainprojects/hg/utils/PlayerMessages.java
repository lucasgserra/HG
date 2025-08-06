package rainprojects.hg.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerMessages {

    public static void timerMessage(Player player, Integer time) {
        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
        player.sendMessage("");
        player.sendMessage(" §a§l                 TIMER");
        player.sendMessage(" §aFaltam §f" + time + "§e segundos §apara iniciar a partida!");
        player.sendMessage("");
    }
    public static void timerMessage(Player player, String message) {
        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
        player.sendMessage("");
        player.sendMessage(" §a§l                 TIMER");
        player.sendMessage(" §a" + message);
        player.sendMessage("");
    }

}
