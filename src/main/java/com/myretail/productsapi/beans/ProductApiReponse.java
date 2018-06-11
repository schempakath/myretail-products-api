package com.myretail.productsapi.beans;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.productsapi.beans.Product;
import com.myretail.productsapi.beans.ProductApiErrorResponse;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ProductApiReponse {
	
	private Product product;
	
	@JsonProperty("error")
	private ProductApiErrorResponse error;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductApiErrorResponse getError() {
		return error;
	}

	public void setError(ProductApiErrorResponse error) {
		this.error = error;
	}
}
