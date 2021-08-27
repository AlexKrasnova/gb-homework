package ru.alexkrasnova.spring.lesson2.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.alexkrasnova.spring.lesson2.deserialization.ProductDeserializer;
import ru.alexkrasnova.spring.lesson2.validation.Company;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ProductDeserializer.class)
public class Product {

    private Long id;

    @NotNull
    private String name;

    @Company
    private String company;

    private BigDecimal price;

    public Product(String name, String company, BigDecimal price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + company + " - " + price + " руб";
    }

    public void setId(long id) {
        this.id = id;
    }
}
