#version 330 core

uniform mat4 m_model;
uniform vec2 mouse;
layout(location = 0) in vec2 vertex;

void main() {
    gl_Position = vec4(vertex, 0.0, 0.0) + vec4(mouse, 0.0, 1.0);
}