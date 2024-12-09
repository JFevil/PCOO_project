package com.packages.view.entity.player;
import com.packages.view.entity.EntityView;
import com.packages.model.entity.movableEntity.player.Player;

public class PlayerView extends EntityView {
    public PlayerView(Player player) {
        super("assets/cutPlayer/tile000.png", player);
    }
}
