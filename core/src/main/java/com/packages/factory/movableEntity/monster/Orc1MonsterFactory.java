package com.packages.factory.movableEntity.monster;

import com.packages.controller.CameraController;
import com.packages.controller.entity.movableEntity.monster.MonsterController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.view.entity.movableEntity.monster.Orc1View;
import com.packages.view.entity.movableEntity.monster.MonsterView;

public class Orc1MonsterFactory implements MonsterFactory {

    private CameraController cameraController;

    public Orc1MonsterFactory(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    @Override
    public MonsterView createView(Monster monster) {
        return new Orc1View(monster, cameraController);
    }

    @Override
    public Monster createModel(float x, float y, float speed, Player player) {
        return new Monster(x, y, 50, speed, 50, 5, player);
    }

    @Override
    public MonsterController createController(Monster model, MonsterView view) {
        return new MonsterController(model, view);
    }
}