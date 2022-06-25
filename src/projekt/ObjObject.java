package projekt;

import lenz.opengl.ShaderProgram;
import projekt.util.Object3D;
import projekt.util.Vector3D;

import java.util.ArrayList;
import java.util.List;

public class ObjObject extends Object3D {
    public List<Vector3D> vertices = new ArrayList<>();
    public List<Vector3D> normals = new ArrayList<>();
    public List<Vector3D> facesVertices = new ArrayList<>();
    public List<Vector3D> facesNormals = new ArrayList<>();
    public int pointCount = 0;

    public ObjObject(ShaderProgram shaderProgram) {
        super(shaderProgram);
    }

    /**
     * Add Vertice to object
     * @param x
     * @param y
     * @param z
     */
    public void addVertice(float x, float y, float z) {
        vertices.add(new Vector3D(x, y, z));
    }

    /**
     * Add Normal to object
     * @param x
     * @param y
     * @param z
     */
    public void addNormal(float x, float y, float z) {
        normals.add(new Vector3D(x, y, z));
    }

    /**
     * Create a Face by a given String in the format vertice/texture/normal
     * a, b and c are the Face corners
     * @param a
     * @param b
     * @param c
     */
    public void addFace(String a, String b, String c) {
        String[] aParts = a.split("/");
        String[] bParts = b.split("/");
        String[] cParts = c.split("/");

        facesVertices.add(
                new Vector3D(Integer.parseInt(aParts[0]),
                             Integer.parseInt(bParts[0]),
                             Integer.parseInt(cParts[0])
                )
        );

        facesNormals.add(
                new Vector3D(Integer.parseInt(aParts[2]),
                             Integer.parseInt(bParts[2]),
                             Integer.parseInt(cParts[2])
                )
        );

        pointCount += 9;
    }

    /* ****************
     * Open GL specific Functions
     **************** */

    /**
     * Write all the object data to the VBO
     */
    public void writeVBO () {
        vaoProgram.createVbo(0, getCoordinatesArray(), 3);
        System.out.println(getCoordinatesArray().length + " " + getPointCount() * 3);
        vaoProgram.addToPointCount(getPointCount());
        vaoProgram.createVbo(1, getNormalsArray(), 3);
    }

    /* ****************
     * Data Read Functions
     **************** */

    public int getPointCount () {
        return pointCount;
    }

    public float[] getCoordinatesArray () {
        ArrayList<Float> allCoordinates = new ArrayList<>();
        for (int i = 0 ; i < facesVertices.size(); i++) {
            Vector3D a = vertices.get((int) (facesVertices.get(i).x) - 1);
            allCoordinates.add(a.x);
            allCoordinates.add(a.y);
            allCoordinates.add(a.z);

            Vector3D b = vertices.get((int) (facesVertices.get(i).y) - 1);
            allCoordinates.add(b.x);
            allCoordinates.add(b.y);
            allCoordinates.add(b.z);

            Vector3D c = vertices.get((int) (facesVertices.get(i).z) - 1);
            allCoordinates.add(c.x);
            allCoordinates.add(c.y);
            allCoordinates.add(c.z);
        }
        return listToFloatArray(allCoordinates);
    }

    public float[] getNormalsArray () {
        ArrayList<Float> allCoordinates = new ArrayList<>();
        for (int i = 0 ; i < facesNormals.size(); i++) {
            Vector3D a = normals.get((int) (facesNormals.get(i).x) - 1);
            allCoordinates.add(a.x);
            allCoordinates.add(a.y);
            allCoordinates.add(a.z);

            Vector3D b = normals.get((int) (facesNormals.get(i).y) - 1);
            allCoordinates.add(b.x);
            allCoordinates.add(b.y);
            allCoordinates.add(b.z);

            Vector3D c = normals.get((int) (facesNormals.get(i).z) - 1);
            allCoordinates.add(c.x);
            allCoordinates.add(c.y);
            allCoordinates.add(c.z);
        }
        return listToFloatArray(allCoordinates);
    }

    private float[] listToFloatArray (ArrayList<Float> list) {
        float[] floatArray = new float[list.size()];
        for (int i = 0 ; i < list.size(); i++) {
            floatArray[i] = list.get(i);
        }
        return floatArray;
    }
}
