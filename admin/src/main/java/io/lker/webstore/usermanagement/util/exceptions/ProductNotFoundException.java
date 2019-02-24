package io.lker.webstore.usermanagement.util.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id){
        super("Could not find product: " + id);
    }
}
