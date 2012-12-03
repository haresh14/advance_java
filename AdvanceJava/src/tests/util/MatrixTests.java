package tests.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.IMatrix;

public abstract class MatrixTests {

	protected IMatrix matrix;
	
	@Before
	public abstract void setUp();

	@After
	public abstract void tearDown();
	
	@Test
	public void printTest(){
		System.out.println(this.matrix.toString());
	}
	
}
