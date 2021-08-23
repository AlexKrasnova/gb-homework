package ru.alexkrasnova.spring.lesson2.exception;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String msg) {
        super(msg);
    }
}
