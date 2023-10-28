package org.kainos.ea.client;

public class InvalidCustomerException extends Exception {

    private String message;

    public InvalidCustomerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
