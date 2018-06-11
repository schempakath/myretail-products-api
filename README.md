# myRetail Products API Service

RESTful service that 1) retrieves product and price details by Id 2) updates product price by Id.

Technology and Frameworks Used : 

1. Java 8 (JDK 1.8)
2. Spring Boot 2.0.2 
3. MongoDB (version - 3.6)
4. Embedded Tomcat server
5. Apache-maven-3.5.3


Prerequisites : Install latest version of MongoDB and ensure MongoDB server is started. 

#Steps to Build and Run the application in local via commandline:

1. git clone https://github.com/schempakath/myretail-products-api.git

2. cd {cloned directory}/myretail-products-api

3. mvn package

4. cd {cloned directory}/myretail-products-api/target 

5. java -jar -Dspring.profiles.active=prod myretail_products_api-0.0.1-SNAPSHOT.jar
	
Note: Based on env, Profile can be changed in above command

java -jar myretail_products_api-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=dev myretail_products_api-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=qa target/myretailapp-0.0.1-SNAPSHOT.jar

java -jar -Dspring.profiles.active=perf target/myretailapp-0.0.1-SNAPSHOT.jar


#APIs

#GetProductData By ID

Request METHOD:GET

URL: http://localhost:8083/myretail/p/products/{productId} 

Sample URL : http://localhost:8083/myretail/p/products/13860428 

Response:

{
    "product": {
        "id": 13860428,
        "name": "The Big Lebowski (Blu-ray)",
        "current_price": {
            "value": 53.99,
            "currency_code": "USD"
        }
    }
}


#Error Details:

Error Code - 500
Error Message - Product Name and/or Price cannot be retrieved now
Scenario - Product Id  in the request is not present in database or while invoking external api


Error Code - 500
Error Message - Service is unavailable now
Scenario - If there is any generic exception thrown in the application.


#UpdateProductPrice by ID

Request METHOD:PUT

URL: http://localhost:8083/myretail/p/products/{productId} 

Sample URL : http://localhost:8083/myretail/p/products/13860428 

Request Header :

Content-Type : application/json

Request Body :

{
	"productPrice":53.99
}

Response:

{
    "product": {
        "id": 13860428,
        "current_price": {
            "value": 53.99,
            "currency_code": "USD"
        }
    }
}


#Error Details


Error Code - 500
Error Message - Service is unavailable now
Scenario - If there is any generic exception thrown in the application.

Error Code - 404
Error Message - Product Id could not be found
Scenario - Product Id  in the request is not present in database

Error Code - 400
Error Message - Request is not valid
Scenario - productPrice field is not passed in Request


#Testing Results

Tests are run during the build process. Run the below command, to run it separately

mvn test