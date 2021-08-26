package ru.alexkrasnova.spring.lesson2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.alexkrasnova.spring.lesson2.validation.Company;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {

    private Long id;

    @NotNull
    private String name;

    @Company
    private String company;

    private BigDecimal price;

    @Override
    public String toString() {
        return name + ", " + company + " - " + price + " руб";
    }

    public void setId(long id) {
        this.id = id;
    }
}
