package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class DrawableObject {
    // Właściwości wspólne dla wszystkich obiektów gry
    protected Vector3 position;
    protected float rotation;
    protected float scale;

    // Środowisko renderowania (może być wspólne dla wszystkich obiektów)
    protected Environment environment;

    // Konstruktor
    public DrawableObject() {
        position = new Vector3();
        rotation = 0f;
        scale = 1f;
        // Zainicjalizuj środowisko renderowania (możesz dostosować to do swoich potrzeb)
        environment = new Environment();
    }

    public abstract void draw(ModelBatch modelBatch);

}

