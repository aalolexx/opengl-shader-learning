package projekt.util;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL30.*;

/**
 * Custom Class that helps managing content of a VAO
 */
public class VaoProgram {
    int shaderProgramId;
    int vaoId;
    int pointCount = 0;
    Map<Integer, VboProgram> vboList = new HashMap<>();

    public VaoProgram (int shaderProgramId) {
        this.vaoId = glGenVertexArrays();
        this.shaderProgramId = shaderProgramId;
    }

    public void createVbo(int attributeId, float[] data, int dataItemLength) {
        VboProgram newVbo = new VboProgram(shaderProgramId, this.vaoId, attributeId, data, dataItemLength);
        vboList.put(attributeId, newVbo);
    }

    public void addToPointCount (int value) {
        pointCount += value;
    }

    public int getPointCount() {
        return pointCount;
    }
}
