package projekt.util;

import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Object3D
{
    public VaoProgram vaoProgram;
    public ShaderProgram shaderProgram;
    private Matrix4 transformMatrix;

    public Object3D(ShaderProgram shaderProgram) {
        glUseProgram(shaderProgram.getId());
        this.shaderProgram = shaderProgram;
        vaoProgram = new VaoProgram(shaderProgram.getId());

        // Pass Initial Matrix to Shader
        this.transformMatrix = new Matrix4();
        passMatrixToShader();
    }

    /* ****************
     * RENDER FUNCTIONS
     * ****************/

    public void draw() {
        glUseProgram(shaderProgram.getId());
        glBindVertexArray(vaoProgram.vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vaoProgram.getPointCount());
    }

    public void translate (float x, float y, float z) {
        transformMatrix.translate(x, y, z);
        passMatrixToShader();
    }

    public void scale (float factor) {
        transformMatrix.scale(factor);
        passMatrixToShader();
    }

    public void rotate (float degX, float degY, float degZ) {
        transformMatrix.rotateX(degX);
        transformMatrix.rotateY(degY);
        transformMatrix.rotateZ(degZ);
        passMatrixToShader();
    }

    private void passMatrixToShader() {
        glBindVertexArray(vaoProgram.vaoId);
        glUseProgram(shaderProgram.getId());
        int matrix_loc = glGetUniformLocation(shaderProgram.getId(), "transformMatrix");
        glUniformMatrix4fv(matrix_loc, true, transformMatrix.getValuesAsArray());
    }
}
