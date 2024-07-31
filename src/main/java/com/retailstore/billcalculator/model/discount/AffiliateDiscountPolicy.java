package com.retailstore.billcalculator.model.discount;

import com.retailstore.billcalculator.helper.DiscountHelper;
import com.retailstore.billcalculator.model.Bill;

public class AffiliateDiscountPolicy  implements DiscountPolicy {
    @Override
    public double applyDiscount(Bill bill) {
        double nonGroceryTotal = DiscountHelper.calculateNonGroceryTotal(bill);
        return nonGroceryTotal * 0.10;
    }
}
