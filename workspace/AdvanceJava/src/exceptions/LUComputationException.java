package exceptions;

public class LUComputationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2873857770017375900L;

	public LUComputationException(){
		super();
	}
	
	public LUComputationException(String msg){
		super(msg);
	}
}
