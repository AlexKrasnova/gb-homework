package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return name + ", " + price + " руб";
    }

}
