package ru.alexkrasnova.spring.lesson2.exception;

import ru.alexkrasnova.spring.lesson2.model.Purchase;

public class PurchaseNotFoundException extends RuntimeException {
    public PurchaseNotFoundException() {
        super("Заказ не найден");
    }
}
