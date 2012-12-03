package dto;

import exceptions.MalformedInputException;

public interface IUserInterfaceDTO {

	public double[][] getMatrixA();

	public double[] getMatrixB();

	public double[][] getMatrixL();

	public double[][] getMatrixU();

	public int[] getPivotingVector();
	
	public double getDeterminant();
	
	public double [][] getMatrixInv();

	
	public boolean captureMatrixA(String s) throws MalformedInputException;

	public boolean captureMatrixB(String s) throws MalformedInputException;

	public boolean setMatrixL(double[][] l);

	public boolean setMatrixU(double[][] u);

	public boolean setPivotingVector(int[] piv);
	
	public boolean setDeterminant(double det);
	
	public boolean setMatrixInv(double [][] inv);
	
	public void clear();

	
	public String printMatrixA();

	public String printVectorB();

	public String printMatrixL();

	public String printMatrixU();

	public String printVectorPivoting();
	
	public String printDeterminant();
	
	public String printMatrixInv();

}
