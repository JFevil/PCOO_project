package com.packages.model.entity.movableEntity;

import com.packages.model.entity.Entity;

public abstract class MovableEntity extends Entity {

    private float speed;
    private int health;
    private int damage;
    private boolean alive = true;
    private float reach;

    public MovableEntity(float x, float y, float size, float speed, int health, int damage) {
        super(x, y, size);
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        reach = size * 1.5f;
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

    public void die() {
        setAlive(false);
    }

    public float getSpeed() { return speed; }
    public void setSpeed(float speed) { this.speed = speed; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    public float getReach() { return reach; }
    public void setReach(float reach) { this.reach = reach; }
}
