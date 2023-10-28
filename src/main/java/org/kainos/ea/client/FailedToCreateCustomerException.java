package org.kainos.ea.client;

public class FailedToCreateCustomerException extends Exception {

    @Override
    public String getMessage() {
        return "A database error occurred. Unable to create customer.";
    }
}
