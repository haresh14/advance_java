package exceptions;

public class MatrixNotInitialisedException extends Exception {

	private static final long serialVersionUID = -3433556226317410971L;

	public MatrixNotInitialisedException(){
		super();
	}
	
	public MatrixNotInitialisedException(String msg){
		super(msg);
	}

}
