package com.myretail.productsapi.operations;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myretail.productsapi.beans.Price;
import com.myretail.productsapi.dao.ProductData;
import com.myretail.productsapi.repositories.ProductDataRepository;

@Component
public class ManageProductPrice {

	private Logger log = LogManager.getLogger(ManageProductPrice.class);
	
	@Autowired 
	ProductDataRepository  prdtDataRepo;
	
	/**
	 * Gets the product price from database for a given product id
	 * 
	 * @param productId
	 * @return price
	 */
	public Price getProductPrice(Integer productId) {
		ProductData prdtData = prdtDataRepo.retrieveProduct(productId);
		Price price = null;
		if(null != prdtData){
			price = new Price();
			price.setCurrencyCode(prdtData.getCurrencyCode());
			price.setValue(prdtData.getProductPrice());
			log.info("Current Product Price: " + price.getValue());
		}
		else{
			log.error("Product Id " + productId + " could not be found");
		}
		return price;
	}

	/**
	 * Update the product price to database
	 * 
	 * @param productId
	 * @param newPrice
	 * @return price
	 */
	public Price updatePrice(Integer productId, BigDecimal newPrice) {
		ProductData prdtData = prdtDataRepo.updatePrice(productId,newPrice);
		Price price = null;
		if(null != prdtData){
			price = new Price();
			price.setCurrencyCode(prdtData.getCurrencyCode());
			price.setValue(prdtData.getProductPrice());
			log.info("Updated Product Price: " + newPrice);
		}
		else{
			log.error("Product id " + productId + " could not be found");
		}
		return price;
	}
	
}
