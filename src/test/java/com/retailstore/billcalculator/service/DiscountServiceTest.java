package com.retailstore.billcalculator.service;

import com.retailstore.billcalculator.model.Bill;
import com.retailstore.billcalculator.model.Product;
import com.retailstore.billcalculator.model.ProductCategory;
import com.retailstore.billcalculator.model.User;
import com.retailstore.billcalculator.model.discount.*;
import com.retailstore.billcalculator.model.user.Affiliate;
import com.retailstore.billcalculator.model.user.Customer;
import com.retailstore.billcalculator.model.user.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class DiscountServiceTest {

    private DiscountService discountService;

    @Mock
    private EmployeeDiscountPolicy employeeDiscountPolicy;

    @Mock
    private AffiliateDiscountPolicy affiliateDiscountPolicy;

    @Mock
    private CustomerDiscountPolicy loyalCustomerDiscountPolicy;

    @Mock
    private NoDiscountPolicy noDiscountPolicy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateNetPayable_EmployeeDiscount() {
        // Arrange
        User employee = new Employee(1, "John Doe", LocalDate.now().minusYears(3));
        List<Product> products = Arrays.asList(
                new Product(1,"Laptop", 1000, ProductCategory.ELECTRONICS),
                new Product(2,"Milk", 10, ProductCategory.GROCERIES)
        );
        Bill bill = new Bill(employee, products);
        when(employeeDiscountPolicy.applyDiscount(bill)).thenReturn(300.0); // 30% of $1000

        discountService = new DiscountService();
        discountService.setDiscountPolicy(employeeDiscountPolicy);

        double totalAmount = bill.calculateTotalAmount();
        double fixedDiscount = (totalAmount / 100) * 5;
        double expectedNetPayable = totalAmount - fixedDiscount - 300.0;

        // Act
        double netPayable = discountService.calculateNetPayable(bill);

        // Assert
        assertEquals(expectedNetPayable, netPayable, 0.01);
    }

    @Test
    public void testCalculateNetPayable_AffiliateDiscount() {
        // Arrange
        User affiliate = new Affiliate(2, "Jane Smith", LocalDate.now().minusYears(1));
        List<Product> products = Arrays.asList(
                new Product(3,"Smartphone", 600, ProductCategory.ELECTRONICS),
                new Product(4,"Bread", 5, ProductCategory.GROCERIES)
        );
        Bill bill = new Bill(affiliate, products);
        when(affiliateDiscountPolicy.applyDiscount(bill)).thenReturn(60.0); // 10% of $600

        discountService = new DiscountService();
        discountService.setDiscountPolicy(affiliateDiscountPolicy);

        double totalAmount = bill.calculateTotalAmount();
        double fixedDiscount = (totalAmount / 100) * 5;
        double expectedNetPayable = totalAmount - fixedDiscount - 60.0;

        // Act
        double netPayable = discountService.calculateNetPayable(bill);

        // Assert
        assertEquals(expectedNetPayable, netPayable, 0.01);
    }

    @Test
    public void testCalculateNetPayable_LoyalCustomerDiscount() {
        // Arrange
        User loyalCustomer = new Customer(3, "Alice Brown", LocalDate.now().minusYears(4));
        List<Product> products = Arrays.asList(
                new Product(5,"Television", 800, ProductCategory.ELECTRONICS),
                new Product(6,"Apples", 15, ProductCategory.GROCERIES)
        );
        Bill bill = new Bill(loyalCustomer, products);
        when(loyalCustomerDiscountPolicy.applyDiscount(bill)).thenReturn(40.0); // 5% of $800

        discountService = new DiscountService();
        discountService.setDiscountPolicy(loyalCustomerDiscountPolicy);

        double totalAmount = bill.calculateTotalAmount();
        double fixedDiscount = (totalAmount / 100) * 5;
        double expectedNetPayable = totalAmount - fixedDiscount - 40.0;

        // Act
        double netPayable = discountService.calculateNetPayable(bill);

        // Assert
        assertEquals(expectedNetPayable, netPayable, 0.01);
    }

    @Test
    public void testCalculateNetPayable_NoDiscount() {
        // Arrange
        User user = new User(4, "Bob White", LocalDate.now(), new NoDiscountPolicy()) {
            @Override
            public double getDiscount(Bill bill) {
                return 0;
            }
        };
        List<Product> products = Arrays.asList(
                new Product(7,"Shirt", 30, ProductCategory.APPAREL),
                new Product(8,"Shoes", 50, ProductCategory.APPAREL)
        );
        Bill bill = new Bill(user, products);
        when(noDiscountPolicy.applyDiscount(bill)).thenReturn(0.0);

        discountService = new DiscountService();
        discountService.setDiscountPolicy(noDiscountPolicy);

        double totalAmount = bill.calculateTotalAmount();
        double fixedDiscount = (totalAmount / 100) * 5;

        // Act
        double netPayable = discountService.calculateNetPayable(bill);
        double expectedNetPayable = netPayable;

        // Assert
        assertEquals(expectedNetPayable, netPayable, 0.01);
    }
}
