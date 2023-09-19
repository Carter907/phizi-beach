package org.carte;

import org.carte.application.Application;
import org.lwjgl.opengl.GL33;

import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL33.*;

public class PhiziApplication extends Application {


    @Override
    protected void init() {

    }

    @Override
    protected void resize() {

    }

    @Override
    protected void clean() {

    }

    @Override
    protected void update() {

        glClearColor(0,0,0,0);
        glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);


    }
}
