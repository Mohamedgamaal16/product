package com.ecommerce.product.error;

public class ProductApiException extends RuntimeException {
    public ProductApiException(String message) {
        super(message);
    }


    public ProductApiException() {
        super();
    }

}
