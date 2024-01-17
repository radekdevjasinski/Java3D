package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
public class Cube extends ModelInstance {

    public Cube(float size, Color color) {
        super(createCubeModel(size, color));
    }

    private static Model createCubeModel(float size, Color color) {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();

        int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.ColorPacked;

        MeshBuilder meshBuilder = new MeshBuilder();
        meshBuilder.begin(attr, GL20.GL_TRIANGLES);
        meshBuilder.part("cube", GL20.GL_TRIANGLES);

        Mesh mesh = meshBuilder.end();

        modelBuilder.part("cube", mesh, GL20.GL_TRIANGLES, new Material());


        return modelBuilder.end();
    }
}
