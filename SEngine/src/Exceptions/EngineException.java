package Exceptions;

public class EngineException extends RuntimeException {
	
	public EngineException(String message) {
		super(message);
	}
	
	public EngineException(String message, Throwable e) {
		super(message, e);
	}
	
}
