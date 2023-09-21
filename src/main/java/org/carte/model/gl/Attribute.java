package org.carte.model.gl;

import static org.lwjgl.opengl.GL33.*;

public class Attribute {

    private GLType dataType;
    private float[] data;
    public int bufferRef;

    public Attribute(GLType dataType, float[] data, boolean isDynamic) {
        this.dataType = dataType;
        this.setData(data);
        bufferRef = glGenBuffers();
        setBuffers(isDynamic);
    }

    private void setBuffers(boolean isDynamic) {
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);
        if (isDynamic) {

            glBufferData(GL_ARRAY_BUFFER, getData(), GL_DYNAMIC_DRAW);
        } else
            glBufferData(GL_ARRAY_BUFFER, getData(), GL_STATIC_DRAW);
    }

    public void locateVariable(int programRef,
                                  String variableName) {
        int variableRef = glGetAttribLocation(programRef,
                variableName);
        if (variableRef == -1)
            throw new RuntimeException("could not locate attribute variable \"" + variableName + "\"");
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);
        switch (dataType) {
            case FLOAT -> glVertexAttribPointer(variableRef, 1, GL_FLOAT,
                    false, 0, 0);
            case INT -> glVertexAttribPointer(variableRef, 1, GL_INT,
                    false, 0, 0);
            case VEC2 -> glVertexAttribPointer(variableRef, 2, GL_FLOAT,
                    false, 0, 0);
            case VEC3 -> glVertexAttribPointer(variableRef, 3, GL_FLOAT,
                    false, 0, 0);
            case VEC4 -> glVertexAttribPointer(variableRef, 4, GL_FLOAT,
                    false, 0, 0);
        }

        glEnableVertexAttribArray(variableRef);
    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }
}
