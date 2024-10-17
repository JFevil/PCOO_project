package com.mypackage.entities;
import com.packages.entities.Entities;
import com.packages.weapons.Weapons;
import com.packages.equipment.Equipment;

public class Player extends Entities {

	Weapons[] weapons;
	Equipment[] equipment;
	int xp;

	public Player(int positionX, int positionY, int hP, int attack, int speed, int resistance) {
		super(positionX, positionY, hP, attack, speed, resistance);
		this.weapons = new Weapons[6];
		this.equipment = new Equipment[6];
		this.xp = 0;
	}
}