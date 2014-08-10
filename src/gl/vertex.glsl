#version 330 core

uniform mat4 m_model;
layout(location = 0) in vec2 vertex;

void main() {
    gl_Position = m_model * vec4(vertex, 0.0, 1.0);
}