package com.packages.model.entity.movableEntity.player;

import com.packages.controller.CameraController;
import com.packages.controller.entity.movableEntity.projectile.BallController;
import com.packages.factory.movableEntity.projectile.ProjectileShooter;
import com.packages.factory.movableEntity.projectile.BallFactory;
import com.packages.model.entity.movableEntity.MovableEntity;

import java.util.ArrayList;
import java.util.List;

public class Player extends MovableEntity {

	private int exp;
	private int[] aim;
	private BallFactory ballFactory;
	private List<BallController> balls;

	public Player(float x, float y, float size, float speed, int health, int damage, CameraController cameraController) {
		super(x, y, size, speed, health, damage);
		this.exp = 0;
		this.aim = new int[]{0, 0};
		this.ballFactory = new BallFactory(cameraController);
		this.balls = new ArrayList<>();
    }

	public void shoot() {
		int aimX = aim[0];
		int aimY = aim[1];
		float playerX = getX();
		float playerY = getY();
		float ballSpeed = 0;
        balls.add(ProjectileShooter.createProjectile(ballFactory, playerX, playerY, 50, ballSpeed, 10, 1000, aimX, aimY));
		System.out.println("Player.shoot() called");
	}

	public void removeBalls(BallController ball) {
		balls.remove(ball);
	}

	public int getExp() {return exp;}
	public int[] getAim() {return aim;}

	public void setExp(int exp) {this.exp = exp;}
	public void setAim(int x, int y) {
		aim[0] = x;
		aim[1] = y;
	}

	public List<BallController> getBalls() {return balls;}
}

