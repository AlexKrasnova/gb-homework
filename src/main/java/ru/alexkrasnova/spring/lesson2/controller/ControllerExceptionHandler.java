package ru.alexkrasnova.spring.lesson2.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.alexkrasnova.spring.lesson2.exception.CustomerNotFoundException;
import ru.alexkrasnova.spring.lesson2.exception.ProductNotFoundException;
import ru.alexkrasnova.spring.lesson2.exception.PurchaseNotFoundException;

import javax.persistence.NoResultException;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleException(ProductNotFoundException exception) {
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleException(CustomerNotFoundException exception) {
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleException(PurchaseNotFoundException exception) {
    }


}
