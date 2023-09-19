package org.carte.application;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public abstract class Application {

        private long window;

        public void run() {
            System.out.println("Hello LWJGL " + Version.getVersion() + "!");

            start();
            loop();

            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);

            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
        abstract void init();
        abstract void resize();
        abstract void clean();
        abstract void update();


        private void start() {
            GLFWErrorCallback.createPrint(System.err).set();

            if ( !glfwInit() )
                throw new IllegalStateException("Unable to initialize GLFW");

            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

            window = glfwCreateWindow(300, 300, "Hello World!", NULL, NULL);
            if ( window == NULL )
                throw new RuntimeException("Failed to create the GLFW window");

            glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, true);
            });

            try ( MemoryStack stack = stackPush() ) {
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

            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);


            while ( !glfwWindowShouldClose(window) ) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                glfwSwapBuffers(window);


                glfwPollEvents();
            }
        }


}
