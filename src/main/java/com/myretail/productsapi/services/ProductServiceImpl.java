package com.myretail.productsapi.services;

import java.math.BigDecimal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myretail.productsapi.beans.Price;
import com.myretail.productsapi.beans.Product;
import com.myretail.productsapi.dao.ProductData;
import com.myretail.productsapi.operations.GetProductName;
import com.myretail.productsapi.operations.ManageProductPrice;
import com.myretail.productsapi.properties.ProductsApiProperties;
import com.myretail.productsapi.repositories.ProductDataRepository;
import com.myretail.productsapi.exception.ProductApiExceptions;

@Service
public class ProductServiceImpl implements ProductServices{

	private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);
	
	@Autowired 
	ProductDataRepository pdtDataRepo;
	
	@Autowired
	ProductsApiProperties props;
	
	@Autowired 
	GetProductName pdtName;
	
	@Autowired 
	ManageProductPrice managePrice;
	
	
	/**
	 * Get Product Data by passing product id
	 * 
	 * @param productId
	 * @return Product
	 * @throws ProductServiceException 
	 */
	public Product getProductDataById(Integer id) throws ProductApiExceptions{
		Product prdt = null;
		try{
			ExecutorService executor = Executors.newCachedThreadPool();
			
			// Get Product Name from External API
			Future<String> productNameCall  = executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {					
					return pdtName.getProductName(id);
				}
				
			});
			
			// Get Product Price from DB
			Future<Price> productPriceCall = executor.submit(new Callable<Price>() {
				@Override
				public Price call() throws Exception {
					return managePrice.getProductPrice(id);
				}
				
			});
			
			String productName = null; 
			Price price = null;
			
			try {
				productName = productNameCall.get();
				price = productPriceCall.get();
			} 
			catch (InterruptedException e) {
				log.error("Error getting product details", e);
				throw e;
			} 
			catch (ExecutionException ex) {
				log.error("Exception occured while trying to get the product details", ex);
			}
			
			executor.shutdown();
			
			prdt = new Product();
			prdt.setId(id);
			
			if(null == productName || null == price){
				log.error("Product Name and/or Price cannot be retrieved now");
				throw new ProductApiExceptions(props.getPrdctDataNotAvailCode(),props.getPrdctDataNotAvailMsg());
			}

			if(null != productName){
				prdt.setName(productName);
			}
			if(null != price){
				prdt.setCurrentPrice(price);
			}			
		}
		catch(ProductApiExceptions pae){
			throw pae;
		}
		catch(Exception ex){
			log.error("Exception occured while trying to get the product details" , ex);
			throw new ProductApiExceptions(props.getGenErrorCode(),props.getGenErrorMsg());
		}
		return prdt;
	}

	/**
	 * Updates the price for a product
	 * 
	 * @param id
	 * @param newPrice
	 * @throws ProductServiceException  
	 * @return Product
	 */
	public Product updateProductPrice(Integer id, BigDecimal newPrice) throws ProductApiExceptions {
		
		Product prdt = null;
		
		try{
			prdt = new Product();
			Price price = managePrice.updatePrice(id,newPrice);
			
			if(null == price){
				throw new ProductApiExceptions(props.getPrdctIdNotFoundCode(),props.getPrdctIdNotFoundMsg());
			}
			
			prdt.setId(id);
			prdt.setCurrentPrice(price);			
		} 
		catch(ProductApiExceptions pse){
			throw pse;
		} 
		catch(Exception ex){
			log.error("Error updating product price", ex);
			throw new ProductApiExceptions(props.getGenErrorCode(),props.getGenErrorMsg());
		}
		
		return prdt;
	}
	

	@Override
	public ProductData addProducts(ProductData product) {
		return pdtDataRepo.addProductData(product);
	}

}
