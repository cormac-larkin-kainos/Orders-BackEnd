package org.kainos.ea.client;

public class FailedToUpdateCustomerException extends Exception {
    @Override
    public String getMessage() {
        return "A database error occurred. Failed to update customer";
    }
}
