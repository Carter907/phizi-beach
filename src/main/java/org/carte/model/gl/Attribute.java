package org.carte.model.gl;

import static org.lwjgl.opengl.GL33.*;

public class Attribute {

    private GLType dataType;
    public float[] data;
    private int bufferRef;

    public Attribute(GLType dataType, float[] data) {
        this.dataType = dataType;
        this.data = data;
        bufferRef = glGenBuffers();
        setBuffers();
    }

    private void setBuffers() {
        glBindBuffer(GL_ARRAY_BUFFER, bufferRef);
        glBufferData(GL_ARRAY_BUFFER, data,
                GL_STATIC_DRAW);
    }

    public void locateVariable(int programRef,
                                  String variableName) {
        int variableRef = glGetAttribLocation(programRef,
                variableName);
        if (variableRef == -1)
            return;
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
}
