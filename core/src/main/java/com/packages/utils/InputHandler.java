package com.packages.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.packages.controller.CameraController;
import com.packages.model.entity.movableEntity.player.Player;
import com.packages.GameScreen;

import java.util.HashSet;
import java.util.Set;

public class InputHandler implements InputProcessor {
    private Player player;
    private Set<Integer> pressedKeys;
    CameraController cameraController;
    GameScreen gameScreen;

    public InputHandler(GameScreen gameScreen) {
        this.player = gameScreen.getPlayer();
        this.pressedKeys = new HashSet<>();
        this.cameraController = gameScreen.getCameraController();
        this.gameScreen = gameScreen;
        Gdx.input.setInputProcessor(this);
    }

    public void handleInput() {
        float dx = 0;
        float dy = 0;
        float delta = Gdx.graphics.getDeltaTime();
        int cursorX = (int) (cameraController.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).x);
        int cursorY = (int) (cameraController.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)).y);

        player.setAim(cursorX, cursorY);

        if (pressedKeys.contains(Input.Keys.RIGHT) || pressedKeys.contains(Input.Keys.D)) {
            dx += delta * player.getSpeed();
        }
        if (pressedKeys.contains(Input.Keys.LEFT) || pressedKeys.contains(Input.Keys.A)) {
            dx -= delta * player.getSpeed();
        }
        if (pressedKeys.contains(Input.Keys.UP) || pressedKeys.contains(Input.Keys.W)) {
            dy += delta * player.getSpeed();
        }
        if (pressedKeys.contains(Input.Keys.DOWN) || pressedKeys.contains(Input.Keys.S)) {
            dy -= delta * player.getSpeed();
        }

        player.move(dx, dy);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            player.shoot();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            GameParameters.getInstance().setShowHitbox(!GameParameters.getInstance().isShowHitbox());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
            GameParameters.getInstance().setShowPlayerAim(!GameParameters.getInstance().isShowPlayerAim());
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            if (GameParameters.getInstance().isPaused()) {
                gameScreen.getGame().resumeGame();
            } else {
                gameScreen.getGame().pauseGame();
            }
        }

        // Check for multi-key shortcuts
//        if (isShortcutPressed(Input.Keys.CONTROL_LEFT, Input.Keys.Z)) {
//            performUndo();
//        }
//        if (isShortcutPressed(Input.Keys.CONTROL_LEFT, Input.Keys.SHIFT_LEFT, Input.Keys.S)) {
//            performSaveAs();
//        }
    }

    private boolean isShortcutPressed(int... keys) {
        for (int key : keys) {
            if (!pressedKeys.contains(key)) {
                return false;
            }
        }
        return true;
    }

    private void performUndo() {
        System.out.println("Undo action performed");
    }

    private void performSaveAs() {
        System.out.println("Save as action performed");
    }

    @Override
    public boolean keyDown(int keycode) {
        pressedKeys.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        pressedKeys.remove(keycode);
            return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY > 0) {
            cameraController.zoomOut(0.1f);
        } else if (amountY < 0) {
            cameraController.zoomIn(0.1f);
        }
        return true;
    }

    public void clearPressedKeys() {
        pressedKeys.clear();
    }
}