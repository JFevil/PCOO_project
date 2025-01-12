package com.packages.model.entity.movableEntity.monster;

import com.badlogic.gdx.Gdx;
import com.packages.model.entity.movableEntity.MovableEntity;
import com.packages.model.entity.movableEntity.player.Player;
import java.lang.Math;

public class Monster extends MovableEntity {

    private Player player;
    private static int mobCap = 0;

    public Monster(float x, float y, float radius, float speed, int health, int damage, Player player) {
        super(x, y, radius, speed, health, damage);
        this.player = player;
        mobCap++;
    }

    public static int getMobCap() { return mobCap; }
    public static void setMobCap(int mobCap) { Monster.mobCap = mobCap; }
    public void die() {
        setAlive(false);
        setMobCap(getMobCap() - 1);
    }

    public void move() {
        float delta = Gdx.graphics.getDeltaTime();
        double playerX = player.getX();
        double playerY = player.getY();
        double directionX = playerX - getX();
        double directionY = playerY - getY();
        double distance = Math.sqrt(directionX * directionX + directionY * directionY);
        if (distance > 0) {
            directionX /= distance;
            directionY /= distance;
            float new_speed = (float) Math.min(getSpeed() * delta, distance);
            float dx = (float) (directionX * new_speed);
            float dy = (float) (directionY * new_speed);
            super.move(dx, dy);
        }
    }
}