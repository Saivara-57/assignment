# assignment

 ## Technology and libraries used
 
 - [x] JAVA 1.8
 - [x] Springboot 2.2.5-RELEASE
 - [x] H2 in memory database
 - [x] Maven build
 - [x] Used in memory database H2
 - [x] Swagger 2.X (spring-fox)
 - [x] Jacoco - to generate unit test cases report
 - [x] JUnit and Mockito

## How to run this application 
 * git clone https://github.com/Saivara-57/assignment.git
 * cd assignment
 * mvn clean install
 * cd target 
 * java -jar  product_catalog-1.1.0-SNAPSHOT.jar
 
 ## URL's to launch application
 * http://localhost:8080/h2-console/   (user= centric, no password)
 * http://localhost:8080/swagger-ui.html#/  (Swagger API management URL)
 
 ## Rest endpoint url 
 * http://localhost:8080/v1/products/product/  (post request- to save/update product)
 * http://localhost:8080/v1/products/search/{category}?pageNumber=0&pageSize=1  
            ( get request - for searching and pagination)
 * http://localhost:8080/v1/products/list   ( get request to fetch all the products from database)
 
 
 ## Tasks Status
 
 - [x] API to save a product
 - [x] API to search poducts based on category
 - [x] Pagination by pageNumber and pageSize
 - [x] Handled Exceptions
 - [x] Used in memory database H2
 - [x] Swagger for API management
 - [ ] Unit tests are in progress

 