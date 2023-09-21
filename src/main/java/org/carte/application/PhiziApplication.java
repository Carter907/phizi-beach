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
    private PhiziEngine engine;

    public PhiziApplication() {
        this.engine = new PhiziEngine();
    }

    @Override
    protected void init() {

        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
            double[] x = new double[1], y = new double[1];
            glfwGetCursorPos(window,x,y);
           if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS)
               engine.addAtPoint((float)x[0]/windowWidth*2-1, -((float)y[0]/windowHeight*2-1));
        });
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
           if (key == GLFW_KEY_F && action == GLFW_PRESS)
               engine.addRandomParticle();
        });

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

        engine.update((float)totalDelta);

        glDrawArrays(GL_POINTS, 0, engine.getVertices().length/3);

    }
}
