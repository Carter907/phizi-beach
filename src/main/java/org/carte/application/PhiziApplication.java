package org.carte.application;

import org.carte.model.Application;
import org.carte.model.gl.utils.ProgramUtils;
import org.carte.model.gl.utils.ShaderUtils;
import org.lwjgl.opengl.GL33;

import static org.lwjgl.opengl.GL33.*;

public class PhiziApplication extends Application {

    private int program;

    @Override
    protected void init() {
        glPointSize(10);
        glEnable(GL_POINT_SMOOTH);

        program = ProgramUtils.getProgram(
                ShaderUtils.getShaderCode("/shaders/vert.glsl"),
                ShaderUtils.getShaderCode("/shaders/frag.glsl")
        );
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);

    }

    @Override
    protected void resize(int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    protected void clean() {

    }

    @Override
    protected void update() {

        glClearColor(0, 0, 0, 0);
        glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
        glUseProgram(program);

        glDrawArrays(GL_POINTS, 0, 1);

    }
}
