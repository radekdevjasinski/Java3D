package com.mygdx.game;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
public abstract class PhysicalObject extends GameObject implements Updatable {
    private static ModelInstance modelInstance;
    protected Vector3 velocity;

    public PhysicalObject(String name, Vector3 position, Vector3 velocity) {
        super(name, position, modelInstance);
        this.velocity = velocity;
    }

    @Override
    public void update(float deltaTime) {
        position.add(velocity.x * deltaTime, velocity.y * deltaTime, velocity.z * deltaTime);
    }
}