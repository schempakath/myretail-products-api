package com.myretail.productsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = {"com.myretail.productsapi.*"})

@SpringBootApplication
public class MyRetailProductsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailProductsApiApplication.class, args);
	}
}
