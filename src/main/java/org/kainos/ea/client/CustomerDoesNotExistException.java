package org.kainos.ea.client;

public class CustomerDoesNotExistException extends Exception {
    @Override
    public String getMessage() {
        return "No customer with the specified ID exists";
    }
}
