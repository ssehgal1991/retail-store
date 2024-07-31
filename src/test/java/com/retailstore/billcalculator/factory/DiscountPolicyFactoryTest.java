package com.retailstore.billcalculator.factory;

import com.retailstore.billcalculator.model.discount.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiscountPolicyFactoryTest {

    @Test
    public void testGetDiscountPolicy_Employee() {
        DiscountPolicy policy = DiscountPolicyFactory.getDiscountPolicy("employee");
        assertTrue(policy instanceof EmployeeDiscountPolicy);
    }

    @Test
    public void testGetDiscountPolicy_Affiliate() {
        DiscountPolicy policy = DiscountPolicyFactory.getDiscountPolicy("affiliate");
        assertTrue(policy instanceof AffiliateDiscountPolicy);
    }

    @Test
    public void testGetDiscountPolicy_Loyal() {
        DiscountPolicy policy = DiscountPolicyFactory.getDiscountPolicy("loyal");
        assertTrue(policy instanceof CustomerDiscountPolicy);
    }

    @Test
    public void testGetDiscountPolicy_NoDiscount() {
        DiscountPolicy policy = DiscountPolicyFactory.getDiscountPolicy(null);
        assertTrue(policy instanceof NoDiscountPolicy);

        policy = DiscountPolicyFactory.getDiscountPolicy("");
        assertTrue(policy instanceof NoDiscountPolicy);
    }
}
