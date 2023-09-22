package org.carte.model;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public abstract class Application {

    protected long window;
    protected double delta, totalDelta; // delta time
    private double inital;
    protected int windowHeight, windowWidth;

    public Application() {
        windowWidth = 500;
        windowHeight = 400;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        start();
        loop();
        clean();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    protected abstract void init();

    protected abstract void resize(int width, int height);

    protected abstract void clean();

    protected abstract void update();


    private void start() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(windowWidth, windowHeight, "phizi engine", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetFramebufferSizeCallback(window, (window, width, height) -> {
            this.windowWidth = width;
            this.windowHeight = height;
            resize(width, height);
        });

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);
    }

    private void loop() {

        GL.createCapabilities();
        init();
        inital = System.currentTimeMillis();
        while (!glfwWindowShouldClose(window)) {

            glfwPollEvents();

            update();

            glfwSwapBuffers(window);
            delta = System.currentTimeMillis() - inital;
            inital = System.currentTimeMillis();
            totalDelta+=delta;
        }
    }


}
