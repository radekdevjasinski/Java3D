package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;

public abstract class PrimitiveObject {
    private ModelInstance modelInstance;

    public PrimitiveObject(Model model, Color color) {
        modelInstance = new ModelInstance(model);
        Material material = modelInstance.materials.get(0);
        material.set(ColorAttribute.createDiffuse(color));
    }

    public PrimitiveObject(float[] floats, short[] shorts, java.awt.Color color, ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    public PrimitiveObject(java.awt.Color color) {
    }

    public void dispose() {
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }

    public abstract void draw();

    public abstract void translate(float x, float y, float z);

    public abstract void rotate(float angle, float axisX, float axisY, float axisZ);

    public abstract void scale(float scaleX, float scaleY, float scaleZ);
}


