package com.mygdx.game;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
public class MeshObject extends GameObject implements Renderable {
    private static ModelInstance modelInstance;
    private Mesh mesh;

    public MeshObject(String name, Vector3 position, Mesh mesh) {
        super(name, position, modelInstance);
        this.mesh = mesh;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render() {
    }
}