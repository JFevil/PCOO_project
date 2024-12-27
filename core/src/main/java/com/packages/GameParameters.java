package com.packages;

import com.badlogic.gdx.Game;

public class GameParameters {
    private static GameParameters instance;
    private boolean debugMode;

    private GameParameters() {
        debugMode = false;
    }

    public static GameParameters getInstance() {
        if (instance == null) {
            instance = new GameParameters();
        }
        return instance;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
