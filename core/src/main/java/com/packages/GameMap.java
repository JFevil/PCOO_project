package com.packages;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.packages.controller.CameraController;
import com.packages.controller.entity.RockController;
import com.packages.model.entity.structure.Rock;
import com.packages.view.entity.RockView;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private TiledMap map;
    private List<RockController> rocks;
    private Rectangle playerStartPosition;
    private CameraController cameraController;

    public GameMap(String path, CameraController cameraController) {
        map = new TmxMapLoader().load(path);
        rocks = new ArrayList<>();
        this.cameraController = cameraController;
        extractObjects();
    }

    private void extractObjects() {
        MapObjects objects = map.getLayers().get("Rocks").getObjects();
        for (MapObject object : objects) {
            if (object instanceof TiledMapTileMapObject) {
                TiledMapTileMapObject tileObject = (TiledMapTileMapObject) object;
                Circle circle = new Circle(tileObject.getX(), tileObject.getY(), 50);
                Rock rock = new Rock(circle);
                RockView rockView = new RockView(rock, cameraController);
                RockController rockController = new RockController(rock, rockView);
                rocks.add(rockController);
            }
        }

        objects = map.getLayers().get("Player").getObjects();
        for (MapObject object : objects) {
            if (object instanceof TiledMapTileMapObject) {
                TiledMapTileMapObject tileObject = (TiledMapTileMapObject) object;
                playerStartPosition = new Rectangle(tileObject.getX(), tileObject.getY(), tileObject.getTextureRegion().getRegionWidth(), tileObject.getTextureRegion().getRegionHeight());
            }
        }
    }

    public List<RockController> getRocks() {
        return rocks;
    }

    public Rectangle getPlayerStartPosition() {
        return playerStartPosition;
    }

    public TiledMap getMap() {
        return map;
    }
}