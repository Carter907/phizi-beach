package org.carte.engine;

import org.carte.engine.elements.Element;
import org.carte.engine.elements.Particle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class PhiziEngine {

    private float[] particles;
    public final int MAX_PARTICLES;

    public PhiziEngine(final int MAX_PARTICLES) {
        this.particles = new float[MAX_PARTICLES];
        this.MAX_PARTICLES = MAX_PARTICLES;
    }
//
//    public void addRandomParticle() {
//
//    }
//    public void addAtPoint(float x, float y) {
//
//    }
//
//    public float[] getVertices() {
//
//    }
//
//    public void update(Consumer<Particle> eachParticle) {
//
//    }
}
