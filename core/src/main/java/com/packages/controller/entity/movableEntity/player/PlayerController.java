package com.packages.controller.entity.movableEntity.player;

import com.packages.controller.Controller;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.movableEntity.player.PlayerView;

public class PlayerController extends Controller {

    private Player player;
    private PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView) {
        super(player, playerView);
    }

    public void move(float dx, float dy) {
        player.move(dx, dy);
    }

    public void shoot() {
        player.shoot();
    }
}
