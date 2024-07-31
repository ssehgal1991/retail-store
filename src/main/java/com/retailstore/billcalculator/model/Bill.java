package com.retailstore.billcalculator.model;

import java.util.List;

public class Bill {

    private User user;
    private List<Product> products;

    public Bill() {
    }

    public Bill(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double calculateTotalAmount() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
