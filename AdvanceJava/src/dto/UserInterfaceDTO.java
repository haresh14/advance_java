package dto;

import exceptions.MalformedInputException;

public class UserInterfaceDTO implements IUserInterfaceDTO {

	private double[][] aMatrix; // A matrix (Ax = b)
	private double[] bMatrix; // b vector (Ax = b)
	private double[][] lMatrix; // L matrix (A = LU)
	private double[][] uMatrix; // U matrix (A = LU)
	private int[] piv; // pivoting vector
	private double determinant;
	private double[][] iMatrix; // inv(A)

	/**
	 * Gives the matrix A
	 * 
	 * @return
	 */
	public double[][] getMatrixA() {
		return this.aMatrix;
	}

	/**
	 * Gives the vector B
	 * 
	 * @return
	 */
	public double[] getMatrixB() {
		return this.bMatrix;
	}

	/**
	 * Gives the matrix L of the LU factorization
	 * 
	 * @return
	 */
	public double[][] getMatrixL() {
		return this.lMatrix;
	}

	/**
	 * Gives the matrix U of the LU factorization
	 * 
	 * @return
	 */
	public double[][] getMatrixU() {
		return this.uMatrix;
	}

	/**
	 * Gives the LU pivoting vector
	 * 
	 * @return
	 */
	public int[] getPivotingVector() {
		return this.piv;
	}

	public double getDeterminant(){ return this.determinant; }
	
	public double [][] getMatrixInv() { return this.iMatrix; }
	/**
	 * Capture the matrix A from its string representation
	 * 
	 * @param s
	 *            string representation
	 * @return true if the operation finishes without any problem
	 * @throws MalformedInputException
	 */
	public boolean captureMatrixA(String s) throws MalformedInputException {

		boolean result = true;

		String[] rows = s.split("\n");
		int numRows = rows.length;
		int numCols = rows[0].split(" ").length;
		this.aMatrix = new double[numRows][numCols];

		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split(" ");
			if (cols.length != numCols)
				throw new MalformedInputException(
						"The matrix A has rows with different number of columns");
			for (int j = 0; j < cols.length; j++) {
				try {
					double value = Double.parseDouble(cols[j]);
					aMatrix[i][j] = value;
				} catch (NumberFormatException ex) {
					throw new MalformedInputException(
							"The matrix A contains non-numerical values");
				}
			}
		}

		return result;
	}

	/**
	 * Capture the matrix B from its string representation
	 * 
	 * @param s
	 *            string representation
	 * @return true if the operation finishes without any problem
	 * @throws MalformedInputException
	 */
	public boolean captureMatrixB(String s) throws MalformedInputException {

		boolean result = true;

		String[] rows = s.split("\n");
		if (rows.length > 1)
			throw new MalformedInputException(
					"The vector b must be of dimmension 1");

		String[] cols = rows[0].split(" ");
		int numCols = cols.length;
		this.bMatrix = new double[numCols];

		for (int j = 0; j < numCols; j++) {
			try {
				double value = Double.parseDouble(cols[j]);
				bMatrix[j] = value;
			} catch (NumberFormatException ex) {
				throw new MalformedInputException(
						"The vector b contains non-numerical values");
			}
		}

		return result;
	}

	public boolean setMatrixL(double[][] l) {
		if (l == null)
			this.lMatrix = null;
		else
			this.lMatrix = l.clone();
		return true;
	}

	public boolean setMatrixU(double[][] u) {
		if (u == null)
			this.uMatrix = null;
		else
			this.uMatrix = u.clone();
		return true;
	}

	public boolean setPivotingVector(int[] piv) {
		if (piv == null)
			this.piv = null;
		else
			this.piv = piv.clone();
		return true;
	}

	public boolean setDeterminant(double det){
		this.determinant = det;
		return true;
	}
	
	public boolean setMatrixInv(double [][] inv){
		if (inv == null)
			this.iMatrix = null;
		else
			this.iMatrix = inv.clone();
		return true;
	}
	
	/**
	 * Clean all the non-original matrices and vectors
	 */
	public void clear(){
		
		this.setMatrixL(null);
		this.setMatrixU(null);
		this.setPivotingVector(null);
		
	}
	
	/**
	 * Return a string which represents the matrix A
	 * 
	 * @return
	 */
	public String printMatrixA() {
		return this.printMatrix(aMatrix, "Original matrix", "\t", " ");
	}

	/**
	 * Return a string which represents the vector b
	 * 
	 * @return
	 */
	public String printVectorB() {
		return this.printVector(this.bMatrix, "Original vector", "\t", " ");
	}

	/**
	 * Return a string which represents the matrix L
	 * 
	 * @return
	 */
	public String printMatrixL() {
		return this.printMatrix(lMatrix, "Lower matrix", "\t", " ");
	}

	/**
	 * Return a string which represents the matrix U
	 * 
	 * @return
	 */
	public String printMatrixU() {
		return this.printMatrix(uMatrix, "Upper matrix", "\t", " ");
	}

	public String printVectorPivoting() {
		return this.printVector(this.piv, "Pivot array", "\t", " ");
	}

	public String printDeterminant(){
		return "Determinant = " + this.determinant + "\n";
	}
	
	public String printMatrixInv() {
		return this.printMatrix(iMatrix, "Inverse matrix", "\t", " ");
	}
	
	/**
	 * Return a string representation of the matrix given
	 * 
	 * @param matrix
	 *            to print
	 * @param title
	 *            of the matrix
	 * @param pre
	 *            characters at the beginning of each row
	 * @param mid
	 *            character/s between the values of the matrix
	 * @return
	 */
	private String printMatrix(double[][] matrix, String title, String pre,
			String mid) {

		String result = title + "\n";

		for (int i = 0; i < matrix.length; i++) {
			result += pre;
			for (int j = 0; j < matrix[i].length; j++) {
				result += "" + matrix[i][j] + mid;
			}
			result += "\n";
		}

		return result;

	}

	/**
	 * Return a string representation of the vector given
	 * 
	 * @param vector
	 *            to print
	 * @param title
	 *            of the vector
	 * @param pre
	 *            characters at the beginning of the row
	 * @param mid
	 *            character/s between the values of the vector
	 * @return
	 */
	private String printVector(double[] vector, String title, String pre,
			String mid) {

		String result = title + "\n";

		for (int i = 0; i < vector.length; i++) {
			result += pre;
			result += "" + vector[i] + mid;
		}
		result += "\n";

		return result;

	}
	
	/**
	 * Return a string representation of the vector given
	 * 
	 * @param vector
	 *            to print
	 * @param title
	 *            of the vector
	 * @param pre
	 *            characters at the beginning of the row
	 * @param mid
	 *            character/s between the values of the vector
	 * @return
	 */
	private String printVector(int[] vector, String title, String pre,
			String mid) {

		String result = title + "\n";

		for (int i = 0; i < vector.length; i++) {
			result += pre;
			result += "" + vector[i] + mid;
		}
		result += "\n";

		return result;

	}
}
