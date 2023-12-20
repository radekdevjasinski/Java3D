package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class DrawableObject {
    protected Vector3 position;
    protected float rotation;
    protected float scale;
    protected Environment environment;

    public DrawableObject() {
        position = new Vector3();
        rotation = 0f;
        scale = 1f;
        environment = new Environment();
    }

    public abstract void draw(ModelBatch modelBatch);

}

