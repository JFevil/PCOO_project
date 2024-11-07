package com.packages.map;
import com.packages.entity.Entity;

public class Map {

	int sizeX;
	int sizeY;
	Entity[][] grid;

	public Map(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.grid = new Entity[sizeY][sizeX];
	}

}