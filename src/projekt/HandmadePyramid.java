package projekt;

import ehrenhoefer.opengl.VaoProgram;
import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class HandmadePyramid {
    public VaoProgram vaoProgram;
    public ShaderProgram shaderProgram;

    // All Triangle Coordinate -> Pyramid is made of 4 triangles
    public float[][] trianglesCoordinates = new float[4][];

    public HandmadePyramid(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
        vaoProgram = new VaoProgram();
        init();
    }

    /* ****************
     * SETUP FUNCTIONS
     * ****************/

    private void init () {
        // Actual Point Coordinates of the pyramide
        float[] pyramideCoordinates = new float[] {
                // Ground
                -0.4f, -0.4f, -0.4f, 0f, -0.4f, 0.4f, 0.4f, -0.4f, -0.4f,
                // Front Face
                -0.4f, -0.4f, -0.4f, 0.4f, -0.4f, -0.4f, 0f, 0.4f, 0f,
                // Right Face
                0.4f, -0.4f, -0.4f, 0f, -0.4f, 0.4f, 0f, 0.4f, 0f,
                // Left Face
                0f, -0.4f, 0.4f, -0.4f, -0.4f, -0.4f, 0f, 0.4f, 0f
        };
        vaoProgram.createVbo(0, pyramideCoordinates, 3);
        vaoProgram.addToPointCount(4 * 3);

        // Face Colors
        float[] pyramideColors = new float[] {
                // Ground -> red
                1, 0, 0, 1, 0, 0, 1, 0, 0,
                // Front Face -> green
                0, 1, 0, 0, 1, 0, 0, 1, 0,
                // Right Face -> blue
                0, 0, 1, 0, 0, 1, 0, 0, 1,
                // Left Face -> something in between
                0, 0.5f, 0.5f, 0, 0.5f, 0.5f, 0, 0.5f, 0.5f,
        };
        vaoProgram.createVbo(1, pyramideColors, 3);
    }

    /* ****************
     * RENDER FUNCTIONS
     * ****************/

    public void draw() {
        glDrawArrays(GL_TRIANGLES, 0, vaoProgram.getPointCount());
    }

    public void rotateAbsolut (float deg) {
        Matrix4 transformMatrix = new Matrix4();
        transformMatrix.rotateY(deg);
        transformMatrix.rotateX(deg);
        int matrix_loc = glGetUniformLocation(shaderProgram.getId(), "transformMatrix");
        glUniformMatrix4fv(matrix_loc, true,transformMatrix.getValuesAsArray());
    }
}
