package ru.alexkrasnova.spring.lesson2.model.filters;

import lombok.Getter;
import ru.alexkrasnova.spring.lesson2.dto.validation.ValidProductFilters;

import java.util.List;

@Getter
public class ProductFilters {

    @ValidProductFilters
    List<ProductFilter> productFilters;
}
