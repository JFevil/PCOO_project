package com.packages.entity.movableEntity.player;

import com.packages.entity.movableEntity.MovableEntity;

public class Player extends MovableEntity {

	int xp;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.xp = 0;
	}

	public void mouv() {

	}
}