package com.packages.view.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.packages.model.entity.Entity;
import com.packages.utils.Observer;

public abstract class EntityView implements Observer {

    Texture texture;
    Entity entity;
    private float x, y;

    public EntityView(String fileName, Entity entity) {
        this.texture = new Texture(fileName);
        this.entity = entity;
        entity.addObserver(this);
        x = entity.getX();
        y = entity.getY();
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    @Override
    public void update(Entity entity) {
        setX(entity.getX());
        setY(entity.getY());
    }

    public float getX() { return x;}
    public float getY() { return y;}
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
}
