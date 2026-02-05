package io.silverdev637.sjge.engine;

public class Camera {
    private double x, y;

    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public Camera setX(double x) { this.x = x; return this; }
    public Camera setY(double y) { this.y = y; return this; }
}

