package jp.ac.kyushu.argyle.impl.exception;

public class NoMatchMethodException extends DispatchException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoMatchMethodException(String str) {
		super("Can find method \""+ str + "\", you need to define it in the MethodList class.");
	}

}
