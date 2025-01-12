package com.packages.controller.entity.movableEntity.projectile;

import com.packages.controller.Controller;
import com.packages.model.entity.movableEntity.projectile.Ball;
import com.packages.view.entity.movableEntity.projectile.BallView;

public class BallController extends Controller {

    public BallController(Ball ball, BallView ballView) {
        super(ball, ballView);
    }

    public void move() {
        ((Ball)getModel()).move();
    }

    public boolean validDistance() {
        return ((Ball)getModel()).validDistance();
    }
}
