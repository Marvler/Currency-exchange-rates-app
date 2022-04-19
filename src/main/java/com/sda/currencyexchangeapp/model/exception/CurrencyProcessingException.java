package com.sda.currencyexchangeapp.model.exception;

public class CurrencyProcessingException extends RuntimeException {

    public CurrencyProcessingException(final String message) {
        super(message);
    }
}
