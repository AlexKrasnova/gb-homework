package ru.alexkrasnova.spring.lesson2.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Продукт не найден");
    }
}
