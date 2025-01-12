package com.packages.utils;

// Singleton class to store parameters of the game
public class GameParameters {
    private static GameParameters instance;
    private boolean showHitbox;
    private boolean showPlayerAim;
    private boolean paused;

    private GameParameters() {
        showHitbox = false;
        paused = false;
        showPlayerAim = false;
    }

    public static GameParameters getInstance() {
        if (instance == null) {
            instance = new GameParameters();
        }
        return instance;
    }

    public boolean isShowHitbox() {return showHitbox;}
    public boolean isPaused() {return paused;}
    public boolean isShowPlayerAim() {return showPlayerAim;}
    public void setShowHitbox(boolean showHitbox) {this.showHitbox = showHitbox;}
    public void setPaused(boolean paused) {this.paused = paused;}
    public void setShowPlayerAim(boolean showPlayerAim) {this.showPlayerAim = showPlayerAim;}
}
