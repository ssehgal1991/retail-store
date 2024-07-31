## Overview
The Shopping Application is a Spring Boot project designed to manage discount policies and calculate discounts on products. It includes REST endpoint for calculating discounts based on various discount policies.

On a retail website, the following discounts apply:
1.	If the user is an employee of the store, he gets a 30% discount
2.	If the user is an affiliate of the store, he gets a 10% discount
3.	If the user has been a customer for over 2 years, he gets a 5% discount.
4.	For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
5.	The percentage based discounts do not apply on groceries.
6.	A user can get only one of the percentage based discounts on a bill.


## Prerequisites
Java Development Kit (JDK) 17: Ensure **JDK 17** is installed on your system.

**Maven**: Maven is used for project build and dependency management. Install it if it's not already available on your machine.

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

## Implementation and UML:-
This project is a bill calculator for a retail store. It involves three primary classes: **Product, User, and Bill**. Below is an explanation of each class and how they interact with each other.

<img width="731" alt="UML" src="https://github.com/user-attachments/assets/7a15d67d-5e1e-4849-9a48-87b55657c96b">

## Class Descriptions

## Product
The Product class represents an item sold in the store. It has Product related information as well as **ProductCategory** and then corresponding getters and setters.

## ProductCategory
This is an enumeration representing different Categories of Products available like **GROCERIES,APPAREL,STATIONERY,ELECTRONICS,FOOTWEAR,OTHER**

## DiscountPolicy
This interface has one method **double applyDiscount(Bill bill)** that represents the applicable discount based on different **User Type**. Using this we are creating **EmployeeDiscountPolicy** (30%),**CustomerDiscountPolicy** (if customer over 2 years 5%), **AffiliateDiscountPolicy** (10%) which provides its own implementation of **applyDiscount** method.

## User 
The User class represents a customer of the retail store. Its a class extended by **Affiliate**,**Customer**,**Employee** also having reference to **DiscountPolicy**.
User can be **Affiliate,Customer or Employee** so these classes extend **User** class and provides their specific **DiscountPolicy** ex-**AffiliateDiscountPolicy**

## Bill
The Bill class represents a bill or invoice for a user, detailing the products purchased. It has the following attributes and method:-

## DiscountPolicyFactory
Its a Factory class for getting the Discount Policy basis the User type for ex- if **User** is an **employee** then **EmployeeDiscountPolicy** will be returned.

## DiscountController
Its a controller that is exposing POST API **/api/v1/discount/calculate** to user to pass **Bill and discountType=(employee,affiliate,customer)** in the request and get the net payable amount after all discounts.It uses **DiscountService** and sets the **Discount Policy** basis the **discountType** received in the request to **DiscountService**

## DiscountService
This is actually doing the business logic to **apply all discounts** and return the **net payable amount** using below method :-
**public double calculateNetPayable(Bill bill)**

## Relationships:-

**Product and Bill:**
A Bill contains a list of Product instances. This is represented as a one-to-many relationship, where one Bill can have multiple Product items.

**User and Bill**:
Each Bill is associated with a single User. This is represented as a one-to-one relationship, where each Bill corresponds to one User.
 



