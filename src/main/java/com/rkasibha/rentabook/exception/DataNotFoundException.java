package com.rkasibha.rentabook.exception;

import lombok.Getter;


public class DataNotFoundException extends Exception {

    @Getter
    private String message;

    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
