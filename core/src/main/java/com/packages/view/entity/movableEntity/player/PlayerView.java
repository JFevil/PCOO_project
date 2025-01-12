package com.packages.view.entity.movableEntity.player;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.packages.controller.CameraController;
import com.packages.model.Model;
import com.packages.view.entity.EntityView;
import com.packages.model.entity.movableEntity.player.Player;

public class PlayerView extends EntityView {

    private int[] aim;

    public PlayerView(Player player, CameraController cameraController) {
        super("assets/cutPlayer/tile000.png", player, cameraController);
        aim = player.getAim();
    }

    @Override
    public void update(Model player) {
        super.update(player);
        aim = ((Player)player).getAim();
    }
}
