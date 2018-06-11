package com.myretail.productsapi.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.productsapi.exception.ProductApiExceptions;
import com.myretail.productsapi.impl.SetErrorResponses;
import com.myretail.productsapi.dao.ProductData;
import com.myretail.productsapi.properties.ProductsApiProperties;
import com.myretail.productsapi.beans.ProductApiReponse;
import com.myretail.productsapi.services.ProductServices;

@RestController
public class ProductController {
	
	private static final Logger log = LogManager.getLogger(ProductController.class);
	
	@Autowired
    private ProductServices prdctService;
	
	@Autowired
	ProductsApiProperties properties;
	
	@Autowired
	SetErrorResponses ser;
	
	@ResponseBody
    @RequestMapping(value = "/myretail/p/products/{id}", method = RequestMethod.GET)
    public ProductApiReponse getProductById(@PathVariable Integer id) {
		log.info("Invoking getProductById () with id as "+ id);
		long startTime = new Date().getTime();
		ProductApiReponse apiResponse = new ProductApiReponse();
		try {
			apiResponse.setProduct(prdctService.getProductDataById(id));
		} catch (ProductApiExceptions pse) {
			apiResponse.setError(ser.setErrorResponse(pse));
		} catch(Exception ex) {
			apiResponse.setError(ser.setErrorResponse(ex));
		}
		log.info("Response time for the service - getProductById - is "+(System.currentTimeMillis() - startTime )+"ms");
		return apiResponse;
    }
	
	@ResponseBody
    @RequestMapping(value = "/myretail/p/products/{id}", method = RequestMethod.PUT)
	public ProductApiReponse updatePrice(@PathVariable("id") Integer id, @RequestBody ProductData prdctData) {
		log.info("Invoking updatePrice () with id as " + id);
		long startTime = new Date().getTime();
		ProductApiReponse apiResponse = new ProductApiReponse();
		try {
			//Validate the request
			if(null == prdctData || null == prdctData.getProductPrice()){
				log.error("Input is not valid");
				throw new ProductApiExceptions(properties.getInvalidRequestCode(), properties.getInvalidRequestMsg());
			}
			apiResponse.setProduct(prdctService.updateProductPrice(id,prdctData.getProductPrice()));
		} catch (ProductApiExceptions pse) {
			apiResponse.setError(ser.setErrorResponse(pse));
		} catch (Exception ex){
			apiResponse.setError(ser.setErrorResponse(ex));
		}
		log.info("Response time for the service - updatePrice - is "+(System.currentTimeMillis() - startTime )+"ms");
		return apiResponse;
    }
	
	
	@ResponseBody
    @RequestMapping(value = "/myretail/p/products", method = RequestMethod.POST)
    public ProductData addProduct(@RequestBody ProductData prdctData) {
        return prdctService.addProducts(prdctData);
    }    

}
