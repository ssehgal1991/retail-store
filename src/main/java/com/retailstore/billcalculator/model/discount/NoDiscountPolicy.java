package com.retailstore.billcalculator.model.discount;

import com.retailstore.billcalculator.model.Bill;

public class NoDiscountPolicy implements DiscountPolicy{
    @Override
    public double applyDiscount(Bill bill) {
        return 0;
    }
}
