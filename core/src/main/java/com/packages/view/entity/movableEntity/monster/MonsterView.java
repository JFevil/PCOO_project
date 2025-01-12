package com.packages.view.entity.movableEntity.monster;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.packages.controller.CameraController;
import com.packages.model.entity.movableEntity.monster.Monster;
import com.packages.view.entity.EntityView;

public class MonsterView extends EntityView {
    public MonsterView(String fileName, Monster monster, CameraController cameraController) {
        super(fileName, monster, cameraController);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), getX() - getRadius()*1.5f, getY() - getRadius()*1.5f, getRadius() * 3, getRadius() * 3);
    }
}