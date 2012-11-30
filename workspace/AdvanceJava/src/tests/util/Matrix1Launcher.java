package tests.util;

import org.junit.After;
import org.junit.Before;

import util.Matrix;

public class Matrix1Launcher extends MatrixTests {

	@Override
	@Before
	public void setUp() {
		Double[][] m =  { { 1.0, 0.0 }, { 1.5, 1.0 } };
		this.matrix = new Matrix(m);
	}

	@Override
	@After
	public void tearDown() {}

}
