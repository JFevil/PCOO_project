package com.packages.model.entity.movableEntity;

import com.packages.model.entity.Entity;
import static com.packages.model.entity.movableEntity.Orientation.*;

public abstract class MovableEntity extends Entity {

    private float speed;
    private Orientation orientation;
    private int health;
    private int damage;
    private boolean alive = true;

    public MovableEntity(float x, float y, float width, float height, float speed, int health, int damage) {
        super(x, y, width, height);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        orientation = FRONT;
    }

    public void move(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
        notifyObservers();
    }

    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        notifyObservers();
    }

    public void setOrientation(Orientation orientation) { this.orientation = orientation;}
    public Orientation getOrientation() { return orientation; }
    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
}
