package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class CustomCamera {
    public PerspectiveCamera camera;

    public void setCamera(PerspectiveCamera camera) {
        this.camera = camera;
    }

    public CustomCamera() {

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 0f, 0f);
        camera.lookAt(0f, 0f, 0f);
        camera.near = 0.1f;
        camera.far = 300f;
        camera.update();
    }

    public void update() {
        camera.update();
    }

    public void moveCamera(Vector3 position) {
        camera.position.set(position);
        camera.update();
    }

    public void rotateCamera(float yaw, float pitch) {
        camera.rotateAround(Vector3.Zero, Vector3.Y, yaw);
        camera.rotateAround(Vector3.Zero, camera.direction.cpy().crs(Vector3.Y), pitch);
        camera.update();
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }
}
