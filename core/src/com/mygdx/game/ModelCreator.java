package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import java.util.ArrayList;
import java.util.List;

public class ModelCreator {
    public static Model createCubeModel(Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(color)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
    public static Model createCubeModel(Texture texture) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(TextureAttribute.createDiffuse(texture));
        return modelBuilder.createBox(5f, 5f, 5f, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }
    public static Model createSphereModel(Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createSphere(7f, 7f, 7f, 20, 20, new Material(ColorAttribute.createDiffuse(color)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
    public static Model createSphereModel(Texture texture) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(TextureAttribute.createDiffuse(texture));
        return modelBuilder.createSphere(7f, 7f, 7f, 20, 20, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }
    public static Model createConeModel(Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createCone(5f, 10f, 5f, 20, new Material(ColorAttribute.createDiffuse(color)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
    public static Model createConeModel(Texture texture) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(TextureAttribute.createDiffuse(texture));
        return modelBuilder.createCone(5f, 10f, 5f, 20, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }
    public static Model createCylinderModel(Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createCylinder(2f, 5f, 2f, 20, new Material(ColorAttribute.createDiffuse(color)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
    public static Model createCylinderModel(Texture texture) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(TextureAttribute.createDiffuse(texture));
        return modelBuilder.createCylinder(5f, 10f, 5f, 20, material, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }
    public static Model createPlaneModel(Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createRect(
                -2.5f, -2.5f, 0f,
                2.5f, -2.5f, 0f,
                2.5f,  2.5f, 0f,
                -2.5f,  2.5f, 0f,
                0, 0, 1,
                new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }
    public static Model createPlaneModel(Texture texture) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(TextureAttribute.createDiffuse(texture));
        return modelBuilder.createRect(
                -2.5f, -2.5f, 0f,
                2.5f, -2.5f, 0f,
                2.5f,  2.5f, 0f,
                -2.5f,  2.5f, 0f,
                0, 0, 1,
                material,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
    }

    public static Model createIrregularShapeModel(List<Model> models) {
        // Łączenie modeli
        Model finalModel = new Model();
        for(Model model : models)
        {
            finalModel.nodes.addAll(model.nodes);
            finalModel.meshes.addAll(model.meshes);
            finalModel.materials.addAll(model.materials);

        }
        return finalModel;
    }
}
