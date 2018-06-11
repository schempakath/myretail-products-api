package com.myretail.productsapi.exception;

public class ProductApiExceptions extends Exception{

	private static final long serialVersionUID = 1L;
	
	private String errorCode = "Unknown_Exception";
	
	public ProductApiExceptions(){		
	}

	public ProductApiExceptions (String erroMessage) {
		super(erroMessage);
	}
	
	public ProductApiExceptions (String errorCode, String erroMessage) {
		super(erroMessage);
		this.errorCode = errorCode;
	}
	
	public ProductApiExceptions(String message, Throwable cause){
		super(message, cause);
	}
	
	public ProductApiExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)	{
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}	
}
