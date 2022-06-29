package projekt.util;

import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Object3D
{
    public VaoProgram vaoProgram;
    public ShaderProgram shaderProgram;

    public Object3D(ShaderProgram shaderProgram) {
        glUseProgram(shaderProgram.getId());
        this.shaderProgram = shaderProgram;
        vaoProgram = new VaoProgram(shaderProgram.getId());

        // Pass Initial Matrix to Shader
        rotateAbsolut(0);
    }

    /* ****************
     * RENDER FUNCTIONS
     * ****************/

    public void draw() {
        glUseProgram(shaderProgram.getId());
        glBindVertexArray(vaoProgram.vaoId);
        glDrawArrays(GL_TRIANGLES, 0, vaoProgram.getPointCount());
    }

    public void rotateAbsolut (float deg) {
        glBindVertexArray(vaoProgram.vaoId);
        Matrix4 transformMatrix = new Matrix4();
        transformMatrix.rotateY(deg);
        //transformMatrix.rotateX(deg);
        //transformMatrix.rotateZ(deg);
        glUseProgram(shaderProgram.getId());
        int matrix_loc = glGetUniformLocation(shaderProgram.getId(), "transformMatrix");
        glUniformMatrix4fv(matrix_loc, true,transformMatrix.getValuesAsArray());
    }
}
