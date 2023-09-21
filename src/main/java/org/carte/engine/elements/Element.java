package org.carte.engine.elements;

public enum Element {
    SAND(0.00000440f),
    WATER(0.00005f);
    public final float mass;

    Element(float mass) {
        this.mass = mass;
    }
}
