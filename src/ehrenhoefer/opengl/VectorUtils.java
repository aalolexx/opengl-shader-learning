package ehrenhoefer.opengl;

public class VectorUtils {

    /**
     * Copied and slightly changed from: https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
     * Get the normal vector of 3 points
     * @param p0
     * @param p1
     * @param p2
     * @return normalVector
     */
    public static float[] getNormal(float[] p0, float[] p1, float[] p2) {
        float[] u = {
                p1[0] - p0[0],
                p1[1] - p0[1],
                p1[2] - p0[2]
        };
        float[] v = {
                p2[0] - p0[0],
                p2[1] - p0[1],
                p2[2] - p0[2]
        };
        return crossProduct(u, v);
    }

    /**
     * Copied and slightly changed from: https://www.demo2s.com/java/java-vector-get-the-normal-vector-of-3-points-that-lie-on-a-plane.html
     * Get the cross product of 2 vectors
     * @param u
     * @param v
     * @return crossproduct vector
     */
    public static float[] crossProduct(float u[], float v[]) {
        float[] crossProduct = new float[3];
        crossProduct[0] = u[1] * v[2] - u[2] * v[1];
        crossProduct[1] = u[2] * v[0] - u[0] * v[2];
        crossProduct[2] = u[0] * v[1] - u[1] * v[0];
        return crossProduct;
    }
}
