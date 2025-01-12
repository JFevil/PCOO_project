package com.packages.view;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

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

    private static Texture filterNonTransparentPixels(Texture texture) {
        TextureData textureData = texture.getTextureData();
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        Pixmap pixmap = textureData.consumePixmap();

        int width = pixmap.getWidth();
        int height = pixmap.getHeight();

        int minX = width, minY = height, maxX = 0, maxY = 0;
        boolean foundNonTransparent = false;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = pixmap.getPixel(x, y);
                int alpha = ((pixel >> 24) & 0xff);

                if (alpha != 0) {
                    foundNonTransparent = true;
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        if (!foundNonTransparent) {
            pixmap.dispose();
            return texture; // Return the original texture if no non-transparent pixels are found
        }

        int croppedWidth = maxX - minX + 1;
        int croppedHeight = maxY - minY + 1;
        System.out.println("Cropped width: " + croppedWidth + ", cropped height: " + croppedHeight);
        Pixmap croppedPixmap = new Pixmap(croppedWidth, croppedHeight, pixmap.getFormat());

        for (int y = 0; y < croppedHeight; y++) {
            for (int x = 0; x < croppedWidth; x++) {
                int pixel = pixmap.getPixel(minX + x, minY + y);
                croppedPixmap.drawPixel(x, y, pixel);
            }
        }

        Texture croppedTexture = new Texture(croppedPixmap);
        pixmap.dispose();
        croppedPixmap.dispose();
        return croppedTexture;
    }

    public static void dispose() {
        for (Texture texture : textureMap.values()) {
            texture.dispose();
        }
        textureMap.clear();
    }
}