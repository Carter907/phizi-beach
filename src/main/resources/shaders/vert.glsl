#version 330 core
uniform float time;
uniform float mass;
in vec2 pos;
in vec2 velocity;
in vec2 force;


void main() {

    float accel_x = force.x / mass;
    float accel_y = force.y / mass;
    float velocity_x_f = accel_x * time;
    float velocity_y_f = accel_y * time;

    gl_Position = vec4(pos.x+(velocity_x_f * time), pos.y+(velocity_y_f * time), 0, 1);
}
