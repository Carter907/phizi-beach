package org.carte.model.gl;

import static org.lwjgl.opengl.GL33.*;

public class Uniform<T> {

    private GLType dataType;
    private int variableRef;
    private T data;

    public Uniform(GLType dataType, T data) {
        this.setData(data);
        this.dataType = dataType;
    }

    // get and store reference for program variable with name
    public void locateVariable(int programRef, String variableName) {
        variableRef = glGetUniformLocation(programRef,
                variableName);
        if (variableRef == -1) {
            throw new RuntimeException("could not locate uniform variable \"" + variableName + "\"");
        }

    }
    public void uploadData() {
        switch  (dataType) {
            case INT, BOOL -> glUniform1i(variableRef, (Integer) getData());
            case FLOAT -> glUniform1f(variableRef, (Float) getData());
            case VEC2 -> {
                float[] vec2 = (float[]) getData();
                glUniform2f(variableRef, vec2[0],vec2[1]);
            }
            case VEC3 -> {
                float[] vec3 = (float[]) getData();
                glUniform3f(variableRef, vec3[0], vec3[1], vec3[2]);
            }
            case VEC4 -> {
                float[] vec4 = (float[]) getData();
                glUniform4f(variableRef, vec4[0], vec4[1], vec4[2], vec4[3]);
            }
        }
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
