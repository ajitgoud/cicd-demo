package com.blackgoku.cicddemo.product;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    private final AtomicLong idGenerator = new AtomicLong(3);

    public ProductService() {
        products.add(new Product(1L, "Laptop", 75000));
        products.add(new Product(2L, "Keyboard", 2500));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {

        product.setId(idGenerator.getAndIncrement());

        products.add(product);

        return product;
    }

    public void deleteProduct(Long id) {

        products.removeIf(product ->
                product.getId().equals(id));
    }
}