package ru.alexkrasnova.spring.lesson2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailingDTO {

    ProductDTO productDTO;
    BigDecimal price;
    Integer number;

}
