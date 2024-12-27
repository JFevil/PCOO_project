package com.packages.view.entity.monster;

import com.packages.controller.CameraController;
import com.packages.model.entity.movableEntity.monster.Monster;

public class Orc1View extends MonsterView {

    public Orc1View(Monster monster, CameraController cameraController) {
        super("assets/cutOrc1/tile000.png", monster, cameraController);
    }
}
