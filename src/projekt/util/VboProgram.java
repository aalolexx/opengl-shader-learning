package projekt.util;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class VboProgram {
    public int vboId;
    public int attributeId;
    public float[] data;

    public VboProgram(int attributeId, float[] data, int dataItemLength) {
        this.data = data;
        this.attributeId = attributeId;

        this.vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, this.vboId);
        glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
        glVertexAttribPointer(attributeId, dataItemLength, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(attributeId);
    }

    public float[] getData () {
        return this.data;
    }
}
