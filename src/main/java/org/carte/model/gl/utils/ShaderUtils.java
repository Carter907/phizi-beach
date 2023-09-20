package org.carte.model.gl.utils;

import org.lwjgl.opengl.GL33;

import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL33.*;

import java.io.*;

public final class ShaderUtils {
    private ShaderUtils() {
    }

    public static String getShaderCode(String path) {
        try (DataInputStream dataIn = new DataInputStream(
                new BufferedInputStream(ShaderUtils.class.getResourceAsStream(path)))) {
            return new String(dataIn.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getShader(String shaderCode, int type) {
        int shader = glCreateShader(type);
        glShaderSource(shader, shaderCode);
        glCompileShader(shader);
        int[] result = new int[1];

        glGetShaderiv(shader, GL_COMPILE_STATUS, result);
        if (result[0] == GL_FALSE) {

            String errMessage = glGetShaderInfoLog(shader);
            glDeleteShader(shader);
            throw new RuntimeException(errMessage);
        }
        return shader;
    }
}
