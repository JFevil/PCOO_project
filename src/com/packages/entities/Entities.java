package com.packages.entities;

public abstract class Entities {

	private int positionX;
	private int positionY;
	private int hP;
	int attack;
	int speed;
	int resistance;
	double orientation;

	public Entities(int positionX, int positionY, int hP, int attack, int speed, int resistance, double orientation) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.hP = hP;
		this.attack = attack;
		this.speed = speed;
		this.resistance = resistance;
		this.orientation = orientation;
	}

	private int trajectory() {
		return 0;
	}

	public void mouv(int mouvX, int mouvY) {
		this.positionX += mouvX;
		this.positionY += mouvY;
	}

}