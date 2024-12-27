package com.packages.view.entity;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;
import java.util.Map;

public class TextureFactory {
    private static final Map<String, Texture> textureMap = new HashMap<>();

    public static Texture getTexture(String fileName) {
        Texture texture = textureMap.get(fileName);
        if (texture == null) {
            texture = new Texture(fileName);
            textureMap.put(fileName, texture);
        }
        return texture;
    }

    public static void dispose() {
        for (Texture texture : textureMap.values()) {
            texture.dispose();
        }
        textureMap.clear();
    }
}