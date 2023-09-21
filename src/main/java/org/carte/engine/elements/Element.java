package org.carte.engine.elements;

public enum Element {
    SAND(1602),
    WATER(997);
    public final int density;

    Element(int density) {
        this.density = density;
    }
}
