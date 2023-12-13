package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class SphereObject extends PrimitiveObject {
    private float radius;
    private Vector3 position;
    private Matrix4 transformMatrix;
    private ModelInstance modelInstance;


    public SphereObject(Model model, Color color ,float radius, float x, float y, float z) {
        super(model,color);
        this.radius = radius;
        this.position = new Vector3(x, y, z);
        this.transformMatrix = new Matrix4();
        this.modelInstance = createSphereModel();
    }

    private ModelInstance createSphereModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material material = new Material(ColorAttribute.createDiffuse(Color.RED)); // Ustawienie koloru na czerwony
        return new ModelInstance(modelBuilder.createSphere(radius * 2, radius * 2, radius * 2, 10, 10, material, Usage.Position | Usage.Normal));
    }

    void createSphereMesh() {
        MeshBuilder meshBuilder = new MeshBuilder();
        meshBuilder.sphere(radius * 2, radius * 2, radius * 2, 10, 10);
    }
    private Camera camera() {
        return null;
    }
    @Override
    public void draw() {
        ModelBatch modelBatch = new ModelBatch();
        modelBatch.begin(camera());
        modelBatch.render(modelInstance);
        modelBatch.end();
    }

    @Override
    public void translate(float x, float y, float z) {
        position.add(x, y, z);
        transformMatrix.idt().translate(position);
        modelInstance.transform.set(transformMatrix);
    }

    @Override
    public void rotate(float angle, float axisX, float axisY, float axisZ) {
        transformMatrix.idt().rotate(axisX, axisY, axisZ, angle);
        modelInstance.transform.set(transformMatrix);
    }

    @Override
    public void scale(float scaleX, float scaleY, float scaleZ) {
        transformMatrix.idt().scale(scaleX, scaleY, scaleZ);
        modelInstance.transform.set(transformMatrix);
    }
}
