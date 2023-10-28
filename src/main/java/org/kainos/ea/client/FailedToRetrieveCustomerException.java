package org.kainos.ea.client;

public class FailedToRetrieveCustomerException extends Exception {
    @Override
    public String getMessage() {
        return "A database error occurred. Unable to retrieve customer";
    }
}
