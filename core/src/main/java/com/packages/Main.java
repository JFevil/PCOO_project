package com.packages;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.packages.utils.GameParameters;
import com.packages.view.MenuScreen;
import com.packages.view.GameScreen;
import com.packages.view.PauseMenu;

public class Main extends Game {
    private Screen menuScreen;
    private GameScreen gameScreen;
    private Screen pauseMenu;

    @Override
    public void create() {
        menuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        pauseMenu = new PauseMenu(this);
        this.setScreen(menuScreen); // Démarrage avec l'écran du menu
    }

    public void pauseGame() {
        GameParameters.getInstance().setPaused(true);
        setScreen(pauseMenu);
    }

    public void resumeGame() {
        GameParameters.getInstance().setPaused(false);
        setScreen(gameScreen);
    }
}