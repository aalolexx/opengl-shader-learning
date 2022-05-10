package projekt;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	public float[][] items;

	public Matrix4() {
		items = new float[][] {
				{1, 0, 0, 0},
				{0, 1, 0, 0},
				{0, 0, 1, 0},
				{0, 0, 0, 1},
		};
	}

	public Matrix4(Matrix4 copy) {
		// TODO neues Objekt mit den Werten von "copy" initialisieren
	}

	public Matrix4(float near, float far) {
		// TODO erzeugt Projektionsmatrix mit Abstand zur nahen Ebene "near" und Abstand zur fernen Ebene "far", ggf. weitere Parameter hinzufügen
	}

	public Matrix4 multiply(Matrix4 other) {
		// Code strongly inspired by https://www.baeldung.com/java-matrix-multiplication
		float[][] firstMatrix = other.items;
		float[][] secondMatrix = this.items;

		float[][] result = new float[firstMatrix.length][secondMatrix[0].length];
		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[row].length; col++) {
				result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
			}
		}

		this.items = result;
		return this;
	}

	public Matrix4 translate(float x, float y, float z) {
		Matrix4 translateMatrix = new Matrix4();
		translateMatrix.items[0][3] = x;
		translateMatrix.items[1][3] = y;
		translateMatrix.items[2][3] = z;
		return multiply(translateMatrix);
	}

	public Matrix4 scale(float uniformFactor) {
		// TODO gleichmäßige Skalierung um Faktor "uniformFactor" zu this hinzufügen
		return this;
	}

	public Matrix4 scale(float sx, float sy, float sz) {
		// TODO ungleichförmige Skalierung zu this hinzufügen
		return this;
	}

	public Matrix4 rotateX(float angle) {
		// TODO Rotation um X-Achse zu this hinzufügen
		return this;
	}

	public Matrix4 rotateY(float angle) {
		// TODO Rotation um Y-Achse zu this hinzufügen
		return this;
	}

	public Matrix4 rotateZ(float angle) {
		// TODO Rotation um Z-Achse zu this hinzufügen
		return this;
	}

	public float[] getValuesAsArray() {
		float[] matrixAsRow = new float[16];
		int i = 0;
		for (float[] column : items) {
			for (float item : column) {
				matrixAsRow[i] = item;
				i++;
			}
		}
		return matrixAsRow;
	}

	/*
	 * CLASS UTILS
	 */
	public void printMatrix () {
		System.out.println("Matrix: ");
		for (int y = 0; y < items.length; y++) {
			for (int x = 0; x < items.length; x++) {
				System.out.print(items[x][y] + " | ");
			}
			System.out.println();
		}
	}

	private float multiplyMatricesCell(float[][] firstMatrix, float[][] secondMatrix, int row, int col) {
		float cell = 0;
		for (int i = 0; i < secondMatrix.length; i++) {
			cell += firstMatrix[row][i] * secondMatrix[i][col];
		}
		return cell;
	}
}
