package com.example.product.exception;

public class ProductCatalogException  extends RuntimeException {

    public ProductCatalogException() {
        super();
    }

    public ProductCatalogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ProductCatalogException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCatalogException(String message) {
        super(message);
    }

    public ProductCatalogException(Throwable cause) {
        super(cause);
    }
}
