package com.packages.view.entity.player;
import com.packages.controller.CameraController;
import com.packages.view.entity.EntityView;
import com.packages.model.entity.movableEntity.player.Player;

public class PlayerView extends EntityView {
    public PlayerView(Player player, CameraController cameraController) {
        super("assets/cutPlayer/tile000.png", player, cameraController);
    }
}
