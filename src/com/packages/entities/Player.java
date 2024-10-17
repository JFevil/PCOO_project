package com.mypackage
import com.packages.entities.Entities

public class Player extends Entities {

	int pv;
	int attack;
	int speed;

	public Player(int pv, int attack, int speed) {
		this.pv = pv;
		this.attack = attack;
		this.speed = speed;
	}

}