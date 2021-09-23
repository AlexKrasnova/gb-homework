package ru.alexkrasnova.spring.lesson11.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Пользователь не найден");
    }

}
