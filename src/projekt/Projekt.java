package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import ehrenhoefer.opengl.VaoProgram;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Projekt extends AbstractOpenGLBase {

	VaoProgram triangle1VAO;
	ShaderProgram shaderProgram;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		// Koordinaten, VAO, VBO, ... hier anlegen und im Grafikspeicher ablegen
		float[] triangleCoordinates = new float[]{-0.4f, -0.4f, 0.4f, -0.4f, 0f, 0.4f};
		float[] redTones = new float[]{0f, 0.5f, 1f};
		triangle1VAO = new VaoProgram();
		triangle1VAO.createVbo(0, triangleCoordinates, 2);
		triangle1VAO.addToPointCount(3);
		triangle1VAO.createVbo(1, redTones, 1);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		Matrix4 transformMatrix = new Matrix4();
		transformMatrix.translate(0.5f,0, 0);
		//transformMatrix.printMatrix();
		int matrix_loc = glGetUniformLocation(shaderProgram.getId(), "transformMatrix");
		glUniformMatrix4fv(matrix_loc, true,transformMatrix.getValuesAsArray());
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glDrawArrays(GL_TRIANGLES, 0, triangle1VAO.getPointCount());
	}

	@Override
	public void destroy() {
	}
}
