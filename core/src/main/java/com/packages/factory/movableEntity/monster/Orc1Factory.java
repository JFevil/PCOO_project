package com.packages.factory.movableEntity.monster;

import com.packages.controller.CameraController;
import com.packages.controller.entity.monster.MonsterController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.monster.MonsterView;

public class Orc1Factory implements Factory {

    private CameraController cameraController;

    public Orc1Factory(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    @Override
    public MonsterView createView(Monster monster) {
        return new MonsterView("assets/cutOrc1/tile000.png", monster, cameraController);
    }

    @Override
    public Monster createModel(float x, float y, float speed, Player player) {
        return new Monster(x, y, 100, 100, speed, 50, 5, player);
    }

    @Override
    public MonsterController createController(Monster model, MonsterView view) {
        return new MonsterController(model, view);
    }
}
