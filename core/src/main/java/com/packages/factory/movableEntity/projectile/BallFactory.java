package com.packages.factory.movableEntity.projectile;

import com.packages.controller.CameraController;
import com.packages.controller.entity.movableEntity.projectile.BallController;
import com.packages.model.entity.movableEntity.projectile.Ball;
import com.packages.view.entity.movableEntity.projectile.BallView;

public class BallFactory implements ProjectileFactory {

    private CameraController cameraController;

    public BallFactory(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    @Override
    public BallView createView(Ball ball) {
        return new BallView("assets/ball.png", ball, cameraController);
    }

    @Override
    public Ball createModel(float x, float y, float size, float speed, int damage, float maxDistance, int directionX, int directionY) {
        return new Ball(x, y, size, speed, damage, maxDistance, directionX, directionY);
    }

    @Override
    public BallController createController(Ball ball, BallView ballView) {
        return new BallController(ball, ballView);
    }
}
