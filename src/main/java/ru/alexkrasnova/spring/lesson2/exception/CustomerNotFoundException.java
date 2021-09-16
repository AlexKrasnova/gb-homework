package ru.alexkrasnova.spring.lesson2.exception;

import ru.alexkrasnova.spring.lesson2.model.Customer;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Покупатель не найден");
    }
}
