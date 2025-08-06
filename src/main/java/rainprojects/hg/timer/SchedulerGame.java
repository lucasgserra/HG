package rainprojects.hg.timer;

import org.bukkit.Bukkit;
import rainprojects.hg.HG;
import rainprojects.hg.utils.PlayerMessages;

public class SchedulerGame {

    private static GameState gameState;

    public static void init() {
        gameState = GameState.STARTING;
        startTimer();
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static void setGameState(GameState gameState) {
        SchedulerGame.gameState = gameState;
    }

    static int i;
    private static void startTimer() {
        i = getGameState().getDurationState();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.getInstance(),
                ()->{
                    switch (getGameState()) {
                        case STARTING:
                            if (i == 0) {
                                if (Bukkit.getOnlinePlayers().size() < 5) {
                                    Bukkit.getOnlinePlayers().forEach(
                                            player->
                                                    PlayerMessages.timerMessage(player,
                                                            "Jogadores insuficientes, recomecando!")
                                    );
                                    i = getGameState().getDurationState();
                                } else {
                                    setGameState(GameState.INVULNERABILITY);
                                    i = gameState.getDurationState();
                                }
                            }
                            if (i % 10 == 0) {
                                Bukkit.getOnlinePlayers().forEach(
                                        player->
                                                PlayerMessages.timerMessage(player, i)
                                );
                            }
                            break;
                        case INVULNERABILITY:

                            break;
                    }
                    i--;
                }, 0L, 20L);
    }
}