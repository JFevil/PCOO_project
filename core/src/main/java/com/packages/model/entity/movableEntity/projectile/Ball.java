package com.packages.model.entity.movableEntity.projectile;

import com.badlogic.gdx.Gdx;
import com.packages.model.entity.movableEntity.MovableEntity;

public class Ball extends MovableEntity {

    private final int[] startingPosition;
    private int[] direction;
    private float maxDistance;

    public Ball(float x, float y, float size, float speed, int damage, float maxDistance, int directionX, int directionY) {
        super(x, y, size, speed, 1, damage);
        this.direction = new int[]{directionX, directionY};
        this.maxDistance = maxDistance;
        this.startingPosition = new int[]{(int) x, (int) y};
    }

    public void move() {
        float delta = Gdx.graphics.getDeltaTime();
        float dx = getSpeed() * direction[0];
        float dy = getSpeed() * direction[1];
        super.move(dx * delta, dy * delta);
    }

    public int[] getDirection() {return direction;}
    public float getMaxDistance() {return maxDistance;}

    public boolean validDistance() {
        float distance = (float) Math.sqrt(Math.pow(getX() - startingPosition[0], 2) + Math.pow(getY() - startingPosition[1], 2));
        return distance <= maxDistance;
    }
}
