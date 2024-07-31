package com.retailstore.billcalculator.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.retailstore.billcalculator.model.discount.DiscountPolicy;
import com.retailstore.billcalculator.model.user.Affiliate;
import com.retailstore.billcalculator.model.user.Customer;
import com.retailstore.billcalculator.model.user.Employee;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Employee.class, name = "employee"),
        @JsonSubTypes.Type(value = Affiliate.class, name = "affiliate"),
        @JsonSubTypes.Type(value = Customer.class, name = "customer")
})
public abstract class User {
    private int id;
    private String name;
    private LocalDate registrationDate;
    private DiscountPolicy discountPolicy;

    public User() {
    }

    public User(int id, String name, LocalDate registrationDate,DiscountPolicy discountPolicy) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.discountPolicy = discountPolicy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public double getDiscount(Bill bill) {
        return discountPolicy.applyDiscount(bill);
    }

}
