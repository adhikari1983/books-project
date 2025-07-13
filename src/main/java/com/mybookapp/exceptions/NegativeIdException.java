package com.mybookapp.exceptions;

public class NegativeIdException extends RuntimeException{
    public NegativeIdException(String message) {
        super(message);
    }

}
