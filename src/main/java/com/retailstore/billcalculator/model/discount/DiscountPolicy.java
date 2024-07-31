package com.retailstore.billcalculator.model.discount;

import com.retailstore.billcalculator.model.Bill;

public interface DiscountPolicy {
    double applyDiscount(Bill bill);
}
