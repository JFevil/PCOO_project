package com.packages.factory.movableEntity.projectile;

import com.packages.controller.entity.movableEntity.projectile.BallController;
import com.packages.model.entity.movableEntity.projectile.Ball;
import com.packages.view.entity.movableEntity.projectile.BallView;

public class ProjectileShooter {
    public static BallController createProjectile(ProjectileFactory projectileFactory, float x, float y, float size, float speed, int damage, float maxDistance, int directionX, int directionY) {
        Ball model = projectileFactory.createModel(x, y, size, speed, damage, maxDistance, directionX, directionY);
        BallView view = projectileFactory.createView(model);
        return projectileFactory.createController(model, view);
    }
}
