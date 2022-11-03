package com.dbank.nace.exceptions;

public class NaceNotFoundException extends RuntimeException {

    public NaceNotFoundException(Long id) {
        super("Could not find NaceID " + id);
    }

    public NaceNotFoundException(String error) {
        super("Error : " + error);
    }
}
