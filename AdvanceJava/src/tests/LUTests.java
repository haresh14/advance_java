package tests;

import logic.Logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dto.UserInterfaceDTO;
import exceptions.LUComputationException;
import exceptions.MalformedInputException;

public class LUTests {

	protected String a;
	protected UserInterfaceDTO uiDTO;
	protected Logic logic;

	@Before
	public void setUp() throws Exception {
		uiDTO = new UserInterfaceDTO();
		logic = new Logic();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_lu_2x2_without() throws MalformedInputException,
			LUComputationException {
		
		a = "4 3\n6 3";
		
		uiDTO.captureMatrixA(a);
		
		logic.computeLUWithoutPivoting(uiDTO);
		
		double[][] resultL = { { 1, 0 }, { 1.5, 1 } };
		double[][] resultU = { { 4, 3 }, { 0, -1.5 } };

		Assert.assertArrayEquals(uiDTO.getMatrixL(), resultL);
		Assert.assertArrayEquals(uiDTO.getMatrixU(), resultU);
	}

	@Test
	public void test_lu_3x3_with() throws MalformedInputException,
			LUComputationException {
		
		a = "1 1 -2\n1 5 1\n1 3 -1";
		
		uiDTO.captureMatrixA(a);
		
		logic.computeLUWithPivoting(uiDTO);
		
		double[][] resultL = { { 1, 0, 0 }, { 1, 1, 0 }, { 1, 0.5, 1 } };
		double[][] resultU = { { 1, 1, -2 }, { 0, 4, 3 }, { 0, 0, -0.5 } };

		Assert.assertArrayEquals(uiDTO.getMatrixL(), resultL);
		Assert.assertArrayEquals(uiDTO.getMatrixU(), resultU);
	}

	@Test
	public void test_lu_4x4_with() throws MalformedInputException,
			LUComputationException {
		
		a = "1 2 1 3\n2 3 1 4\n1 4 3 2\n2 4 1 5";
		
		uiDTO.captureMatrixA(a);
		
		logic.computeLUWithPivoting(uiDTO);
		
		double[][] resultL = { { 1, 0, 0, 0 }, { 0.5, 1, 0, 0 },
				{ 1, 0.4, 1, 0 }, { 0.5, 0.2, -0.0, 1 } };
		double[][] resultU = { { 2, 3, 1, 4 }, { 0, 2.5, 2.5, 0 },
				{ 0, 0, -1, 1 }, { 0, 0, -0, 1 } };

		Assert.assertArrayEquals(uiDTO.getMatrixL(), resultL);
		Assert.assertArrayEquals(uiDTO.getMatrixU(), resultU);
	}

}
