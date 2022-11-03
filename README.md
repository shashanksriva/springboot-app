# springboot-app

This is an spring boot application to demonstrate CRUD operations on sample NACE data. Exposes its api through implementation of Swagger-UI. 

First NACE data(nace-data.csv) needs to be uploaded to application DB using localhost:8080/api/csv/upload option through postman. If data is not present in DB appropriate error message will be displayed.

Application supports: Get, Post, Delete operation. 

Test cases are implemented using JUnit and Mockito.

