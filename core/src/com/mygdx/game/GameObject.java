package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public abstract class GameObject {
    protected String name;
    protected Vector3 position;
    public ModelInstance modelInstance;

    public GameObject(String name, Vector3 position, ModelInstance modelInstance) {
        this.name = name;
        this.position = position;
        this.modelInstance = modelInstance;
    }

    public abstract void update(float deltaTime);




}