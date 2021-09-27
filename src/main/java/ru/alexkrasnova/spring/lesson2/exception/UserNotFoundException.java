package ru.alexkrasnova.spring.lesson2.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}
