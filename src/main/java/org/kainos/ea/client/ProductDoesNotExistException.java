package org.kainos.ea.client;

public class ProductDoesNotExistException extends Exception {

    @Override
    public String getMessage() {
        return "No product with the specified ID exists";
    }
}
