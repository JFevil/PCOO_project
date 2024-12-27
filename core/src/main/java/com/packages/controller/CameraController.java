package com.packages.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraController {
    private OrthographicCamera camera;

    public CameraController(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.position.set(viewportWidth / 2, viewportHeight / 2, 0);
        camera.update();
    }

    public void update(float deltaTime) {
        camera.update();
    }

    public void follow(float x, float y) {
        camera.position.set(x, y, 0);
        camera.update();
    }

    public void zoomIn(float amount) {
        camera.zoom = Math.max(camera.zoom - amount, 0.1f); // Prevent zooming too far in
        camera.update();
    }

    public void zoomOut(float amount) {
        camera.zoom = Math.min(camera.zoom + amount, 10f); // Prevent zooming too far out
        camera.update();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}