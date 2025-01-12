package com.packages.model;

import com.packages.utils.Observable;
import com.packages.utils.Observer;

import java.util.List;
import java.util.ArrayList;

public abstract class Model implements Observable {

    private List<Observer> observers;

    public Model() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}