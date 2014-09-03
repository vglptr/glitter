#version 330 core

uniform mat4 m_model;
uniform vec2 mouse;
uniform float time;
layout(location = 0) in vec2 vertex;

void main() {
	//vertex means all the vertices here
    gl_Position = vec4(vertex.x, vertex.y + sin(5 * (vertex.x + time / 1000)) / 2,  0.0, 0.0) + vec4(mouse, 0.0, 1.0);
}