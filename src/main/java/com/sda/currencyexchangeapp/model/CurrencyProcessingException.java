package com.sda.currencyexchangeapp.model;

public class CurrencyProcessingException extends RuntimeException {
    public CurrencyProcessingException(final String message) {
        super(message);
    }
}