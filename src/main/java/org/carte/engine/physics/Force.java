package org.carte.engine.physics;

public class Force {
    private float magnitude;
    private Direction direction;
    public Force(
       float magnitude,
       Direction direction
    ) {
        this.magnitude = magnitude;
        this.direction = direction;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
