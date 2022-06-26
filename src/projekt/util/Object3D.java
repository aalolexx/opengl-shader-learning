package projekt.util;

import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public class Object3D
{
    public VaoProgram vaoProgram;
    public ShaderProgram shaderProgram;

    public Object3D(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
        vaoProgram = new VaoProgram();
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
        //transformMatrix.rotateX(deg);
        //transformMatrix.rotateZ(deg);
        int matrix_loc = glGetUniformLocation(shaderProgram.getId(), "transformMatrix");
        glUniformMatrix4fv(matrix_loc, true,transformMatrix.getValuesAsArray());
    }
}
