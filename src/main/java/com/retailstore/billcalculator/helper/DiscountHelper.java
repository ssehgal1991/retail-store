package com.retailstore.billcalculator.helper;

import com.retailstore.billcalculator.model.Bill;
import com.retailstore.billcalculator.model.Product;
import com.retailstore.billcalculator.model.ProductCategory;

public class DiscountHelper {
    public static double calculateNonGroceryTotal(Bill bill) {
        return bill.getProducts().stream()
                .filter(product -> product.getCategory() != ProductCategory.GROCERIES)
                .mapToDouble(Product::getPrice)
                .sum();
    }
}
