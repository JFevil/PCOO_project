package com.packages.model.entity.movableEntity.player;

import com.packages.model.entity.movableEntity.MovableEntity;

public class Player extends MovableEntity {

	int exp;

	public Player(int x, int y, int width, int height, float speed, int health, int damage) {
		super(x, y, width, height, speed, health, damage);
		this.exp = 0;
    }
}

