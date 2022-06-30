package projekt;

import projekt.util.ObjLoader;
import projekt.util.Object3D;
import projekt.util.VectorUtils;
import lenz.opengl.ShaderProgram;

import java.io.IOException;

import static org.lwjgl.opengl.GL20.*;

public class HandmadePyramid extends Object3D {
    Texture texture;

    public HandmadePyramid(ShaderProgram shaderProgram) {
        super(shaderProgram);
        init();
    }

    /* ****************
     * SETUP FUNCTIONS
     * ****************/

    private void init () {
        // Actual Point Coordinates of the pyramide
        float[][] groundFace = new float[][] {
                {-0.2f, -0.2f, -0.2f},
                {0f, -0.2f, 0.2f},
                {0.2f, -0.2f, -0.2f}
        };
        float[][] frontFace = new float[][] {
                {-0.2f, -0.2f, -0.2f},
                {0.2f, -0.2f, -0.2f},
                {0, 0.2f, 0}
        };
        float[][] rightFace = new float[][] {
                {0.2f, -0.2f, -0.2f},
                {0f, -0.2f, 0.2f},
                {0f, 0.2f, 0f},
        };
        float[][] leftFace = new float[][] {
                {0f, -0.2f, 0.2f},
                {-0.2f, -0.2f, -0.2f},
                {0f, 0.2f, 0f}
        };

        // Points as float array for VBO
        // Note: Yes this could be optimized as a loop, but this Class's purpose is to
        //       Demonstrate how hard it is to create shapes in a non-programmatic approach
        float[] pyramideCoordinates = new float[] {
                // Ground
                groundFace[0][0], groundFace[0][1], groundFace[0][2],
                groundFace[1][0], groundFace[1][1], groundFace[1][2],
                groundFace[2][0], groundFace[2][1], groundFace[2][2],
                // Front Face
                frontFace[0][0], frontFace[0][1], frontFace[0][2],
                frontFace[1][0], frontFace[1][1], frontFace[1][2],
                frontFace[2][0], frontFace[2][1], frontFace[2][2],
                // Right Face
                rightFace[0][0], rightFace[0][1], rightFace[0][2],
                rightFace[1][0], rightFace[1][1], rightFace[1][2],
                rightFace[2][0], rightFace[2][1], rightFace[2][2],
                // Left Face
                leftFace[0][0], leftFace[0][1], leftFace[0][2],
                leftFace[1][0], leftFace[1][1], leftFace[1][2],
                leftFace[2][0], leftFace[2][1], leftFace[2][2],
        };
        vaoProgram.createVbo(0, pyramideCoordinates, 3);
        vaoProgram.addToPointCount(4 * 3);

        // Normals of the faces
        float[] groundNormal = VectorUtils.getNormal(groundFace[0], groundFace[1], groundFace[2]);
        float[] frontNormal = VectorUtils.getNormal(frontFace[0], frontFace[1], frontFace[2]);
        float[] rightNormal = VectorUtils.getNormal(rightFace[0], rightFace[1], rightFace[2]);
        float[] leftNormal = VectorUtils.getNormal(leftFace[0], leftFace[1], leftFace[2]);
        float[] pyramideNormals = new float[] {
                // Ground
                groundNormal[0], groundNormal[1], groundNormal[2],
                groundNormal[0], groundNormal[1], groundNormal[2],
                groundNormal[0], groundNormal[1], groundNormal[2],
                // Front Face
                frontNormal[0], frontNormal[1], frontNormal[2],
                frontNormal[0], frontNormal[1], frontNormal[2],
                frontNormal[0], frontNormal[1], frontNormal[2],
                // Right Face
                rightNormal[0], rightNormal[1], rightNormal[2],
                rightNormal[0], rightNormal[1], rightNormal[2],
                rightNormal[0], rightNormal[1], rightNormal[2],
                // Left Face
                leftNormal[0], leftNormal[1], leftNormal[2],
                leftNormal[0], leftNormal[1], leftNormal[2],
                leftNormal[0], leftNormal[1], leftNormal[2],
        };
        vaoProgram.createVbo(1, pyramideNormals, 3);

        // Texture
        /*float[] texCoords = new float []{
                0, 1f, 1f, 1f, 0.5f, 0,
                0, 1f, 1f, 1f, 0.5f, 0,
                0, 1f, 1f, 1f, 0.5f, 0,
                0, 1f, 1f, 1f, 0.5f, 0,
        };*/
        float[] texCoords = new float []{
                1f, 1f, 1f, 1f, 1f, 1f,
                1f, 1f, 1f, 1f, 1f, 1f,
                1f, 1f, 1f, 1f, 1f, 1f,
                1f, 1f, 1f, 1f, 1f, 1f,
        };
        //vaoProgram.createVbo(2, texCoords, 2);
        //texture = new Texture("./resources/marble_tex/test.png", texCoords, vaoProgram, shaderProgram);
    }
}
