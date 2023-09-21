#version 330 core
in vec3 pos;
uniform float add_x;
void main() {
    gl_Position = vec4(pos.x+add_x, pos.yz,1);
}
