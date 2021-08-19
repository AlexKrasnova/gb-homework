package ru.alexkrasnova.spring.lesson2;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;


}
