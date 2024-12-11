package com.packages.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.packages.Main;
import com.packages.controller.entity.monster.MonsterController;
import com.packages.controller.entity.player.PlayerController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.monster.MonsterView;
import com.packages.view.entity.player.PlayerView;

public class GameScreen implements Screen {
    private final Main game;
    private boolean isPaused = false;
    private BitmapFont pauseFont;

    private SpriteBatch batch;
    private PlayerView playerView;
    private MonsterView monsterView1;
    private MonsterView monsterView2;
    private PlayerController playerController;
    private MonsterController monsterController1;
    private MonsterController monsterController2;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        // Initialisation des modèles
        Player player = new Player(400, 400, 100, 100, 300, 50, 10);
        Monster monster1 = new Monster(300, 300, 100, 100, 100, 50, 5, player);
        Monster monster2 = new Monster(100, 100, 100, 100, 150, 50, 5, player);

        // Initialisation des vues
        playerView = new PlayerView(player);
        monsterView1 = new MonsterView(monster1);
        monsterView2 = new MonsterView(monster2);

        // Initialisation des contrôleurs
        playerController = new PlayerController(player);
        monsterController1 = new MonsterController(monster1, player);
        monsterController2 = new MonsterController(monster2, player);

        // Police pour l'écran de pause
        pauseFont = new BitmapFont();
        pauseFont.setColor(Color.YELLOW);
        pauseFont.getData().setScale(2);
    }


    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (isPaused) {
                resume(); // Reprendre le jeu
            } else {
                pause(); // Mettre en pause
            }
        }

        ScreenUtils.clear(Color.BLACK);

        batch.begin();

        if (isPaused) {
            // Affichage du message de pause
            pauseFont.draw(batch, "Jeu en pause", 300, 400);
            pauseFont.draw(batch, "Appuyez sur ECHAP pour reprendre", 200, 350);
        } else {
            // Mise à jour et rendu des entités
            playerController.input();
            monsterController1.updateMonsterPosition();
            monsterController2.updateMonsterPosition();

            playerView.render(batch);
            monsterView1.render(batch);
            monsterView2.render(batch);
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Gérer le redimensionnement si nécessaire
    }

    @Override
    public void pause() {
        isPaused = true;
        // Ajoute ici d'autres actions spécifiques si nécessaire
    }

    @Override
    public void resume() {
        isPaused = false;
        // Ajoute ici d'autres actions spécifiques si nécessaire
    }

    @Override
    public void hide() {
        // Libérer les ressources si nécessaire lorsque l'écran est caché
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerView.dispose();
        monsterView1.dispose();
        monsterView2.dispose();
        pauseFont.dispose();
    }
}
