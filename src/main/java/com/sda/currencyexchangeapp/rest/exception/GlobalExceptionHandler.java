package com.sda.currencyexchangeapp.rest.exception;

import com.sda.currencyexchangeapp.model.gold.CurrencyProcessingException;
import com.sda.currencyexchangeapp.model.gold.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CurrencyProcessingException.class)
    public ErrorResponse handleCurrencyProcessingException(final CurrencyProcessingException exception) {
        log.debug("Exception occurred");
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoResultException.class)
    public ErrorResponse handleGoldProcessingException(final NoResultException exception) {
        log.debug("Exception occurred");
        return new ErrorResponse(exception.getMessage());
    }
}
