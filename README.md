## Overview
The Shopping Application is a Spring Boot project designed to manage discount policies and calculate discounts on products. It includes REST endpoint for calculating discounts based on various discount policies.

## Prerequisites
Java Development Kit (JDK) 17: Ensure JDK 17 is installed on your system.
Maven: Maven is used for project build and dependency management. Install it if it's not already available on your machine.

## Build the Project
To build the project and resolve dependencies, run:
mvn clean install

## Running the Application
You can start the application using Maven:
mvn spring-boot:run

## Testing
To execute the unit tests against the business logic service classes please run the following maven command:
mvn test

## To generate a code coverage report using JaCoCo, use the following Maven command:
mvn clean test jacoco:report

## Example Request Using curl
Request with discountType:
URL: http://localhost:8080/api/v1/discount/calculate?discountType=employee

Method: POST

Request Body:
{
"user": {
"type": "employee",
"name": "John Doe",
"registrationDate": "2021-01-15"
},
"products": [
{
"name": "Laptop",
"price": 1000.00,
"category": "ELECTRONICS"
},
{
"name": "Milk",
"price": 10.00,
"category": "GROCERIES"
}
]
}

The response is net payable amount:
659.5

##Implementation and UML:-

<img width="731" alt="UML" src="https://github.com/user-attachments/assets/7a15d67d-5e1e-4849-9a48-87b55657c96b">

