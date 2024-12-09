package com.packages.controller.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.packages.model.entity.movableEntity.player.Player;

public class PlayerController {

    private Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void move(float dx, float dy) {
        player.move(dx, dy);
    }

    public void input() {
        float dx = 0;
        float dy = 0;
        float delta = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) dx += delta * player.getSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) dx -= delta * player.getSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) dy += delta * player.getSpeed();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) dy -= delta * player.getSpeed();
        player.move(dx, dy);
    }
}
