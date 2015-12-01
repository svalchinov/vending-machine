package com.vendingmachine.model;

import java.util.HashMap;


public class Products {

    private HashMap<Product, Integer> availableProducts = new HashMap<Product, Integer>();

    public Products(HashMap<Product, Integer> products) {
        availableProducts = products;
    }

    public HashMap<Product, Integer> getAvailableProducts() {
        return availableProducts;
    }

    public int findProductCount(Product product) {
        return availableProducts.get(product);
    }
}
