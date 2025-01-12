package com.packages.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.packages.model.Model;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.utils.Observer;

public class GameUI implements Observer {
    private Stage stage;
    private Skin skin;
    private Label healthLabel;
    private int health;

    public GameUI() {
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("skin/craftacular/skin/craftacular-ui.json"));

        BitmapFont font = new BitmapFont();
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.YELLOW);

        healthLabel = new Label("Health: " + health, labelStyle);

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(healthLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void render() {
        healthLabel.setText("Health: " + health);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void update(Model model) {
        health = ((Player) model).getHealth();
    }
}