package projekt;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import projekt.util.ObjLoader;

public class Projekt extends AbstractOpenGLBase {

	ShaderProgram objShaderProgram;
	ShaderProgram pyramideShaderProgram;
	float counter = 0;

	HandmadePyramid pyramid;
	ObjObject objObject;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		// Init Loaded Object
		objShaderProgram = new ShaderProgram("projekt");
		objObject = ObjLoader.load3DModel("./resources/monkey.obj", objShaderProgram, 0.4f);

		// Init Handmade Pyramide
		pyramideShaderProgram = new ShaderProgram("pyramide");
		pyramid = new HandmadePyramid(pyramideShaderProgram);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		counter += 0.005;
		objObject.rotateAbsolut(counter);
		//pyramid.rotateAbsolut(counter * -1);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		objObject.draw();
		pyramid.draw();
	}

	@Override
	public void destroy() {
	}
}
