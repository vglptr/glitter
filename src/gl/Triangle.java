package gl;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Triangle implements Updatable {
	ShaderProgram shader;
	int vboID, vaoID;
	int uniformLocationTransform;
	int uniformLocationMouse;
	Transform transform;
	float width;
	float height;
	int mouseX;
	int mouseY;

	public Triangle() {
		width = (float)Display.getWidth();
		height = (float)Display.getHeight();
		shader = new ShaderProgram();
		shader.attachVertexShader("gl/vertex.glsl");
		shader.attachFragmentShader("gl/fragment.glsl");
		shader.link();
		uniformLocationTransform = shader.getUniformLocation("m_model");
		uniformLocationMouse = shader.getUniformLocation("mouse");

		transform = new Transform();

		// Create a FloatBuffer to hold our vertex data
		FloatBuffer vertices = BufferUtils.createFloatBuffer(6);

		// Add vertices of the triangle
		vertices.put(new float[] { +0.0f, +0.2f, // Top vertex
				+0.2f, -0.2f, // Bottom-right vertex
				-0.2f, -0.2f // Bottom-left vertex
		});

		// Rewind the vertices
		vertices.rewind();

		// Create a VAO
		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);

		// Create a VBO
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

		// Set the pointers in the VAO
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

		// Unbind the VAO
		glBindVertexArray(0);
	}

	public void update(long delta) {
		// transform.rotate(1, 1, 1);
		// transform.reset();
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		render();
	}

	public void render() {
		// Clear the screen
		glClear(GL_COLOR_BUFFER_BIT);

		// Bind our ShaderProgram
		shader.bind();

		shader.setUniform(uniformLocationTransform, transform.getFloatBuffer());
		shader.setUniform(uniformLocationMouse,
				(mouseX * 2.0f - width) / width,
				(mouseY * 2.0f - height) / height);
		// Bind the VAO
		glBindVertexArray(vaoID);

		// Enable the location 0 to send vertices to the shader
		glEnableVertexAttribArray(0);

		// Draw a triangle with first 3 vertices
		glDrawArrays(GL_TRIANGLES, 0, 3);
		Display.processMessages();

		// Disable the location 0 and unbind the VAO
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);

		// Unbind the ShaderProgram
		ShaderProgram.unbind();
	}

	public void resized() {
		// Set the viewport to the entire window
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

	public void dispose() {
		// Dispose the shaders
		shader.dispose();

		// Dispose the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoID);

		// Dispose the VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vboID);
	}
}
