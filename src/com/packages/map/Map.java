package com.packages.map;
import com.packages.entity.Entity;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Map {

	int sizeX;
	int sizeY;
	List<Entity> entities;

	public Map(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.entities = new ArrayList<>();
	}

	public void update() {
        for (Entity entity : this.entities) {

        }
	}

}