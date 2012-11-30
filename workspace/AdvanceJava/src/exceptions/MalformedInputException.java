package exceptions;

public class MalformedInputException extends Exception {
	private static final long serialVersionUID = -2873857770017375900L;

	public MalformedInputException(){
		super();
	}
	
	public MalformedInputException(String msg){
		super(msg);
	}
}
