package com.packages.view;

import com.badlogic.gdx.graphics.Texture;
import com.packages.controller.CameraController;
import com.packages.model.Model;
import com.packages.utils.Observer;
import com.packages.utils.TextureFactory;

public abstract class View implements Observer {

    private Texture texture;
    private CameraController cameraController;

    public View(String fileName, Model model, CameraController cameraController) {
        this.texture = TextureFactory.getTexture(fileName);
        this.cameraController = cameraController;
        model.addObserver(this);
    }

    public Texture getTexture() {
        return texture;
    }
    public void setTexture(Texture texture) {this.texture = texture;}
}
