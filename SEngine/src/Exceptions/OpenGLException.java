package Exceptions;

public class OpenGLException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -362590618109883329L;

	public OpenGLException() {
		super();
	}
	
	public OpenGLException(String message) {
		super(message);
	}

	public OpenGLException(String message, Exception e) {
		super(message, e);		
	}
}
