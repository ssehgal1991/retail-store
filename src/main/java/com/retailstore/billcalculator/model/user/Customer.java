package com.retailstore.billcalculator.model.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.retailstore.billcalculator.model.User;
import com.retailstore.billcalculator.model.discount.CustomerDiscountPolicy;

import java.time.LocalDate;

@JsonTypeName("customer")
public class Customer extends User {
    public Customer() {
        // Default constructor required for Jackson
    }
    public Customer(int id, String name, LocalDate registrationDate) {
        super(id, name, registrationDate, new CustomerDiscountPolicy());
    }
}
