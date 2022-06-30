package projekt.util;

import de.matthiasmann.twl.utils.PNGDecoder;
import lenz.opengl.ShaderProgram;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import projekt.ObjObject;

import java.io.*;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class ObjLoader {
    public static ObjObject load3DModel(String objFileName, ShaderProgram shaderProgram, float scaleFactor) {
        ObjObject mesh = new ObjObject(shaderProgram);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(objFileName)));
        } catch (FileNotFoundException e) {
            System.err.println("MTL File not found: " + objFileName);
        }

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] lineParts = line.split(" ");
                switch (line.substring(0, 2)) {
                    case "v ":
                        mesh.addVertice(
                                Float.parseFloat(lineParts[1]) * scaleFactor,
                                Float.parseFloat(lineParts[2]) * scaleFactor,
                                Float.parseFloat(lineParts[3]) * scaleFactor
                        );
                        break;
                    case "vn":
                        mesh.addNormal(
                                Float.parseFloat(lineParts[1]),
                                Float.parseFloat(lineParts[2]),
                                Float.parseFloat(lineParts[3])
                        );
                        break;
                    case "f ":
                        mesh.addFace(
                                lineParts[1],
                                lineParts[2],
                                lineParts[3]
                        );
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mesh.writeVBO();
        return mesh;
    }
}
