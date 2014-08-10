package gl;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;

public class Triangle {
	ShaderProgram shader;
	int vboID, vaoID;
	int uniformLocation;
	Transform transform;
	
	public Triangle() {
		shader = new ShaderProgram();
		shader.attachVertexShader("gl/vertex.glsl");
		shader.attachFragmentShader("gl/fragment.glsl");
		shader.link();
		uniformLocation = shader.getUniformLocation("m_model");
		transform = new Transform();
		
		// Create a FloatBuffer to hold our vertex data
		FloatBuffer vertices = BufferUtils.createFloatBuffer(6);

		// Add vertices of the triangle
		vertices.put(new float[] {
		    +0.0f, +0.8f,    // Top vertex
		    +0.8f, -0.8f,    // Bottom-right vertex
		    -0.8f, -0.8f     // Bottom-left vertex
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
	
	public void update(long elapsedTime) {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            MainComponent.end();
       	transform.rotate(1, 1, 1);	
        render();
    }
	
	 public void render() {
        // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);
        
        // Bind our ShaderProgram
        shader.bind();
        
        //this method takes the 99% of this program by querying uniform location in each update...
        //shader.setUniform("m_model", transform.getFloatBuffer());
        
        shader.setUniform(uniformLocation, transform.getFloatBuffer());
        
        // Bind the VAO
        glBindVertexArray(vaoID);
        
        // Enable the location 0 to send vertices to the shader
        glEnableVertexAttribArray(0);
        
        // Draw a triangle with first 3 vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);
         
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
