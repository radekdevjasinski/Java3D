package com.mygdx.game;
public abstract class TransformableObject {
    public abstract void translate(float x, float y, float z);
    public abstract void rotate(float angle, float axisX, float axisY, float axisZ);
    public abstract void scale(float scaleX, float scaleY, float scaleZ);
}