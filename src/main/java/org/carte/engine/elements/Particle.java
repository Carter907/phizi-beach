package org.carte.engine.elements;

import org.carte.engine.EngineContext;

public class Particle {
    private float x;
    private float y;
    private Element element;
    private EngineContext context;
    public Particle(

            float x,
            float y,
            Element element,
            EngineContext context
    ) {
        this.x = x;
        this.y = y;
        this.element = element;
        this.context = context;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public EngineContext getContext() {
        return context;
    }

    public void setContext(EngineContext context) {
        this.context = context;
    }
}
