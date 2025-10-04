package model;

public class Pet {
    private double x;
    private double y;
    private double targetX;
    private double targetY;

    public Pet(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.targetX = startX;
        this.targetY = startY;
    }

    // Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTargetX() {
        return targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    // Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setTarget(double targetX, double targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
    }
}