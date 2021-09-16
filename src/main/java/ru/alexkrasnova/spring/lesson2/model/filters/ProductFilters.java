package ru.alexkrasnova.spring.lesson2.model.filters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import ru.alexkrasnova.spring.lesson2.dto.validation.ValidProductFilters;

import java.util.List;

@Getter
@ApiModel("Список фильтров для продукта")
public class ProductFilters {

    @ValidProductFilters
    @ApiModelProperty("Список фильтров")
    List<ProductFilter> productFilters;
}
