package rainprojects.hg.timer;

public enum GameState {

    STARTING(60),
    INVULNERABILITY(90),
    GAME(60*5),
    END_GAME(180);

    private final int durationState;

    GameState(int durationState) {
        this.durationState = durationState;
    }

    public int getDurationState() {
        return durationState;
    }
}
