package com.packages.view.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.packages.controller.CameraController;
import com.packages.model.entity.Entity;

public class RockView extends EntityView {

    public RockView(Entity entity, CameraController cameraController) {
        super("assets/Rocks/Objects_separately/Rock4_3_no_shadow.png", entity, cameraController);
        setX(entity.getX());
        setY(entity.getY());
        setRadius(entity.getRadius());
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), getX() - getRadius(), getY() - getRadius(), getRadius() * 2, getRadius() * 2);
    }
}
