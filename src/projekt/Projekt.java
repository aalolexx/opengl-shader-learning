package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import projekt.util.ObjLoader;

public class Projekt extends AbstractOpenGLBase {

	ShaderProgram shaderProgram;
	float counter = 0;

	HandmadePyramid pyramid;
	ObjObject objObject;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		shaderProgram = new ShaderProgram("projekt");
		glUseProgram(shaderProgram.getId());

		//pyramid = new HandmadePyramid(shaderProgram);
		objObject = ObjLoader.load3DModel("./resources/monkey.obj", shaderProgram, 0.4f);

		System.out.println(" ------------- First Vertice: ");
		System.out.println(objObject.vertices.get(0).x);
		System.out.println(objObject.vertices.get(0).y);
		System.out.println(objObject.vertices.get(0).z);

		System.out.println(" ------------- First Face: ");
		System.out.println(objObject.facesVertices.get(0).x);
		System.out.println(objObject.facesVertices.get(0).y);
		System.out.println(objObject.facesVertices.get(0).z);

		System.out.println("-------- First Coordinates");
		System.out.println(objObject.getCoordinatesArray()[0]);
		System.out.println(objObject.getCoordinatesArray()[1]);
		System.out.println(objObject.getCoordinatesArray()[2]);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		counter += 0.005;
		//pyramid.rotateAbsolut(counter);
		objObject.rotateAbsolut(counter);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//pyramid.draw();
		objObject.draw();
	}

	@Override
	public void destroy() {
	}
}
