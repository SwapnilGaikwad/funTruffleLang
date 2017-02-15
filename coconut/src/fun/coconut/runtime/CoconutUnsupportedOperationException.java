package fun.coconut.runtime;

public class CoconutUnsupportedOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	public CoconutUnsupportedOperationException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
