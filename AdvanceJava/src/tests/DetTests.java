package tests;

import logic.Logic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dto.UserInterfaceDTO;
import exceptions.LUComputationException;
import exceptions.MalformedInputException;

public class DetTests {

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
	public void test_det_4x4() throws MalformedInputException,
			LUComputationException {

		a = "1 2 1 3\n2 3 1 4\n1 4 3 2\n2 4 1 5";

		uiDTO.captureMatrixA(a);
		
		logic.computeDeterminant(uiDTO);
		
		Assert.assertEquals(-5, uiDTO.getDeterminant(), 0);
	}
}
