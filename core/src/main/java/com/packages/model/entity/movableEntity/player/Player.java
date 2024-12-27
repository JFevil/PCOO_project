package com.packages.model.entity.movableEntity.player;

import com.packages.model.entity.movableEntity.MovableEntity;

public class Player extends MovableEntity {

	int exp;

	public Player(float x, float y, float width, float height, float speed, int health, int damage) {
		super(x, y, width, height, speed, health, damage);
		this.exp = 0;
    }
}

