package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class PrimitiveObject {
    private ModelInstance modelInstance;
    public float velocity = 0;
    public PrimitiveObject(Model model, Color color) {
        modelInstance = new ModelInstance(model);
        Material material = modelInstance.materials.get(0);
        material.set(ColorAttribute.createDiffuse(color));
    }
    public PrimitiveObject(Model model, Texture texture) {
        modelInstance = new ModelInstance(model);
        Material material = modelInstance.materials.get(0);
        material.clear(); // Clear existing attributes
        material.set(TextureAttribute.createDiffuse(texture));
    }

    public PrimitiveObject(float[] floats, short[] shorts, java.awt.Color color, ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }

    public PrimitiveObject(java.awt.Color color) {
    }

    public void dispose() {
        // Dispose resources if needed
    }

    public ModelInstance getModelInstance() {
        return modelInstance;
    }
}