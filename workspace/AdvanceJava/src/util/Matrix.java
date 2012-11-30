package util;

import java.util.Iterator;

public class Matrix implements IMatrix {

	private Double[][] matrix;

	/* Constructors */
	/**
	 * Dimension constructor
	 * 
	 * @param numRows
	 * @param numCols
	 */
	public Matrix(int numRows, int numCols) {
		super();
		this.matrix = new Double[numRows][numCols];
	}

	/**
	 * Constructor from an array
	 * 
	 * @param m
	 */
	public Matrix(Double[][] m) {
		super();
		int nRows = m.length;
		int nCols = m[0].length;
		this.matrix = new Double[nRows][nCols];
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				this.matrix[i][j] = m[i][j];
			}
		}
	}

	/**
	 * Copy constructor
	 */
	public Matrix(Matrix m) {
		super();
		int nRows = m.getNumRows();
		int nCols = m.getNumColumns();
		this.matrix = new Double[nRows][nCols];
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				this.set(i, j, m.get(i, j));
			}
		}
	}

	/* Getters & setters */
	/**
	 * Return the number of rows the matrix has
	 * 
	 * @return 0 if the matrix is not initialised
	 */
	public int getNumRows() {
		if (this.matrix == null)
			return 0;
		;
		return this.matrix.length;
	}

	/**
	 * Return the number of columns the matrix has
	 * 
	 * @return 0 if the matrix is not initialised
	 */
	public int getNumColumns() {
		if (this.matrix == null)
			return 0;
		return this.matrix[0].length;

	}

	/**
	 * Get the value in the (i,j) cell
	 * 
	 * @param i
	 *            row
	 * @param j
	 *            column
	 * @return
	 */
	public Double get(int i, int j) {
		return this.matrix[i][j];
	}

	/**
	 * Set the value of the cell (i,j)
	 * 
	 * @param i
	 *            row
	 * @param j
	 *            column
	 * @param value
	 *            new value of the cell
	 */
	public void set(int i, int j, double value) {
		this.matrix[i][j] = value;
	}

	/* Operations */
	public Matrix mult(Matrix m) {
		if (m == null || this.getNumColumns() != m.getNumRows())
			throw new UnsupportedOperationException("");
		int nRows = this.getNumRows();
		int nCols = m.getNumColumns();
		Matrix result = new Matrix(nRows, nCols);

		for (int i = 0; i < nCols; i++) {
			for (int j = 0; j < nCols; j++) {
				Double acc = 0.;
				Iterator<Double> itR = this.getRowIterator(i);
				Iterator<Double> itC = m.getColumnIterator(j);
				while (itR.hasNext()) {
					acc += itR.next() * itC.next();
				}
			}
		}

		return result;
	}

	public boolean equals(Matrix m) {
		if (m == null || this.getNumColumns() != m.getNumColumns()
				|| this.getNumColumns() != m.getNumColumns())
			return false;
		for(int i = 0; i < this.getNumRows(), i ++){
			Iterator it1 = this.getRowIterator(i);			
		}
	}

	/* Iterators */
	/**
	 * Returns an Iterator for the specific row
	 * 
	 * @param row
	 * @return
	 */
	public Iterator<Double> getRowIterator(int row) {
		return new RowIterator(row);
	}

	/**
	 * Return an Iterator for the specific column
	 * 
	 * @param col
	 * @return
	 */
	public Iterator<Double> getColumnIterator(int col) {
		return new ColumnIterator(col);
	}

	/**
	 * Iterator to iterate throw a determined row
	 * 
	 */
	public class RowIterator implements Iterator<Double> {

		/**
		 * Row to iterate throw
		 */
		private int row;

		/**
		 * Current column
		 */
		private int column;

		/**
		 * Constructor
		 * 
		 * @param row
		 *            that will be iterated
		 */
		public RowIterator(int row) {
			super();
			this.row = row;
			this.column = 0;
		}

		@Override
		public boolean hasNext() {
			return this.column < getNumColumns();
		}

		@Override
		public Double next() {
			this.column++;
			return matrix[row][column - 1];
		}

		@Override
		public void remove() {
			// no sense
		}

	}

	/**
	 * Iterator to iterate throw a determined row
	 * 
	 */
	public class ColumnIterator implements Iterator<Double> {

		/**
		 * current row
		 */
		private int row;

		/**
		 * Column to iterate throw
		 */
		private int column;

		/**
		 * Constructor
		 * 
		 * @param row
		 *            that will be iterated
		 */
		public ColumnIterator(int column) {
			super();
			this.row = 0;

			if (column < 0)
				this.column = column;
			else if (column >= getNumColumns())
				this.column = getNumColumns() - 1;
			else
				this.column = column;
		}

		@Override
		public boolean hasNext() {
			return this.row < getNumRows();
		}

		@Override
		public Double next() {
			this.row++;
			return matrix[row - 1][column];
		}

		@Override
		public void remove() {
			// no sense
		}

	}

	/* Util */
	public String toString() {
		String result = "";

		for (int i = 0; i < this.getNumRows(); i++) {
			Iterator<Double> it = this.getRowIterator(i);
			while (it.hasNext()) {
				result += it.next() + "\t";
			}
			result += "\n";
		}

		return result;

	}

}
