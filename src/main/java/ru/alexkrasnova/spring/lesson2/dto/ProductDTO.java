package ru.alexkrasnova.spring.lesson2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.alexkrasnova.spring.lesson2.dto.deserialization.ProductDeserializer;
import ru.alexkrasnova.spring.lesson2.dto.validation.Company;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = ProductDeserializer.class)
public class ProductDTO {

    private Long id;

    @NotNull
    private String name;

    @Company
    private String company;

    private BigDecimal price;

    public ProductDTO(String name, String company, BigDecimal price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + company + " - " + price + " руб";
    }

}
