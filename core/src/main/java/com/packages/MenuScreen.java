package com.packages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;

public class MenuScreen implements Screen {
    private final Main game;
    private SpriteBatch batch;
    private BitmapFont font;

    public MenuScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2); // Agrandir la taille du texte
    }

    @Override
    public void render(float delta) {
        // Effacer l'écran avec une couleur de fond
        ScreenUtils.clear(Color.DARK_GRAY);

        // Début du rendu
        batch.begin();
        font.draw(batch, "Bienvenue dans le jeu !", 200, 400);
        font.draw(batch, "Appuyez sur [Entrée] pour commencer", 150, 300);
        font.draw(batch, "Appuyez sur [Echap] pour quitter", 150, 250);
        batch.end();

        // Gestion des entrées utilisateur
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            // Changer pour l'écran de jeu
            game.setScreen(new GameScreen(game));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Quitter le jeu
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Gérer les modifications de taille si nécessaire
    }

    @Override
    public void pause() {
        // Pause du menu si nécessaire
    }

    @Override
    public void resume() {
        // Reprise du menu
    }

    @Override
    public void hide() {
        // Libérer les ressources lorsque l'écran est masqué
    }

    @Override
    public void dispose() {
        // Libération des ressources
        batch.dispose();
        font.dispose();
    }
}
