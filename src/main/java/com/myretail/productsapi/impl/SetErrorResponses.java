package com.myretail.productsapi.impl;

import org.springframework.stereotype.Component;

import com.myretail.productsapi.beans.ProductApiErrorResponse;
import com.myretail.productsapi.exception.ProductApiExceptions;

@Component
public class SetErrorResponses {

	/**
	 * Sets the error response from ProductApiExceptions
	 * 
	 * @param pse
	 * @return error
	 */
	public ProductApiErrorResponse setErrorResponse(ProductApiExceptions pse) {
		ProductApiErrorResponse error = new ProductApiErrorResponse();
		error.setErrorCode(pse.getErrorCode());
		error.setErrorMessage(pse.getMessage());
		return error;
	}
	
	/**
	 * Sets the error response from Exception
	 * 
	 * @param ex
	 * @return error
	 */
	public ProductApiErrorResponse setErrorResponse(Exception ex) {
		ProductApiErrorResponse error = new ProductApiErrorResponse();
		error.setErrorMessage(ex.getMessage());
		return error;
	}

}
