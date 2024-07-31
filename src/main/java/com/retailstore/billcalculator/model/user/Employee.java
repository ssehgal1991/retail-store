package com.retailstore.billcalculator.model.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.retailstore.billcalculator.model.User;
import com.retailstore.billcalculator.model.discount.EmployeeDiscountPolicy;

import java.time.LocalDate;

@JsonTypeName("employee")
public class Employee extends User {
    public Employee() {
        // Default constructor required for Jackson
    }

    public Employee(int id, String name, LocalDate registrationDate) {
        super(id, name, registrationDate, new EmployeeDiscountPolicy());
    }
}
