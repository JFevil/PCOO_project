package com.packages.view.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.packages.controller.CameraController;
import com.packages.model.Model;
import com.packages.model.entity.Entity;
import com.packages.view.View;

public abstract class EntityView extends View {

    private float x, y, radius;

    public EntityView(String fileName, Entity entity, CameraController cameraController) {
        super(fileName, entity, cameraController);
        this.radius = entity.getRadius();
    }

    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), x - radius*2, y - radius*2, radius * 4, radius * 4);
    }

    public void renderHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(x, y, radius);
    }

    public void update(Model model) {
        this.x = ((Entity)model).getX();
        this.y = ((Entity)model).getY();
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getRadius() { return radius; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setRadius(float radius) { this.radius = radius; }
}