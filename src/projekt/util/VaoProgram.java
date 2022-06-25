package projekt.util;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL30.*;

/**
 * Custom Class that helps managing content of a VAO
 */
public class VaoProgram {
    int glDrawType = GL_TRIANGLES;
    int vaoId;
    int pointCount = 0;
    Map<Integer, VboProgram> vboList = new HashMap<>();

    public VaoProgram (int glDrawType) {
        this();
        this.glDrawType = glDrawType;
    }

    public VaoProgram () {
        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);
    }

    public void createVbo(int attributeId, float[] data, int dataItemLength) {
        VboProgram newVbo = new VboProgram(attributeId, data, dataItemLength);
        vboList.put(attributeId, newVbo);
    }

    public void addToPointCount (int value) {
        pointCount += value;
    }

    public int getPointCount() {
        return pointCount;
    }
}
