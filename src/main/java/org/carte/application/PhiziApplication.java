package org.carte.application;

import org.carte.engine.EngineContext;
import org.carte.engine.PhiziEngine;
import org.carte.engine.elements.Element;
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
    private int time;
    private int pos, velocity, force;
    private float[] vertices;
    private PhiziEngine engine;

    final int MAX_VERTS = 10000;

    public PhiziApplication() {
        this.engine = new PhiziEngine(MAX_VERTS);
    }

    @Override
    protected void init() {


//        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//            if (key == GLFW_KEY_F && action == GLFW_PRESS)
//                engine.addRandomParticle();
//        });

        glPointSize(1f);
        glEnable(GL_POINT_SMOOTH);
        program = ProgramUtils.getProgram(
                ShaderUtils.getShaderCode("/shaders/vert.glsl"),
                ShaderUtils.getShaderCode("/shaders/frag.glsl")
        );
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        vertices = new float[]{
                0f, 0f, //position
                0f, 0f, //velocity
                0f, -2.81f, //acceleration


        };

//        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
//            double[] x = new double[1], y = new double[1];
//            glfwGetCursorPos(window,x,y);
//            if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS)
//                engine.addAtPoint((float)x[0]/windowWidth*2-1, -((float)y[0]/windowHeight*2-1));
//        });


        glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
        int posAttrib = glGetAttribLocation(program, "pos");
        glVertexAttribPointer(posAttrib, 2, GL_FLOAT, false, Float.BYTES * 6,0);
        glEnableVertexAttribArray(posAttrib);
        int velocityAttrib = glGetAttribLocation(program, "velocity");
        glVertexAttribPointer(velocityAttrib, 2, GL_FLOAT, false, Float.BYTES * 6, Float.BYTES * 2);
        glEnableVertexAttribArray(velocityAttrib);
        int forceAttrib = glGetAttribLocation(program, "accel");
        glVertexAttribPointer(forceAttrib, 2, GL_FLOAT, false, Float.BYTES * 6, Float.BYTES * 4);
        glEnableVertexAttribArray(forceAttrib);




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

//        if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
//            double[] x = new double[1], y = new double[1];
//            glfwGetCursorPos(window, x, y);
//
//            engine.addAtPoint((float) x[0] / windowWidth * 2 - 1, -((float) y[0] / windowHeight * 2 - 1));
//        }
        glUniform1f(glGetUniformLocation(program, "time"), (float)totalDelta / 1000);
        glUniform1f(glGetUniformLocation(program, "mass"), (float)Element.SAND.mass);

        glDrawArrays(GL_POINTS, 0, 1);

    }
}
