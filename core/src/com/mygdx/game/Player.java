package com.mygdx.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class Player extends PhysicalObject implements Renderable {
    private Camera camera;
    private float health;
    public Player(String name, Vector3 position, Vector3 velocity, Camera camera, float health) {
        super(name, position, velocity);
        this.camera = camera;
        this.health = health;
    }
    @Override
    public void render() {
    }
}