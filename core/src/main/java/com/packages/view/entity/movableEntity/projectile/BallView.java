package com.packages.view.entity.movableEntity.projectile;

import com.packages.controller.CameraController;
import com.packages.model.entity.movableEntity.projectile.Ball;
import com.packages.view.entity.EntityView;

public class BallView extends EntityView {

        public BallView(String fileName, Ball ball, CameraController cameraController) {
            super(fileName, ball, cameraController);
        }
}
