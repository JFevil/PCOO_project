package com.packages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.packages.utils.GameMap;
import com.packages.utils.GameParameters;
import com.packages.utils.InputHandler;
import com.packages.controller.CameraController;
import com.packages.controller.Controller;
import com.packages.controller.entity.RockController;
import com.packages.controller.entity.movableEntity.monster.MonsterController;
import com.packages.controller.entity.movableEntity.player.PlayerController;
import com.packages.controller.entity.movableEntity.projectile.BallController;
import com.packages.factory.movableEntity.monster.MonsterSpawner;
import com.packages.factory.movableEntity.monster.Orc1MonsterFactory;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.model.entity.structure.Rock;
import com.packages.utils.Grid;
import com.packages.utils.TextureFactory;
import com.packages.view.GameUI;
import com.packages.view.entity.RockView;
import com.packages.view.entity.movableEntity.monster.MonsterView;
import com.packages.view.entity.movableEntity.player.PlayerView;
import com.packages.view.entity.movableEntity.projectile.BallView;

import java.util.ArrayList;
import java.util.List;

/**
 * GameScreen class
 * This class is used to display the game screen
 */
public class GameScreen implements Screen {
    private final Main game;
    private BitmapFont pauseFont;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private PlayerController playerController;
    private InputHandler inputHandler;
    private CameraController cameraController;
    private List<MonsterController> monsters;
    private Grid grid;
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private Stage stage;
    private Skin skin;
    private GameUI gameUI;
    private boolean initialized = false;
    private List<RockController> rocks;

    /**
     * Constructor of the class GameScreen
     * @param game Pointeur vers la classe Main
     */
    public GameScreen(Main game) {
        this.game = game;
        monsters = new ArrayList<>();
        grid = new Grid(100); // Cell size adjusted as needed
    }

    /**
     * Initialize the game screen
     */
    private void initialize() {
        try {
            System.out.println("GameScreen initialize");
            batch = new SpriteBatch();
            shapeRenderer = new ShapeRenderer();

            backgroundTexture = TextureFactory.getTexture("assets/background.png");
            backgroundRegion = new TextureRegion(backgroundTexture);

            cameraController = new CameraController(300, 300);


            GameMap gameMap = new GameMap("tiled/carte0.tmx", cameraController);
            rocks = gameMap.getRocks();

            Rectangle playerStartPosition = gameMap.getPlayerStartPosition();
            createPlayerController(playerStartPosition.x , playerStartPosition.y, 50, 300, 50, 10);

            inputHandler = new InputHandler(this);

            gameUI = new GameUI();

            Orc1MonsterFactory orc1Factory = new Orc1MonsterFactory(cameraController);

            for (int i = 0; i < 50; i++) {
                float x = getPlayer().getX() + (float) Math.random() * 1000 - 500;
                float y = getPlayer().getY() + (float) Math.random() * 1000 - 500;
                int speed = 100;
                addMonster(MonsterSpawner.createMonster(orc1Factory, x, y, speed, getPlayer()));
            }

            for (RockController rock : rocks) {
                grid.add(rock);
            }

            pauseFont = new BitmapFont();
            pauseFont.setColor(Color.YELLOW);
            pauseFont.getData().setScale(2);

            stage = new Stage();
            skin = new Skin(Gdx.files.internal("skin/craftacular/skin/craftacular-ui.json"));

            initialized = true;
        } catch (Exception e) {
            System.out.println("Error initializing GameScreen");
            e.printStackTrace();
        }
    }

    /**
     * Show the game screen
     */
    @Override
    public void show() {
        if (!initialized) {
            initialize();
        }
        Gdx.input.setInputProcessor(inputHandler);
    }

    /**
     * Add a monster to the game
     * @param controller MonsterController
     */
    public void addMonster(MonsterController controller) {
        monsters.add(controller);
        grid.add(controller);
    }

    /**
     * Remove a monster from the game
     * @param controller MonsterController
     */
    public void removeMonster(MonsterController controller) {
        monsters.remove(controller);
        grid.remove(controller);
    }

    /**
     * Create a player controller
     * @param x Position x
     * @param y Position y
     * @param size Size
     * @param speed Speed
     * @param health Health
     * @param damage Damage
     */
    public void createPlayerController(float x, float y, float size, float speed, int health, int damage) {
        Player player = new Player(x, y, size, speed, health, damage, cameraController);
        PlayerView playerView = new PlayerView(player, cameraController);
        playerController = new PlayerController(player, playerView);
    }

    /**
     * Render the game screen
     * @param delta Time between two frames
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        cameraController.update(delta);
        cameraController.follow(getPlayerView().getX() + ((Player)playerController.getModel()).getRadius(), getPlayerView().getY() + ((Player)playerController.getModel()).getRadius());

        batch.setProjectionMatrix(cameraController.getCamera().combined);
        shapeRenderer.setProjectionMatrix(cameraController.getCamera().combined);
        gameUI.update(playerController.getModel());

        inputHandler.handleInput();
        batch.begin();
        drawBackground();
        updateEntities();
        drawMonsters();
        drawRocks();
        drawBalls();
        getPlayerView().render(batch);
        batch.end();
        gameUI.render();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        if (GameParameters.getInstance().isShowPlayerAim()) {
            drawPlayerAim();
        }

        if (GameParameters.getInstance().isShowHitbox()) {
            drawMonstersDebug();
            getPlayerView().renderHitbox(shapeRenderer);
//            grid.renderDebug(shapeRenderer);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.line(-100, 0, 100, 0);
            shapeRenderer.line(0, -100, 0, 100);
        }

        shapeRenderer.end();
        stage.act(delta);
        stage.draw();
    }

    /**
     * Update the entities and handle collisions
     */
    public void updateEntities() {
        grid.clear();
        for (MonsterController monster : monsters) {
            grid.add(monster);
        }
        for (RockController rock : rocks) {
            grid.add(rock);
        }


        // Collision entre toutes les entités qui ne sont pas des rochers
        for (MonsterController e1 : monsters) {
            if (((Player)playerController.getModel()).collidesWith((Monster)e1.getModel())) {
                ((Player)playerController.getModel()).applyCollisionForce((Monster)e1.getModel());
            }
            List<Controller> nearbyEntities = grid.getNearbyEntities(((MonsterView)e1.getView()).getX(), ((MonsterView)e1.getView()).getY());
            for (Controller e2 : nearbyEntities) {
                if (e1 != e2 && e2 instanceof MonsterController && ((Monster)e1.getModel()).collidesWith((Monster)e2.getModel())) {
                    ((Monster)e1.getModel()).applyCollisionForce((Monster)e2.getModel());
                }
            }
        }

        // Collision entre les rochers et les autres entités
        for (RockController rock : rocks) {
            if (((Rock)rock.getModel()).collidesWith(((Player)playerController.getModel()))) {
                ((Rock)rock.getModel()).applyCollisionForce(((Player)playerController.getModel()));
            }
            for (MonsterController monster : monsters) {
                if (((Rock)rock.getModel()).collidesWith((Monster)monster.getModel())) {
                    ((Rock)rock.getModel()).applyCollisionForce(((Monster)monster.getModel()));
                }
            }
        }

        for (BallController ball : getPlayer().getBalls()) {
            grid.add(ball);
        }
    }

    public void drawPlayerAim() {
        int x = getPlayer().getAim()[0];
        int y = getPlayer().getAim()[1];
        float playerX = getPlayerView().getX();
        float playerY = getPlayerView().getY();
        float radius = getPlayerView().getRadius();
        float angle = (float) Math.atan2(y - playerY, x - playerX);
        float endX = playerX + radius * (float) Math.cos(angle);
        float endY = playerY + radius * (float) Math.sin(angle);
        shapeRenderer.line(endX, endY, endX + 50 * (float) Math.cos(angle), endY + 50 * (float) Math.sin(angle));
    }

    /**
     * Draw the rocks
     */
    public void drawRocks() {
        for (RockController rock : rocks) {
            ((RockView)rock.getView()).render(batch);
        }
    }

    public void drawBalls() {
        for (BallController ball : getPlayer().getBalls()) {
            System.out.println("Drawing ball");
            ((BallView)ball.getView()).render(batch);
        }
    }

    /**
     * Draw the monsters
     */
    public void drawMonsters() {
        for (MonsterController monster : monsters) {
            monster.updateMonsterPosition();
            ((MonsterView)monster.getView()).render(batch);
        }
    }

    /**
     * Draw the hitbox and path of the monsters
     */
    public void drawMonstersDebug() {
        shapeRenderer.setColor(Color.GREEN);
        for (RockController rock : rocks) {
            ((Rock)rock.getModel()).renderHitbox(shapeRenderer);
        }
        shapeRenderer.setColor(Color.RED);
        for (MonsterController monster : monsters) {
            ((MonsterView)monster.getView()).renderHitbox(shapeRenderer);

            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.line(getPlayerView().getX(), getPlayerView().getY(), ((MonsterView)monster.getView()).getX(), ((MonsterView)monster.getView()).getY());
        }
    }

    /**
     * Draw the background
     */
    public void drawBackground() {
        float cameraX = cameraController.getCamera().position.x - cameraController.getCamera().viewportWidth / 2;
        float cameraY = cameraController.getCamera().position.y - cameraController.getCamera().viewportHeight / 2;
        float zoom = cameraController.getCamera().zoom;

        float tileWidth = backgroundRegion.getRegionWidth() * 0.1f;
        float tileHeight = backgroundRegion.getRegionHeight() * 0.1f;

        float playerX = getPlayerView().getX() + ((Player)playerController.getModel()).getRadius();
        float playerY = getPlayerView().getY() + ((Player)playerController.getModel()).getRadius();

        float startX = playerX - (playerX % tileWidth) - tileWidth;
        float startY = playerY - (playerY % tileHeight) - tileHeight;

        int additionalTiles = (int) (2 * zoom);
        int tilesX = (int) Math.ceil(cameraController.getCamera().viewportWidth / (tileWidth * zoom)) + additionalTiles;
        int tilesY = (int) Math.ceil(cameraController.getCamera().viewportHeight / (tileHeight * zoom)) + additionalTiles;

        for (int x = -tilesX; x <= tilesX+1; x++) {
            for (int y = -tilesY; y <= tilesY+1; y++) {
                float drawX = startX + x * tileWidth;
                float drawY = startY + y * tileHeight;
                batch.draw(backgroundRegion, drawX, drawY, tileWidth, tileHeight);
            }
        }
    }

    /**
     * Resize the game screen
     * @param width Width
     * @param height Height
     */
    @Override
    public void resize(int width, int height) {
        cameraController.getCamera().viewportWidth = width;
        cameraController.getCamera().viewportHeight = height;
        cameraController.getCamera().update();
        stage.getViewport().update(width, height, true);
    }

    /**
     * Pause the game screen
     */
    @Override
    public void pause() {}

    /**
     * Resume the game screen
     */
    @Override
    public void resume() {}

    /**
     * Hide the game screen
     */
    @Override
    public void hide() {
        inputHandler.clearPressedKeys();
    }

    /**
     * Dispose the game screen
     */
    @Override
    public void dispose() {
        batch.dispose();
        pauseFont.dispose();
        gameUI.dispose();
        stage.dispose();
        skin.dispose();
    }

    /**
     * Getters
     */
    public Main getGame() {return game;}
    public Player getPlayer() {return ((Player)playerController.getModel());}
    public PlayerView getPlayerView() {return ((PlayerView)playerController.getView());}
    public CameraController getCameraController() {return cameraController;}
}