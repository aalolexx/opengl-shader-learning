package projekt.util;

import lenz.opengl.ShaderProgram;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class VboProgram {
    public int shaderProgramId;
    public int vboId;
    public int vaoId;
    public int attributeId;
    public float[] data;

    public VboProgram(int shaderProgramId, int vaoId, int attributeId, float[] data, int dataItemLength) {
        this.shaderProgramId = shaderProgramId;
        this.data = data;
        this.attributeId = attributeId;

        //glUseProgram(shaderProgramId);
        glEnableVertexAttribArray(this.vboId);
        glBindVertexArray(vaoId);

        this.vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeId, dataItemLength, GL_FLOAT, false, 0, 0);
    }

    public float[] getData () {
        return this.data;
    }
}
