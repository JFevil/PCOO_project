package com.packages.model.entity;

import com.badlogic.gdx.math.Circle;
import com.packages.model.Model;
import com.packages.utils.Observable;

public abstract class Entity extends Model implements Observable {

    private Circle hitbox;

    public Entity(float x, float y, float radius) {
        super();
        hitbox = new Circle(x, y, radius/1.5f);
    }

    public boolean collidesWith(Entity other) {
        return getHitbox().overlaps(other.getHitbox());
    }

    public void applyCollisionForce(Entity other) {
        float overlapX = getHitbox().x - other.getHitbox().x;
        float overlapY = getHitbox().y - other.getHitbox().y;

        float distance = (float) Math.sqrt(overlapX * overlapX + overlapY * overlapY);

        // Eviter la division par 0
        if (distance == 0) {
            distance = 0.1f;
        }

        float minDistance = getHitbox().radius + other.getHitbox().radius;
        float forceX = overlapX / distance;
        float forceY = overlapY / distance;

        other.setX(other.getX() - forceX * (minDistance - distance) / 2);
        other.setY(other.getY() - forceY * (minDistance - distance) / 2);
    }

    public float getX() { return hitbox.x; }
    public float getY() { return hitbox.y; }
    public float getRadius() { return hitbox.radius; }
    public Circle getHitbox() { return hitbox; }

    public void setX(float x) { hitbox.x = x; notifyObservers(); }
    public void setY(float y) { hitbox.y = y; notifyObservers(); }
    public void setRadius(float radius) { hitbox.radius = radius; notifyObservers(); }
    public void setHitbox(Circle hitbox) { this.hitbox = hitbox; notifyObservers(); }
}