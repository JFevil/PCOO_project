package com.packages.factory.movableEntity.projectile;

import com.packages.controller.entity.movableEntity.projectile.BallController;
import com.packages.model.entity.movableEntity.projectile.Ball;
import com.packages.view.entity.movableEntity.projectile.BallView;

public interface ProjectileFactory {
    BallView createView(Ball ball);
    Ball createModel(float x, float y, float size, float speed, int damage, float maxDistance, int directionX, int directionY);
    BallController createController(Ball ball, BallView ballView);
}
