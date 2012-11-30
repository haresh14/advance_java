package logic;

import dto.IUserInterfaceDTO;
import exceptions.LUComputationException;
import exceptions.MalformedInputException;

public class Logic {

	/**
	 * Computes the LU factorization without pivoting of the A matrix embedded
	 * in the UserInterfaceDTO
	 * 
	 * @param uiDTO
	 *            parameters given by the user using the user interface
	 * @throws MalformedInputException
	 *             if the matrix is not nxn
	 * @throws LUComputationException
	 *             if LU factorization is not possible
	 */
	public void computeLUWithoutPivoting(IUserInterfaceDTO uiDTO)
			throws MalformedInputException, LUComputationException {

		uiDTO.clear();

		int n = uiDTO.getMatrixA().length;
		if (uiDTO.getMatrixA()[0].length != n)
			throw new MalformedInputException("The matrix A is not nxn");

		double[][] temp = new double [n][n];
		double mult;
		int i, j, k;

		// create a temp matrix
		for (i = 0; i < n; i ++)
			for (j = 0; j < n; j ++)
				temp[i][j] = uiDTO.getMatrixA()[i][j];

		// LU (Doolittle's) decomposition without pivoting
		for (k = 0; k < n - 1; k++) {
			for (i = k + 1; i < n; i++) {
				if (temp[k][k] == 0)
					throw new LUComputationException("Pivot is 0");

				mult = temp[i][k] / temp[k][k];
				temp[i][k] = mult; // entries of L are saved in temp
				for (j = k + 1; j < n; j++) {
					temp[i][j] -= mult * temp[k][j]; // entries of U are saved
														// in temp
				}
			}
		}

		// create l and u from temp
		double[][] l = new double[n][n];
		for (i = 0; i < n; i++)
			l[i][i] = 1.0;
		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++)
				l[i][j] = temp[i][j];
			for (j = i + 1; j < n; j++)
				l[i][j] = 0;
		}
		uiDTO.setMatrixL(l);
		double[][] u = new double[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < i; j++)
				u[i][j] = 0;
			for (j = i; j < n; j++)
				u[i][j] = temp[i][j];
		}
		uiDTO.setMatrixU(u);

	}

	/**
	 * Computes the LU factorization without pivoting of the A matrix embedded
	 * in the UserInterfaceDTO
	 * 
	 * @param uiDTO
	 *            parameters given by the user using the user interface
	 * @throws MalformedInputException
	 *             if the matrix is not nxn
	 * @throws LUComputationException
	 *             if LU factorization is not possible
	 */
	public void computeLUWithPivoting(IUserInterfaceDTO uiDTO)
			throws MalformedInputException, LUComputationException {

		int n = uiDTO.getMatrixA().length;
		if (uiDTO.getMatrixA()[0].length != n)
			throw new MalformedInputException("The matrix A is not nxn");
		
		uiDTO.clear();

		double[][] temp = new double[n][n];
		for (int i = 0; i < n; i ++)
			for (int j = 0; j < n; j ++)
				temp[i][j] = uiDTO.getMatrixA()[i][j];
		
		int[] piv = new int[n];
		for (int i = 0; i < n; i++) {
			piv[i] = i;
		}
		int pivsign = 1;
		double[] row;
		double[] col = new double[n];

		for (int j = 0; j < n; j++) {

			for (int i = 0; i < n; i++) {
				col[i] = temp[i][j];
			}

			for (int i = 0; i < n; i++) {
				row = temp[i];

				int kmax = Math.min(i, j);
				double s = 0.0;
				for (int k = 0; k < kmax; k++) {
					s += row[k] * col[k];
				}

				row[j] = col[i] -= s;
			}

			// Find best pivot and exchange if it is necessary.

			int p = j;
			for (int i = j + 1; i < n; i++) {
				if (Math.abs(col[i]) > Math.abs(col[p])) {
					p = i;
				}
			}
			if (p != j) {
				for (int k = 0; k < n; k++) {
					double t = temp[p][k];
					temp[p][k] = temp[j][k];
					temp[j][k] = t;
				}
				int k = piv[p];
				piv[p] = piv[j];
				piv[j] = k;
				pivsign = -pivsign;
			}

			if (j < n & temp[j][j] != 0.0) {
				for (int i = j + 1; i < n; i++) {
					temp[i][j] /= temp[j][j];
				}
			}
		}

		// create l and u from temp
		int i, j;
		double[][] l = new double[n][n];
		for (i = 0; i < n; i++)
			l[i][i] = 1.0;
		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++)
				l[i][j] = temp[i][j];
			for (j = i + 1; j < n; j++)
				l[i][j] = 0;
		}
		uiDTO.setMatrixL(l);
		double[][] u = new double[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < i; j++)
				u[i][j] = 0;
			for (j = i; j < n; j++)
				u[i][j] = temp[i][j];
		}
		uiDTO.setMatrixU(u);
		uiDTO.setPivotingVector(piv);
	}

	public void computeDeterminant(IUserInterfaceDTO uiDTO)
			throws MalformedInputException, LUComputationException {

		if (uiDTO.getMatrixL() == null && uiDTO.getMatrixU() == null)
			this.computeLUWithPivoting(uiDTO);

		double result = 1.0;

		for (int i = 0; i < uiDTO.getMatrixL().length; i++) {
			result *= uiDTO.getMatrixL()[i][i] * uiDTO.getMatrixU()[i][i];
		}

		uiDTO.setDeterminant(result);

	}

	public void computeInv(IUserInterfaceDTO uiDTO)
			throws MalformedInputException, LUComputationException {

		if (uiDTO.getMatrixL() == null && uiDTO.getMatrixU() == null)
			this.computeLUWithPivoting(uiDTO);

		int n = uiDTO.getMatrixA().length;
		double[][] ident = new double[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r == c)
					ident[r][c] = 1;
				else
					ident[r][c] = 0;
			}
		}

		// L -> inv(L)
		double[][] l = new double [n][n];
		for (int i = 0; i < n; i ++)
			for (int j = 0; j < n; j ++)
				l[i][j] = uiDTO.getMatrixL()[i][j];
		
		double[][] invL = new double [n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r == c)
					invL[r][c] = 1;
				else
					invL[r][c] = 0;
			}
		}
		for (int c = 0; c < n; c++) {
			for (int r = c + 1; r < n; r++) {
				invL[r][c] += -1 * l[r][c];
			}
		}
		
		// U -> inv(U)
		double[][] u = new double [n][n];
		for (int i = 0; i < n; i ++)
			for (int j = 0; j < n; j ++)
				u[i][j] = uiDTO.getMatrixU()[i][j];
		double[][] invU = new double [n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (r == c)
					invU[r][c] = 1;
				else
					invU[r][c] = 0;
			}
		}
		for (int c = n-1; c >= 0; c--) {
			invU[c][c] = 1;
			for (int r = c - 1; r >= 0; r--) {
				invL[r][c] += -1 * l[r][c];
			}
		}
		
		// inv(A) = inv(U)*inv(L)
		for (int i = 0; i < n; i ++){
			for (int j = 0; j < n; j ++){
				for (int k = 0; k < n; k++){
					ident[i][j] += invU[i][k]*invL[k][j];
				}
			}
		}
		
		uiDTO.setMatrixInv(ident);
	}
}
