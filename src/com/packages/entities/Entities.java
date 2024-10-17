package com.packages.entities;

public class Entities {

	private int positionX;
	private int positionY;
	private int hP;
	int attack;
	int speed;
	int resistance;

	public Entities(int positionX, int positionY, int hP, int attack, int speed, int resistance) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.hP = hP;
		this.attack = attack;
		this.speed = speed;
		this.resistance = resistance;
	}

	public void mouv(int mouvX, int mouvY) {
		this.positionX += mouvX;
		this.positionY += mouvY;
	}

}