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

        // Define vertices
        float[] vertices = {
                -size / 2f, -size / 2f, -size / 2f, 0, 0, -1, Color.toFloatBits(color.r, color.g, color.b, color.a),
                size / 2f, -size / 2f, -size / 2f, 0, 0, -1, Color.toFloatBits(color.r, color.g, color.b, color.a),
                size / 2f, size / 2f, -size / 2f, 0, 0, -1, Color.toFloatBits(color.r, color.g, color.b, color.a),
                -size / 2f, size / 2f, -size / 2f, 0, 0, -1, Color.toFloatBits(color.r, color.g, color.b, color.a),

        };

        // Define indices
        short[] indices = {
                0, 1, 2, 2, 3, 0,
        };

        MeshBuilder meshBuilder = new MeshBuilder();
        meshBuilder.begin(attr, GL20.GL_TRIANGLES);
        meshBuilder.part("cube", GL20.GL_TRIANGLES);

        Mesh mesh = meshBuilder.end();

        modelBuilder.part("cube", mesh, GL20.GL_TRIANGLES, new Material());

        //modelBuilder.end();

        return modelBuilder.end();
    }
}
