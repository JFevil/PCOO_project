package com.packages.map;
import com.packages.entities.Entities;

public class Map {

	int sizeX;
	int sizeY;
	Entities[][] grid;

	public Map(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.grid = new Entities[sizeY][sizeX];
	}

}