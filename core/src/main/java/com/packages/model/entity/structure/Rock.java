package com.packages.model.entity.structure;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.packages.model.entity.Entity;

public class Rock extends Entity {

    public Rock(float x, float y, float radius) {
        super(x, y, radius);
    }

    public Rock(Circle cirle) {
        super(cirle.x, cirle.y, cirle.radius);
    }

    @Override
    public String toString() {
        return "Rock{" + getHitbox() + '}';
    }

    public void renderHitbox(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(getHitbox().x, getHitbox().y, getHitbox().radius);
    }
}
