package com.packages.entities;

public class Monster extends UpdatableEntities {

	public Monster (int positionX, int positionY, int hP, int attack, int speed, int resistance) {
		super(positionX, positionY, hP, attack, speed, resistance, 0);
	}
}