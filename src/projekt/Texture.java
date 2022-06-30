package projekt;

import de.matthiasmann.twl.utils.PNGDecoder;
import lenz.opengl.ShaderProgram;
import org.lwjgl.system.MemoryUtil;
import projekt.util.VaoProgram;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL30.*;

public class Texture {
    VaoProgram vaoProgram;
    ShaderProgram shaderProgram;
    float[] texCoords;

    PNGDecoder decoder;
    ByteBuffer buffer;


    public Texture (String path, float[] texCoords, VaoProgram vaoProgram, ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
        this.texCoords = texCoords;
        this.vaoProgram = vaoProgram;

        try {
            decoder = new PNGDecoder(
                    new java.io.FileInputStream(new File(path))
            );
            buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();

            glEnable(GL_TEXTURE_2D);

            int textureId = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureId);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            glTexImage2D(
                    GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(),
                    decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer
            );

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glGenerateMipmap(GL_TEXTURE_2D);

            glActiveTexture(GL_TEXTURE0);

            setVBO();
            setUniform();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void setVBO () {
        glUseProgram(shaderProgram.getId());
        //glBindVertexArray(vaoProgram.vaoId);
        vaoProgram.createVbo(2, texCoords, 2);
    }

    private void setUniform () {
        int uni_loc = glGetUniformLocation(shaderProgram.getId(), "textureSampler");
        glUniform1i(uni_loc, 0); //TODO VALUE NOT 0
    }
}
