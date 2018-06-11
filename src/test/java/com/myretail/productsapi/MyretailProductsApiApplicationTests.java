package com.myretail.productsapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myretail.productsapi.beans.ProductApiReponse;
import com.myretail.productsapi.controller.ProductController;
import com.myretail.productsapi.dao.ProductData;
import com.myretail.productsapi.repositories.ProductDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyretailProductsApiApplicationTests {

	@Autowired
	ProductDataRepository pdtDataRepo;
	
	@Autowired
	ProductController controller;
	
	// Get Product Data api call with valid product id
	@Test
	public void testGetProductDataWithValidId(){
		Integer id = 13860428;
		ProductApiReponse response = controller.getProductById(id);
		assertNotNull(response);
		assertNull(response.getError());
		assertNotNull(response.getProduct());
		assertEquals("13860428",response.getProduct().getId().toString());
		assertEquals("The Big Lebowski (Blu-ray)",response.getProduct().getName());
		assertEquals("53.99",response.getProduct().getCurrentPrice().getValue().toString());
		assertEquals("USD",response.getProduct().getCurrentPrice().getCurrencyCode());
	}
	
	//Update Product Price api call with valid product id
	@Test
	public void testUpdateProductPriceWithValidId(){
		Integer id = 13860428;
		ProductData request = new ProductData();
		request.setProductPrice(new BigDecimal("53.99"));
		ProductApiReponse response = controller.updatePrice(id, request);
		assertNotNull(response);
		assertNull(response.getError());
		assertNotNull(response.getProduct());
		assertEquals("13860428",response.getProduct().getId().toString());
		assertEquals("53.99",response.getProduct().getCurrentPrice().getValue().toString());
		response = controller.getProductById(id);
		assertNotNull(response);
		assertNull(response.getError());
		assertNotNull(response.getProduct());
		assertEquals("13860428",response.getProduct().getId().toString());
		assertEquals("53.99",response.getProduct().getCurrentPrice().getValue().toString());
	}
	
	//Product Id is not available in DB and/or in the external API - Get Product Data api call
	@Test
	public void testGetProductDataWithInvalidId(){
		Integer id = 138604281;
		ProductApiReponse response = controller.getProductById(id);
		assertNotNull(response);
		assertNotNull(response.getError());
		assertEquals("500", response.getError().getErrorCode());
		assertEquals("Product Name and/or Price cannot be retrieved now", response.getError().getErrorMessage());		
	}
	
	//Product Id is not available in DB and in the external API -  - Update Product Price api call
	@Test
	public void testUpdateProductPriceWithInvalidId(){
		Integer id = 1234567;
		ProductData request = new ProductData();
		request.setProductPrice(new BigDecimal("53.99"));
		ProductApiReponse response = controller.updatePrice(id, request);
		assertNotNull(response);
		assertNull(response.getProduct());
		assertNotNull(response.getError());
		assertEquals("404", response.getError().getErrorCode());
		assertEquals("Product Id could not be found", response.getError().getErrorMessage());
	}
	
	//new price is missing in the request for Update Product Price
	@Test
	public void testUpdateProductPriceWithInvalidRequest(){
		Integer id = 13860428;
		ProductData request = new ProductData();
		ProductApiReponse response = controller.updatePrice(id, request);
		assertNotNull(response);
		assertNull(response.getProduct());
		assertNotNull(response.getError());
		assertEquals("400", response.getError().getErrorCode());
		assertEquals("Request is not valid", response.getError().getErrorMessage());
	}
	
	

}
