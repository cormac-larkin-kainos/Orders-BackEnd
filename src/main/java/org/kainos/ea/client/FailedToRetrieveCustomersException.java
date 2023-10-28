package org.kainos.ea.client;

public class FailedToRetrieveCustomersException extends Exception {

    @Override
    public String getMessage() {
        return "A database error occurred. Unable to retrieve customers";
    }
}
