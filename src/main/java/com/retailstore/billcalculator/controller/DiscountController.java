package com.retailstore.billcalculator.controller;

import com.retailstore.billcalculator.factory.DiscountPolicyFactory;
import com.retailstore.billcalculator.model.Bill;
import com.retailstore.billcalculator.model.discount.DiscountPolicy;
import com.retailstore.billcalculator.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate")
    public double calculateDiscount(@Valid @RequestBody Bill bill,
                                    @RequestParam(required = false) String discountType) {
        // Get the appropriate discount policy using the factory
        DiscountPolicy policy = DiscountPolicyFactory.getDiscountPolicy(discountType);
        discountService.setDiscountPolicy(policy);

        // Calculate and return the net payable amount
        return discountService.calculateNetPayable(bill);
    }
}
