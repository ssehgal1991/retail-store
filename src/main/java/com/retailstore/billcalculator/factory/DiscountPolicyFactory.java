package com.retailstore.billcalculator.factory;

import com.retailstore.billcalculator.model.discount.*;

public class DiscountPolicyFactory {
    public static DiscountPolicy getDiscountPolicy(String discountType) {
        switch (discountType != null ? discountType : "") {
            case "employee":
                return new EmployeeDiscountPolicy();
            case "affiliate":
                return new AffiliateDiscountPolicy();
            case "loyal":
                return new CustomerDiscountPolicy();
            default:
                return new NoDiscountPolicy();
        }
    }
}
