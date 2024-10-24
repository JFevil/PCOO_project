package com.packages.entities;

public class Player extends Entities {

	int xp;

	public Player(int positionX, int positionY, int hP, int attack, int speed, int resistance, double orientation) {
		super(positionX, positionY, hP, attack, speed, resistance, 0);
		this.xp = 0;
	}
}