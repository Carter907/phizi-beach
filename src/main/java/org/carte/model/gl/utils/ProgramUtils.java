package org.carte.model.gl.utils;

import static org.lwjgl.opengl.GL33.*;

public final class ProgramUtils {
    private ProgramUtils() {
    }

    public static int getProgram(String vertexCode, String fragCode) {
        int program = glCreateProgram();
        int vertShader = ShaderUtils.getShader(vertexCode, GL_VERTEX_SHADER);
        int fragShader = ShaderUtils.getShader(fragCode, GL_FRAGMENT_SHADER);
        glAttachShader(program, vertShader);
        glAttachShader(program, fragShader);

        glLinkProgram(program);
        int[] result = new int[1];
        glGetProgramiv(program, GL_LINK_STATUS, result);
        if (result[0] == GL_FALSE) {
            String errMessage = glGetProgramInfoLog(program);
            glDeleteProgram(program);
            throw new RuntimeException(errMessage);
        }
        glDeleteShader(fragShader);
        glDeleteShader(vertShader);

        return program;

    }

}
