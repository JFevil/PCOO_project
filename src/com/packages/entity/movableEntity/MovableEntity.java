package com.packages.entity.movableEntity;
import com.packages.entity.Entity;

public abstract class MovableEntity extends Entity implements Movable {
    public MovableEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move(int dx, int dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
}
