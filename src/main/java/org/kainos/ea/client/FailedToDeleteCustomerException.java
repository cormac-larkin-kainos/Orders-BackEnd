package org.kainos.ea.client;

public class FailedToDeleteCustomerException extends Exception {
    @Override
    public String getMessage() {
        return "A database error occurred. Failed to delete customer";
    }
}
