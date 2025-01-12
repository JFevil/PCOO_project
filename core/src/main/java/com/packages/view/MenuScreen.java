package com.packages.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.ScreenUtils;
import com.packages.Main;

public class MenuScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean initialized = false;

    public MenuScreen(Main game) {
        this.game = game;
    }

    private void initialize() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("skin/craftacular/raw/font-title-export.fnt"));
        font.setColor(Color.WHITE);
        font.getData().setScale(1);

        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/craftacular/skin/craftacular-ui.json"));
        Gdx.input.setInputProcessor(stage);

        TextButton startButton = new TextButton("Commencer", skin);
        startButton.setSize(200, 50);
        startButton.setPosition(Gdx.graphics.getWidth() / 2f - startButton.getWidth() / 2f, 300);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.resumeGame();
            }
        });

        TextButton quitButton = new TextButton("Quitter", skin);
        quitButton.setSize(200, 50);
        quitButton.setPosition(Gdx.graphics.getWidth() / 2f - quitButton.getWidth() / 2f, 200);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(startButton);
        stage.addActor(quitButton);

        initialized = true;
    }

    @Override
    public void show() {
        if (!initialized) {
            initialize();
        }
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);

        batch.begin();
        GlyphLayout layout = new GlyphLayout();
        String title = "Bienvenue dans le jeu !";
        layout.setText(font, title);
        font.draw(batch, layout, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight()-100);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        font.dispose();
    }
}