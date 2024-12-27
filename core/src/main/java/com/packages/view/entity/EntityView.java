package com.packages.view.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.packages.GameParameters;
import com.packages.controller.CameraController;
import com.packages.model.entity.Entity;
import com.packages.utils.Observer;

public abstract class EntityView implements Observer {

    Texture texture;
    private float x, y, width, height;
    private ShapeRenderer shapeRenderer;
    private CameraController cameraController;

    public EntityView(String fileName, Entity entity, CameraController cameraController) {
        this.texture = TextureFactory.getTexture(fileName);
        this.cameraController = cameraController;
        entity.addObserver(this);
        x = entity.getX();
        y = entity.getY();
        width = entity.getWidth();
        height = entity.getHeight();
        shapeRenderer = new ShapeRenderer();
    }

    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cameraController.getCamera().combined);
        batch.draw(texture, x, y, width, height);
        if (GameParameters.getInstance().isDebugMode()) {
            renderDebug();
        }
    }

    public void renderDebug() {
        shapeRenderer.setProjectionMatrix(cameraController.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public void update(Entity entity) {
        setX(entity.getX());
        setY(entity.getY());
        setWidth(entity.getWidth());
        setHeight(entity.getHeight());
    }

    public float getX() { return x;}
    public float getY() { return y;}
    public float getWidth() { return width;}
    public float getHeight() { return height;}
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setWidth(float width) {this.width = width;}
    public void setHeight(float height) {this.height = height;}
}