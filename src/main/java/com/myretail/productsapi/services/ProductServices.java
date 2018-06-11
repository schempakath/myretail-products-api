package com.myretail.productsapi.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.myretail.productsapi.exception.ProductApiExceptions;
import com.myretail.productsapi.beans.Product;
import com.myretail.productsapi.dao.ProductData;


@Service
public interface ProductServices {
	
	public Product getProductDataById(Integer id) throws ProductApiExceptions;
	
	public Product updateProductPrice(Integer id, BigDecimal newPrice) throws ProductApiExceptions;
	
	public ProductData addProducts(ProductData product);

}
