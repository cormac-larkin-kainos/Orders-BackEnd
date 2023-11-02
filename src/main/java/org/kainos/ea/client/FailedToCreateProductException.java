package org.kainos.ea.client;

public class FailedToCreateProductException extends Exception {
    @Override
    public String getMessage() {
        return "A database error occurred. Failed to add product";
    }
}
