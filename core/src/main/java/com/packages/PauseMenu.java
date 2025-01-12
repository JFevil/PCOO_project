package com.packages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class PauseMenu implements Screen {
    private final Main onResume;
    private Stage stage;
    private Skin skin;
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean initialized = false;

    public PauseMenu(Main onResume) {
        this.onResume = onResume;
        initialize();
    }

    private void initialize() {
        // Initialisation de la scène et des éléments graphiques
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/craftacular/skin/craftacular-ui.json")); // Assurez-vous d'avoir un fichier uiskin.json valide
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("skin/craftacular/raw/font-title-export.fnt"));

        Gdx.input.setInputProcessor(stage);

        // Création du bouton "Reprendre"
        TextButton resumeButton = new TextButton("Reprendre", skin);
        resumeButton.setSize(200, 50);
        resumeButton.setPosition(Gdx.graphics.getWidth() / 2f - resumeButton.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        // Listener pour le bouton
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                onResume.resumeGame(); // Appelle l'action de reprise passée en paramètre
            }
        });

        // Ajout du bouton à la scène
        stage.addActor(resumeButton);

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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            onResume.resumeGame();
        }

        ScreenUtils.clear(Color.DARK_GRAY);

        stage.act(delta);
        stage.draw();

        // Afficher le titre du menu en haut de l'écran
        batch.begin();
        GlyphLayout layout = new GlyphLayout();
        String title = "PAUSE";
        font.getData().setScale(2.0f);
        layout.setText(font, title);
        font.draw(batch, layout, (Gdx.graphics.getWidth() - layout.width) / 2, Gdx.graphics.getHeight() - 100);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

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