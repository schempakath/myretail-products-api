package com.myretail.productsapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * 
 * Properties file for myRetail Products API
 * Properties can be modified at run time using JCONSOLE
 */

@Configuration
@ConfigurationProperties(prefix = "myretail.products.api")
@ManagedResource(objectName = "myretail.products.api.config:name=ProductsApiProperties", description = "myRetail Product API Properties")
public class ProductsApiProperties {
	
	private String productDetailsUrl;
	
	private String productDetailsUrlPendingFields;
	
	private String prdctIdNotFoundCode;
	
	private String prdctIdNotFoundMsg;
	
	private String invalidRequestCode;
	
	private String invalidRequestMsg;
	
	private String genErrorCode;
	
	private String genErrorMsg;
	
	private String prdctDataNotAvailCode;
	
	private String prdctDataNotAvailMsg;

	@ManagedAttribute
	public String getProductDetailsUrl() {
		return productDetailsUrl;
	}

	@ManagedAttribute
	public void setProductDetailsUrl(String productDetailsUrl) {
		this.productDetailsUrl = productDetailsUrl;
	}
	
	@ManagedAttribute
	public String getProductDetailsUrlPendingFields() {
		return productDetailsUrlPendingFields;
	}

	@ManagedAttribute
	public void setProductDetailsUrlPendingFields(String productDetailsUrlPendingFields) {
		this.productDetailsUrlPendingFields = productDetailsUrlPendingFields;
	}

	@ManagedAttribute
	public String getPrdctIdNotFoundCode() {
		return prdctIdNotFoundCode;
	}

	@ManagedAttribute
	public void setPrdctIdNotFoundCode(String prdctIdNotFoundCode) {
		this.prdctIdNotFoundCode = prdctIdNotFoundCode;
	}

	@ManagedAttribute
	public String getPrdctIdNotFoundMsg() {
		return prdctIdNotFoundMsg;
	}

	@ManagedAttribute
	public void setPrdctIdNotFoundMsg(String prdctIdNotFoundMsg) {
		this.prdctIdNotFoundMsg = prdctIdNotFoundMsg;
	}

	@ManagedAttribute
	public String getInvalidRequestCode() {
		return invalidRequestCode;
	}

	@ManagedAttribute
	public void setInvalidRequestCode(String invalidRequestCode) {
		this.invalidRequestCode = invalidRequestCode;
	}

	@ManagedAttribute
	public String getInvalidRequestMsg() {
		return invalidRequestMsg;
	}

	@ManagedAttribute
	public void setInvalidRequestMsg(String invalidRequestMsg) {
		this.invalidRequestMsg = invalidRequestMsg;
	}

	@ManagedAttribute
	public String getGenErrorCode() {
		return genErrorCode;
	}

	@ManagedAttribute
	public void setGenErrorCode(String genErrorCode) {
		this.genErrorCode = genErrorCode;
	}

	@ManagedAttribute
	public String getGenErrorMsg() {
		return genErrorMsg;
	}

	@ManagedAttribute
	public void setGenErrorMsg(String genErrorMsg) {
		this.genErrorMsg = genErrorMsg;
	}
	
	@ManagedAttribute
	public String getPrdctDataNotAvailCode() {
		return prdctDataNotAvailCode;
	}

	@ManagedAttribute
	public void setPrdctDataNotAvailCode(String prdctDataNotAvailCode) {
		this.prdctDataNotAvailCode = prdctDataNotAvailCode;
	}

	@ManagedAttribute
	public String getPrdctDataNotAvailMsg() {
		return prdctDataNotAvailMsg;
	}

	@ManagedAttribute
	public void setPrdctDataNotAvailMsg(String prdctDataNotAvailMsg) {
		this.prdctDataNotAvailMsg = prdctDataNotAvailMsg;
	}
}
