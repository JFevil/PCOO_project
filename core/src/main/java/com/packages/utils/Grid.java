package com.packages.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.packages.controller.Controller;
import com.packages.view.entity.EntityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    private final int cellSize;
    private final Map<String, List<Controller>> cells;

    public Grid(int cellSize) {
        this.cellSize = cellSize;
        this.cells = new HashMap<>();
    }

    private String getCellKey(int x, int y) {
        return x + "," + y;
    }

    private int getCellIndex(float coordinate) {
        return (int) (coordinate / cellSize);
    }

    public void add(Controller entity) {
        int x = getCellIndex(((EntityView)entity.getView()).getX());
        int y = getCellIndex(((EntityView)entity.getView()).getY());
        String key = getCellKey(x, y);
        cells.computeIfAbsent(key, k -> new ArrayList<>()).add(entity);
    }

    public void remove(Controller entity) {
        int x = getCellIndex(((EntityView)entity.getView()).getX());
        int y = getCellIndex(((EntityView)entity.getView()).getY());
        String key = getCellKey(x, y);
        List<Controller> cell = cells.get(key);
        if (cell != null) {
            cell.remove(entity);
            if (cell.isEmpty()) {
                cells.remove(key);
            }
        }
    }

    public List<Controller> getNearbyEntities(float x, float y) {
        int cellX = getCellIndex(x);
        int cellY = getCellIndex(y);
        List<Controller> nearbyEntities = new ArrayList<>();
        for (int i = cellX - 1; i <= cellX + 1; i++) {
            for (int j = cellY - 1; j <= cellY + 1; j++) {
                String key = getCellKey(i, j);
                List<Controller> cell = cells.get(key);
                if (cell != null) {
                    nearbyEntities.addAll(cell);
                }
            }
        }
        return nearbyEntities;
    }

    public void clear() {
        cells.clear();
    }

    public void renderDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.PURPLE);
        for (String key : cells.keySet()) {
            String[] parts = key.split(",");
            int x = Integer.parseInt(parts[0]) * cellSize;
            int y = Integer.parseInt(parts[1]) * cellSize;
            shapeRenderer.rect(x, y, cellSize, cellSize);
        }
    }
}