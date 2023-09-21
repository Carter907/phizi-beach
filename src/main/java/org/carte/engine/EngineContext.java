package org.carte.engine;

import java.util.Collections;
import java.util.Map;

public class EngineContext {
    private float velocityX, velocityY;
    private float forceX, forceY;
    private float mass;

    public EngineContext(float velocityX, float velocityY, float forceX, float forceY, float mass) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.forceX = forceX;
        this.forceY = forceY;
        this.mass = mass;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public float getForceX() {
        return forceX;
    }

    public void setForceX(float forceX) {
        this.forceX = forceX;
    }

    public float getForceY() {
        return forceY;
    }

    public void setForceY(float forceY) {
        this.forceY = forceY;
    }

    public float getAccelerationX() {
       return forceX / mass;
    }
    public float getAccelerationY() {
        return forceY / mass;
    }
}
