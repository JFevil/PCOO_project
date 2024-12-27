package com.packages.controller.entity.player;

import com.packages.controller.Controller;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.player.PlayerView;

public class PlayerController implements Controller {

    private Player player;
    private PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView) {
        this.player = player;
        this.playerView = playerView;
    }

    public void move(float dx, float dy) {
        player.move(dx, dy);
    }

    public Player getPlayer() {
        return player;
    }
}
