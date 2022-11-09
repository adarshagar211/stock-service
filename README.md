# Stock Service - Microservice

Stock Service - Microservice production ready Springboot Microservice built using Spring 5 / Java 11 . Its a simple microservice which performs CRUD operations on stocks data through REST endpoints .

![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master) ![License](http://img.shields.io/:license-apache-blue.svg)

## Requirements

For building and running the application you need:
 
- Java 11
- Spring 5
- Maven 3+ or higher
- Git

## About the project
 
 Its created as a SpringBoot production ready application which runs on embedded Tomcat server. This project can simply be imported as maven project and can run on Eclipse or IntelliJ or run directly using below command. 
 
 ```shell
mvn spring-boot:run
```
It has external dependencies of PostGRE DB connection which should be running on localhost for starting this microservice. 

 Five APi's built : 
 - Get all stocks            -  GET /api/stocks
 - Add a newstock            - POST /api/stocks
 - Get a particular stock    - GET /api/stocks/{id}
 - Delete a particular stock - DELETE /api/stocks/{id}
 - Update a particular stock - PATCH /api/stocks/{id}
 
Added Unit test cases on service and controller layer for code coverage

API thows Exception Error  : 
- Incase of validation failure on server - Response status is 400 Bad Request. 
- Incase of data not present - Response status is 404 Not Found. 
- Incase of unexpected error on server - Response status is 500 Internal Server Error

Please check swagger URL for testing and more documentation on API's

## Running test cases

Run a Spring Boot test cases

There are several ways to run a tests on your local machine. One way is to execute the `Run as-> Junit Test` on src/test/java folder from STS IDE.

Other way is to run below command on root folder : 

```shell
mvn clean test
```

## Running the application locally

Prerequisite : Either run application on docker engine using script or have postgres db running on "localhost" with properties in project

To run a Spring Boot application on your local machine you can execute `main` method in the `com.payconiq.StocksApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Run on docker

Install docker engine on your local system and run below script in stocks-api/ folder to start the application

```shell
docker-compose up
```

## After running the application

Once Application is up and running on docker or on jvm , check the API documentation on below swagger url : 

```shell
Swagger URL  :  http://localhost:8080/docs
Swagger URL  :  http://localhost:8080/swagger-ui/index.html
```

## Scope for Improvements

We could add spring security like token, OAuth, make it reactive, add more test cases, add caching and feature wise make application more dynamic .

### Contact

Adarsh Agarwal - adarsh.agar211@gmail.com

## Copyright

Released under the Apache License 2.0. See the [LICENSE] file.
