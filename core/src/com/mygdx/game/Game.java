package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.Array;

public class Game implements ApplicationListener {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Environment environment;
    private Array<PrimitiveObject> objects;

    @Override
    public void create() {
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0, 0, 5);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 100f;
        camera.update();

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));

        objects = new Array<>(); // Poprawka do inicjalizacji
        objects.add(new PrimitiveObject(createCubeModel(), Color.RED) {
            @Override
            public void draw() {

            }

            @Override
            public void translate(float x, float y, float z) {

            }

            @Override
            public void rotate(float angle, float axisX, float axisY, float axisZ) {

            }

            @Override
            public void scale(float scaleX, float scaleY, float scaleZ) {

            }
        });
        objects.add(new PrimitiveObject(createSphereModel(), Color.BLUE) {
            @Override
            public void draw() {

            }

            @Override
            public void translate(float x, float y, float z) {

            }

            @Override
            public void rotate(float angle, float axisX, float axisY, float axisZ) {

            }

            @Override
            public void scale(float scaleX, float scaleY, float scaleZ) {

            }
        });
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);
        for (PrimitiveObject object : objects) {
            modelBatch.render(object.getModelInstance(), environment);
        }
        modelBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        for (PrimitiveObject object : objects) {
            object.dispose();
        }
    }

    private Model createCubeModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createBox(1f, 1f, 1f, new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    private Model createSphereModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        return modelBuilder.createSphere(0.5f, 0.5f, 0.5f, 20, 20, new Material(), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    // Pozostałe metody interfejsu ApplicationListener (resize, pause, resume) mogą pozostać puste.
}
