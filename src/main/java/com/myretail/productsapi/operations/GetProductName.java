package com.myretail.productsapi.operations;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myretail.productsapi.properties.ProductsApiProperties;

@Component
public class GetProductName {
	
private static final Logger log = LogManager.getLogger(GetProductName.class);
	
	@Autowired 
	ProductsApiProperties props;

	/**
	 * Gets the product name by consuming the product API.
	 * 
	 * @param id
	 * @return productName
	 * @throws JSONException 
	 */
	public String getProductName(Integer id) throws JSONException {
		final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(props.getProductDetailsUrl());
		builder.path(id.toString());
		builder.queryParam("excludes", props.getProductDetailsUrlPendingFields());		
		RestTemplate restTemplate = new RestTemplate();
		long startTime = new Date().getTime();
		log.info("START - Invoking external API to get Product Name - "+ builder.build().toUriString());
		String responseJson = restTemplate.getForObject(builder.build().toUriString(), String.class);
		log.info("Response time to get Product Name from external API is "+(System.currentTimeMillis() - startTime )+"ms");
		log.info("END - Invoking external API to get Product Name");
		return processRestServiceResponse(responseJson);
	}
	
	/**
	 * Obtains product name from the JSON response of external API Get call
	 * 
	 * @param responseJson
	 * @return prdctName
	 * @throws JSONException 
	 */
	private String processRestServiceResponse (String responseJson) throws JSONException {
		String prdctName = "";
		if(null != responseJson && !responseJson.isEmpty()){
			JSONObject object = new JSONObject(responseJson);
			if(null != object && object.has("product")){
				JSONObject product = object.getJSONObject("product");
				if(null != product && product.has("item")){
					JSONObject item = product.getJSONObject("item");
					if(null != item && item.has("product_description")){
						JSONObject pdtDesc = item.getJSONObject("product_description");						
						if(null != pdtDesc && pdtDesc.has("title")){
							prdctName = pdtDesc.get("title").toString();								
						}
					}		
				}
			}
		}
		else
		{
			log.info("Inside processRestServiceResponse: response json is null");
		}
		return prdctName;
	}


}
