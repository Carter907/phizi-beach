package org.carte.engine;

import org.carte.engine.physics.Direction;
import org.carte.engine.physics.Force;
import org.carte.engine.physics.Velocity;

import java.util.Map;

public class EngineContext {
    private Velocity velocity;
    private Force netForce;

    public EngineContext(Velocity velocity, Force netForce) {
        this.velocity = velocity;
        this.netForce = netForce;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public Force getNetForce() {
        return netForce;
    }

    public void setNetForce(Force netForce) {
        this.netForce = netForce;
    }
}
