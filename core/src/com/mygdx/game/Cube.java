package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

import java.awt.*;

public class Cube extends PrimitiveObject {
    private static ModelInstance modelInstance;

    public Cube(float size, Color color) {
        super(new float[]{
                -size / 2, -size / 2, -size / 2,
                size / 2, -size / 2, -size / 2,
                size / 2, size / 2, -size / 2,
                // ... (dodaj resztę wierzchołków sześcianu)
        }, new short[]{
                0, 1, 2,
                // ... (dodaj resztę indeksów sześcianu)
        }, color, modelInstance);
    }
}
