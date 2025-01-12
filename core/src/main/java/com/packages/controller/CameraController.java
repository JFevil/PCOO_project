package com.packages.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public class CameraController {
    private OrthographicCamera camera;
    private float targetZoom;
    private float zoomSpeed = 3f;

    public CameraController(float viewportWidth, float viewportHeight) {
        camera = new OrthographicCamera(viewportWidth, viewportHeight);
        camera.position.set(viewportWidth / 2, viewportHeight / 2, 0);
        camera.update();
        targetZoom = camera.zoom;
    }

    public void update(float deltaTime) {
        // Smoothly interpolate the camera's zoom level towards the target zoom level
        camera.zoom = MathUtils.lerp(camera.zoom, targetZoom, zoomSpeed * deltaTime);
        camera.update();
    }

    public void follow(float x, float y) {
        camera.position.set(x, y, 0);
        camera.update();
    }

    public void zoomIn(float amount) {
        targetZoom = Math.max(targetZoom - amount, 0.5f); // Prevent zooming too far in
    }

    public void zoomOut(float amount) {
        targetZoom = Math.min(targetZoom + amount, 1.5f); // Prevent zooming too far out
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}