package com.retailstore.billcalculator.service;

import com.retailstore.billcalculator.helper.DiscountHelper;
import com.retailstore.billcalculator.model.Bill;
import com.retailstore.billcalculator.model.Product;
import com.retailstore.billcalculator.model.ProductCategory;
import com.retailstore.billcalculator.model.discount.DiscountPolicy;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    private DiscountPolicy discountPolicy;

    public DiscountService() {}

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public double calculateNetPayable(Bill bill) {
        // Calculate the total amount for all products
        double totalAmount = bill.calculateTotalAmount();

        // Calculate the fixed discount
        double fixedDiscount = calculateFixedDiscount(totalAmount);

        // Apply the discount policy
        double percentageDiscountAmount = discountPolicy.applyDiscount(bill);

        // Calculate the net payable amount
        double netPayable = totalAmount - fixedDiscount - percentageDiscountAmount;

        return netPayable;
    }

    private double calculateFixedDiscount(double totalAmount) {
        if(totalAmount>100) {
            return (totalAmount / 100) * 5; // $5 discount per $100
        }
        return 0.0;
    }

}
