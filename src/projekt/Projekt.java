package projekt;

import static org.lwjgl.opengl.GL11.*;

import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.ShaderProgram;
import projekt.util.ObjLoader;

public class Projekt extends AbstractOpenGLBase {

	ShaderProgram objShaderProgram;
	ShaderProgram pyramideShaderProgram1;
	ShaderProgram pyramideShaderProgram2;
	float counter = 0;

	HandmadePyramid pyramid1;
	HandmadePyramid pyramid2;
	ObjObject objObject;

	public static void main(String[] args) {
		new Projekt().start("CG Projekt", 700, 700);
	}

	@Override
	protected void init() {
		// Init Loaded Object
		//objShaderProgram = new ShaderProgram("monkey");
		//objObject = ObjLoader.load3DModel("./resources/monkey.obj", objShaderProgram, 0.25f);

		// Init Handmade Pyramide 1
		pyramideShaderProgram1 = new ShaderProgram("pyramide");
		pyramid1 = new HandmadePyramid(pyramideShaderProgram1);
		//pyramid1.translate(0.75f, 0, 0);

		//// Init Handmade Pyramide 2
		//pyramideShaderProgram2 = new ShaderProgram("pyramide");
		//pyramid2 = new HandmadePyramid(pyramideShaderProgram2);
		//pyramid2.translate(0, 0.75f, 0);
		//pyramid2.rotate(3.141f, 0, 0);

		glEnable(GL_DEPTH_TEST); // z-Buffer aktivieren
		glEnable(GL_CULL_FACE); // backface culling aktivieren
	}

	@Override
	public void update() {
		//objObject.rotate(0, 0.01f, 0);
		//pyramid1.rotate(0, -0.005f, 0);
		pyramid1.rotate(0.005f, 0.005f, 0);
		//pyramid2.rotate(-0.005f, 0, 0);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		//objObject.draw();
		pyramid1.draw();
		//pyramid2.draw();
	}

	@Override
	public void destroy() {
	}
}
