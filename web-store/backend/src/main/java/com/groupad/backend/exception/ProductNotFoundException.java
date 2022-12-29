package com.groupad.backend.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("Couldn't find product id: " + id);
    }

}
