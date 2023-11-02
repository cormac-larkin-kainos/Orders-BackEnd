package org.kainos.ea.client;

public class InvalidProductException extends Exception {

    private String message;

    public InvalidProductException(String message) {
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
