package com.packages.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.packages.Main;
import com.packages.InputHandler;
import com.packages.controller.CameraController;
import com.packages.controller.entity.monster.MonsterController;
import com.packages.controller.entity.player.PlayerController;
import com.packages.factory.movableEntity.monster.MonsterFactory;
import com.packages.factory.movableEntity.monster.Orc1Factory;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.TextureFactory;
import com.packages.view.entity.player.PlayerView;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final Main game;
    private boolean paused = false;
    private BitmapFont pauseFont;

    private SpriteBatch batch;
    private PlayerView playerView;
    private PlayerController playerController;
    private InputHandler inputHandler;
    private CameraController cameraController;
    private List<MonsterController> monsters;
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;

    public GameScreen(Main game) {
        this.game = game;
        monsters = new ArrayList<>();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        // Charger la texture de fond
        backgroundTexture = TextureFactory.getTexture("assets/background.png");
        backgroundRegion = new TextureRegion(backgroundTexture);

        // Initialize CameraController
        cameraController = new CameraController(800, 800);

        // Initialize models
        Player player = new Player(400, 400, 100, 100, 300, 50, 10);
        playerView = new PlayerView(player, cameraController);
        playerController = new PlayerController(player, playerView);

        // Initialize InputHandler
        inputHandler = new InputHandler(this);

        Orc1Factory orc1Factory = new Orc1Factory(cameraController);
        MonsterFactory monsterFactory = new MonsterFactory();

        for (int i=0; i<50; i++) {
            int x = (int) (Math.random() * 800);
            int y = (int) (Math.random() * 800);
            int speed = (int) (Math.random() * 100) + 50;
            addMonster(monsterFactory.createMonster(orc1Factory, x, y, speed, player));
        }

        // Font for pause screen
        pauseFont = new BitmapFont();
        pauseFont.setColor(Color.YELLOW);
        pauseFont.getData().setScale(2);
    }

    public void addMonster(MonsterController controller) {
        monsters.add(controller);
    }

    public void removeMonster(MonsterController controller) {
        controller.getView().dispose();
        monsters.remove(controller);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        cameraController.update(delta);
        cameraController.follow(playerView.getX()+playerView.getWidth()/2, playerView.getY()+playerView.getHeight()/2);

        batch.setProjectionMatrix(cameraController.getCamera().combined);
        batch.begin();

        drawBackground();

        if (isPaused()) {
            pauseFont.draw(batch, "Game Paused", 300, 400);
            pauseFont.draw(batch, "Press ESC to resume", 200, 350);
        } else {
            inputHandler.handleInput();
            playerView.render(batch);
            for (MonsterController monster : monsters) {
                monster.updateMonsterPosition();
                monster.getView().render(batch);
            }
        }

        batch.end();
    }

    public void drawBackground() {
        // Get the camera's position and zoom
        float cameraX = cameraController.getCamera().position.x - cameraController.getCamera().viewportWidth / 2;
        float cameraY = cameraController.getCamera().position.y - cameraController.getCamera().viewportHeight / 2;
        float zoom = cameraController.getCamera().zoom;

        // Adjust the size of the background tiles based on the zoom level
        float tileWidth = backgroundRegion.getRegionWidth() * zoom;
        float tileHeight = backgroundRegion.getRegionHeight() * zoom;

        // Draw the background texture repeatedly considering negative coordinates
        for (int x = (int) (Math.floor(cameraX / tileWidth) * tileWidth); x < cameraX + cameraController.getCamera().viewportWidth; x += tileWidth) {
            for (int y = (int) (Math.floor(cameraY / tileHeight) * tileHeight); y < cameraY + cameraController.getCamera().viewportHeight; y += tileHeight) {
                batch.draw(backgroundRegion, x, y, tileWidth, tileHeight);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        cameraController.getCamera().viewportWidth = width;
        cameraController.getCamera().viewportHeight = height;
        cameraController.getCamera().update();
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerView.dispose();
        pauseFont.dispose();
        backgroundTexture.dispose(); // N'oubliez pas de disposer de la texture de fond
        for (MonsterController monster : monsters) {
            monster.getView().dispose();
        }
    }

    public Player getPlayer() {
        return playerController.getPlayer();
    }

    public CameraController getCameraController() {
        return cameraController;
    }

    public boolean isPaused() {
        return paused;
    }
}