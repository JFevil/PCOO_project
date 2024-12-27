package com.packages.view.entity.monster;

import com.packages.controller.CameraController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.EntityView;

public class MonsterView extends EntityView {

    public MonsterView(String fileName, Monster monster, CameraController cameraController) {
        super(fileName, monster, cameraController);
    }
}
