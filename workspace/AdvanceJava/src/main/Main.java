package main;

import logic.Logic;
import dto.UserInterfaceDTO;
import exceptions.LUComputationException;
import exceptions.MalformedInputException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// testing UserInterfaceDTO
		UserInterfaceDTO uiDTO = new UserInterfaceDTO();
		Logic logic = new Logic();

		// testing A
		String a;
		
		/*
		a = "1 2 1 3\n2 3 1 4\n1 4 3 2\n2 4 1 5";
		try {
			uiDTO.captureMatrixA(a);
		} catch (MalformedInputException e) {
			System.err.println("error");
			System.err.println(e.getMessage());
		}
		try {
			logic.computeInv(uiDTO);
			logic.computeDeterminant(uiDTO);
			System.out.println(uiDTO.printMatrixA());
			System.out.println(uiDTO.printMatrixL());
			System.out.println(uiDTO.printMatrixU());
			System.out.println(uiDTO.printMatrixInv());
			System.out.println(uiDTO.printDeterminant());
			System.out.println(uiDTO.printVectorPivoting());
		} catch (MalformedInputException e) {
			System.err.println(e.getMessage());
		} catch (LUComputationException e) {
			System.err.println(e.getMessage());
		}
		*/
	}

}
