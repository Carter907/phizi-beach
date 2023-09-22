package org.carte.engine;

import java.util.Collections;
import java.util.Map;

public class EngineContext {
    private double velocityX, velocityY;
    private double forceX, forceY;
    private double mass;

    public EngineContext(double velocityX, double velocityY, double forceX, double forceY, double mass) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.forceX = forceX;
        this.forceY = forceY;
        this.mass = mass;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getForceX() {
        return forceX;
    }

    public void setForceX(double forceX) {
        this.forceX = forceX;
    }

    public double getForceY() {
        return forceY;
    }

    public void setForceY(double forceY) {
        this.forceY = forceY;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getAccelerationX() {
       return forceX / mass;
    }
    public double getAccelerationY() {
        return forceY / mass;
    }

}
