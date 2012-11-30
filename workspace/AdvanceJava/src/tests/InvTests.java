package tests;

import static org.junit.Assert.*;
import logic.Logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.UserInterfaceDTO;

public class InvTests {

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
	public void test() {
		fail("Not yet implemented");
	}

}
