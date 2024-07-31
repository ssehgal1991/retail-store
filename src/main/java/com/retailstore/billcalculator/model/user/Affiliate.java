package com.retailstore.billcalculator.model.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.retailstore.billcalculator.model.User;
import com.retailstore.billcalculator.model.discount.AffiliateDiscountPolicy;

import java.time.LocalDate;

@JsonTypeName("affiliate")
public class Affiliate extends User {
    public Affiliate() {
        // Default constructor required for Jackson
    }
    public Affiliate(int id, String name, LocalDate registrationDate) {
        super(id, name, registrationDate, new AffiliateDiscountPolicy());
    }

}
