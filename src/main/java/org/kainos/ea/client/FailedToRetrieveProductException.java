package org.kainos.ea.client;

public class FailedToRetrieveProductException extends Exception {
    @Override
    public String getMessage() {
        return "A database error occurred. Failed to retrieve product";
    }
}
