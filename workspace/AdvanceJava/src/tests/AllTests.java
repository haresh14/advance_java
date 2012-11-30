package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.util.Matrix1Launcher;

@RunWith(Suite.class)
@SuiteClasses({  
	Matrix1Launcher.class,/*
	ParseMatrix.class, 
	ParseVector.class, 
	LUTests.class,
	InvTests.class,
	DetTests.class*/
	})
public class AllTests {

}
