package org.carte.application;

import org.carte.engine.PhiziEngine;
import org.carte.model.Application;
import org.carte.model.gl.Attribute;
import org.carte.model.gl.GLType;
import org.carte.model.gl.Uniform;
import org.carte.model.gl.utils.ProgramUtils;
import org.carte.model.gl.utils.ShaderUtils;
import org.lwjgl.opengl.GL33;

import java.util.Arrays;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;

public class PhiziApplication extends Application {

    private int program;
    private Attribute pos;
    private Uniform<Float> add_x;
    private PhiziEngine engine;

    public PhiziApplication() {
        this.engine = new PhiziEngine();
    }

    @Override
    protected void init() {
        glPointSize(5f);
        program = ProgramUtils.getProgram(
                ShaderUtils.getShaderCode("/shaders/vert.glsl"),
                ShaderUtils.getShaderCode("/shaders/frag.glsl")
        );
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        final int MAX_VERTS = 1000;
        float[] vertices = new float[MAX_VERTS * 3];
        pos = new Attribute(GLType.VEC3, vertices, true);
        pos.locateVariable(program, "pos");

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
        glBindBuffer(GL_ARRAY_BUFFER, pos.bufferRef);
        glBufferSubData(GL_ARRAY_BUFFER, 0, engine.getVertices());
        System.out.println(Arrays.toString(engine.getVertices()));
        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS)
            engine.addRandomParticle();


        glDrawArrays(GL_POINTS, 0, engine.getVertices().length/3);

    }
}
