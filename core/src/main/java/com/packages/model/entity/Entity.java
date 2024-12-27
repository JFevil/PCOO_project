package com.packages.model.entity;
import com.badlogic.gdx.math.Rectangle;
import com.packages.model.Model;
import com.packages.utils.Observable;
import com.packages.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends Rectangle implements Observable, Model {
    private List<Observer> observers;

    public Entity(float x, float y, float width, float height) {
        super(x, y, width, height);
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public boolean collidesWith(Entity other) {
        return overlaps(other);
    }
}
