package org.carte.engine.elements;

public enum Element {
    SAND(0.00000001562),
    WATER(0.00005);
    public final double mass;

    Element(double mass) {
        this.mass = mass;
    }
}
