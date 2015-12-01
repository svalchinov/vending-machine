package com.vendingmachine.model;


import java.util.List;

public class Purchase {
    private Product product;
    private List<Coin> availableChange;

    public Purchase(Product product, List<Coin> change) {
        this.product = product;
        this.availableChange = change;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Coin> getAvailableChange() {
        return availableChange;
    }

    public void setAvailableChange(List<Coin> availableChange) {
        this.availableChange = availableChange;
    }
}
