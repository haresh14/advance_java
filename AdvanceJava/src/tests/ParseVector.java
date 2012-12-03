package tests;

import logic.Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.UserInterfaceDTO;
import exceptions.MalformedInputException;

public class ParseVector {

	protected String b;
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
	public void test_capture4x4() throws MalformedInputException {
		b = "1 2 1 3";
		this.uiDTO.captureMatrixB(b);
	}
	
	@Test (expected = MalformedInputException.class)
	public void test_badDimenssions() throws MalformedInputException {
		b = "1 2 1 3\n1";
		this.uiDTO.captureMatrixB(b);
	}

	@Test (expected = MalformedInputException.class)
	public void test_wrongCharacter() throws MalformedInputException {
		b = "1 2 1 w 2";
		this.uiDTO.captureMatrixB(b);
	}

}
