#version 330 core
uniform float time;
in vec2 pos;
in vec2 velocity;
in vec2 accel;


void main() {

    float velocity_x_f = accel.x * time;
    float velocity_y_f = accel.y * time;

    gl_Position = vec4(pos.x+(velocity_x_f * time), pos.y+(velocity_y_f * time), 0, 1);
}
