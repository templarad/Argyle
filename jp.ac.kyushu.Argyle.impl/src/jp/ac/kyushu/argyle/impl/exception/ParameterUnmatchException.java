package jp.ac.kyushu.argyle.impl.exception;

public class ParameterUnmatchException extends DispatchException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7045458471527644424L;

	public ParameterUnmatchException(String DSL) {
		super("Parameters from DSL("+DSL+ ") do not match those are implemented in the tool.");
	}
	
}
