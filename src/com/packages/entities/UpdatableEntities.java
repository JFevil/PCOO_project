package com.packages.entities;

public class UpdatableEntities extends Entities implements Updatable {

    public UpdatableEntities(int positionX, int positionY, int hP, int attack, int speed, int resistance, double orientation) {
        super(positionX, positionY, hP, attack, speed, resistance, orientation);
    }

    @Override
    public void update() {

    }
}
