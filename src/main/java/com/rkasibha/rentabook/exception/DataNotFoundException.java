package com.rkasibha.rentabook.exception;

import lombok.Data;

@Data
public class DataNotFoundException extends Exception {
    private String message;
}
