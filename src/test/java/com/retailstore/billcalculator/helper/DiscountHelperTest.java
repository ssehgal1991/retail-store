package com.retailstore.billcalculator.helper;

import com.retailstore.billcalculator.model.Bill;
import com.retailstore.billcalculator.model.Product;
import com.retailstore.billcalculator.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountHelperTest {

    @Test
    public void testCalculateNonGroceryTotal_WithOnlyGroceryItems() {
        // Arrange
        Product grocery1 = new Product(1, "Milk", 10.0, ProductCategory.GROCERIES);
        Product grocery2 = new Product(2, "Bread", 5.0, ProductCategory.GROCERIES);
        Bill bill = new Bill(null, Arrays.asList(grocery1, grocery2));

        // Act
        double result = DiscountHelper.calculateNonGroceryTotal(bill);

        // Assert
        assertEquals(0.0, result, "Total should be 0.0 when all items are groceries.");
    }

    @Test
    public void testCalculateNonGroceryTotal_WithNoGroceryItems() {
        // Arrange
        Product electronics = new Product(1, "Laptop", 1000.0, ProductCategory.ELECTRONICS);
        Product apparel = new Product(2, "Shirt", 20.0, ProductCategory.APPAREL);
        Bill bill = new Bill(null, Arrays.asList(electronics, apparel));

        // Act
        double result = DiscountHelper.calculateNonGroceryTotal(bill);

        // Assert
        assertEquals(1020.0, result, "Total should be the sum of non-grocery items.");
    }

    @Test
    public void testCalculateNonGroceryTotal_WithMixedItems() {
        // Arrange
        Product electronics = new Product(1, "Laptop", 1000.0, ProductCategory.ELECTRONICS);
        Product grocery = new Product(2, "Milk", 10.0, ProductCategory.GROCERIES);
        Bill bill = new Bill(null, Arrays.asList(electronics, grocery));

        // Act
        double result = DiscountHelper.calculateNonGroceryTotal(bill);

        // Assert
        assertEquals(1000.0, result, "Total should be the sum of non-grocery items.");
    }

    @Test
    public void testCalculateNonGroceryTotal_WithEmptyBill() {
        // Arrange
        Bill bill = new Bill(null, Collections.emptyList());

        // Act
        double result = DiscountHelper.calculateNonGroceryTotal(bill);

        // Assert
        assertEquals(0.0, result, "Total should be 0.0 when the bill has no items.");
    }
}
