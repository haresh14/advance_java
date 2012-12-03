package tests;

import logic.Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.UserInterfaceDTO;
import exceptions.MalformedInputException;

public class ParseMatrix {

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
	public void test_capture4x4() throws MalformedInputException {
		a = "1 2 1 3\n2 3 1 4\n1 4 3 2\n2 4 1 5";
		this.uiDTO.captureMatrixA(a);
	}
	
	@Test (expected = MalformedInputException.class)
	public void test_badDimenssions() throws MalformedInputException {
		a = "1 2 1 3\n2 1 4\n1 4 3 2\n2 4 1 5";
		this.uiDTO.captureMatrixA(a);
	}

	@Test (expected = MalformedInputException.class)
	public void test_wrongCharacter() throws MalformedInputException {
		a = "1 2 1 3\n2 3 w 4\n1 4 3 2\n2 4 1 5";
		this.uiDTO.captureMatrixA(a);
	}
}
