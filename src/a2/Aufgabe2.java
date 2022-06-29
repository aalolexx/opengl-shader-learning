package a2;

import static org.lwjgl.opengl.GL30.*;

import projekt.util.VaoProgram;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;

public class Aufgabe2 extends AbstractOpenGLBase {

	VaoProgram triangle1VAO;

	public static void main(String[] args) {
		new Aufgabe2().start("CG Aufgabe 2", 700, 700);
	}

	@Override
	protected void init() {
		// Folgende Zeile läd automatisch "aufgabe2_v.glsl" (vertex) und "aufgabe2_f.glsl" (fragment)
		ShaderProgram shaderProgram = new ShaderProgram("aufgabe2");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] triangleCoordinates = new float[]{-0.4f, -0.4f, 0f, 0.4f, 0.4f, -0.4f};
		float[] redTones = new float[]{0f, 0.5f, 1f};
		triangle1VAO = new VaoProgram(shaderProgram.getId());
		triangle1VAO.createVbo(0, triangleCoordinates, 2);
		triangle1VAO.addToPointCount(3);
		triangle1VAO.createVbo(1, redTones, 1);
		//triangle1VAO.createVbo(triangleCoordinates2);
	}

	@Override
	public void update() {
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // Zeichenfläche leeren

		// hier vorher erzeugte VAOs zeichnen
		glDrawArrays(GL_TRIANGLES, 0, triangle1VAO.getPointCount());
	}

	@Override
	public void destroy() {

	}
}