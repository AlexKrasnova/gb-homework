package ru.alexkrasnova.spring.lesson2.cart;

public interface Cart {

    void removeProductById(String id);

    void addProductById(String id);

    String toString();
}
