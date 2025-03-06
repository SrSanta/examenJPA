package com.example.examenjpa.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Not found Product with id: " + id);
    }
}
