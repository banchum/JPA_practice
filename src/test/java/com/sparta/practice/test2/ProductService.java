package com.sparta.practice.test2;

import java.util.List;

public class ProductService {
    private final RecSystemClient client;

    public ProductService(RecSystemClient client) {
        this.client = client;
    }

    public void process(){
        List<Integer> productIds = client.getProducts();
    }
}
