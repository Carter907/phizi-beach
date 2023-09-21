package org.carte.engine;

import org.carte.engine.elements.Element;
import org.carte.engine.elements.Particle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

public class PhiziEngine {

    private LinkedList<Particle> particles;
    public PhiziEngine() {
        this.particles = new LinkedList<>();

    }

    public void addRandomParticle() {
        this.particles.offer(
                new Particle(
                        (float)Math.random()*2-1,
                        (float)Math.random()*2-1,
                        Element.SAND,
                        new EngineContext()
                ));
    }

    public float[] getVertices() {
        float[] vertices = new float[particles.size()*3];
        for (int i = 0, j = 0; j < particles.size(); i+=3, j++) {
            vertices[i] = particles.get(j).getX();
            vertices[i+1] = particles.get(j).getY();
            vertices[i+2] = 0;

        }

        return vertices;
    }
}
