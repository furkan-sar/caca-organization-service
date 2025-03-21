This project is a scalable and modular backend application developed using Spring Boot.

✅ **RESTful API Support:** Provides fast and secure data exchange with a JSON-based API.  
✅ **Database Integration:** PostgreSQL integration with Spring Data JPA and Hibernate.  
✅ **Spring Validation and Service Layer Checks:** Incoming data to the API endpoints will be validated using both Spring Validation and additional checks in the service layer. While Spring Validation focuses on ensuring data validity and compliance with business rules, the service layer will handle more complex business logic and special rules. This approach ensures that invalid data is not processed and enhances the robustness of the API.    
✅ **Dependency Injection:** Constructor Injection method is used for Dependency Injection. This approach allows the dependencies required by classes to be injected as parameters into the constructor method.    
✅ **Error Handling:** Errors occurring in the application will be caught centrally using the GlobalExceptionHandler class, and JSON responses will be returned in a specific format. This ensures consistent error management with user-friendly messages and error codes.  
✅ **Security:** Basic authentication and authorization mechanisms are provided with Spring Security. JWT-based authentication and access control will be added in future versions.  
✅ **Documentation:** API endpoints can be easily discovered with Swagger. Swagger documentation will be added to the project in future versions.
✅ **Lombok Usage:** The project utilizes the Lombok library to make Java code cleaner and more readable.


## Test Automation:
API endpoints in the project will be extensively tested using RestAssured. Requests made to the API endpoints will be tested to check if they return the correct responses, considering happy path (positive scenarios), negative scenarios (incorrect requests), and business rules (correct data format, boundary values, etc.).

### The tests will focus on the following criteria:

**Happy Path:** Valid requests with correct data will ensure that the API returns the expected correct response.  
**Negative Scenarios:** Tests with invalid data or incorrect requests will check if the API returns appropriate error messages and error codes.  
**Authentication and Authorization:** Access to API endpoints will be tested to ensure that the correct authentication and authorization mechanisms are working properly. Unauthorized access and role-based permissions will be tested.

These tests will ensure that the API functions correctly, securely, and reliably, both under expected conditions and in unexpected scenarios.
