package a2;

import static org.lwjgl.opengl.GL30.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// Folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] dreiecksKoordinaten = new float[]{-0.5f, -0.5f, 0.5f, -0.5f, 0, 0.5f};

		int vaoId = glGenVertexArrays();
		glBindVertexArray(vaoId);

		int vboId = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboId);
		glBufferData(GL_ARRAY_BUFFER, dreiecksKoordinaten, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

		// hier vorher erzeugte VAOs zeichnen
		glDrawArrays(GL_TRIANGLES, 0, 3);
	}

	@Override
	public void destroy() {

	}
}