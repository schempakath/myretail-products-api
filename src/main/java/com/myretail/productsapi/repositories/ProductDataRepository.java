package com.myretail.productsapi.repositories;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.myretail.productsapi.dao.ProductData;

@Repository
public class ProductDataRepository {
	
	@Autowired 
	MongoTemplate mongo;
	
	/**
	 * 
	 * @param productId
	 * @return ProductData
	 */
	public ProductData retrieveProduct(Integer productId) {
		Query query = new Query(Criteria.where("productId").is(productId));
		return mongo.findOne(query, ProductData.class);
	}
		
	/**
	 * @param productId
	 * @param productPrice
	 * @return ProductData
	 */
	public ProductData updatePrice(Integer productId, BigDecimal productPrice) {
		Query query = new Query(Criteria.where("productId").is(productId));
		mongo.updateFirst(query, Update.update("productPrice", productPrice), ProductData.class);
		return mongo.findOne(query, ProductData.class);
	}
	
	/**
	 * @param ProductData
	 * @return ProductData
	 */
	public ProductData addProductData(ProductData prdctData) {
		mongo.insert(prdctData);
		Query query = new Query(Criteria.where("productId").is(prdctData.getProductId()));
		return mongo.findOne(query, ProductData.class);
	}
}