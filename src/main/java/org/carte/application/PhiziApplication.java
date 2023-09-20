package org.carte.application;

import org.carte.model.Application;
import org.carte.model.gl.Attribute;
import org.carte.model.gl.GLType;
import org.carte.model.gl.utils.ProgramUtils;
import org.carte.model.gl.utils.ShaderUtils;
import org.lwjgl.opengl.GL33;

import static org.lwjgl.opengl.GL33.*;

public class PhiziApplication extends Application {

    private int program;

    @Override
    protected void init() {

        program = ProgramUtils.getProgram(
                ShaderUtils.getShaderCode("/shaders/vert.glsl"),
                ShaderUtils.getShaderCode("/shaders/frag.glsl")
        );
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        float[] vertices = {

                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0f, .5f, 0.0f,
        };
        var attrib = new Attribute(GLType.VEC3, vertices);
        attrib.locateVariable(program, "pos");
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

        glDrawArrays(GL_TRIANGLES, 0, 3);

    }
}
