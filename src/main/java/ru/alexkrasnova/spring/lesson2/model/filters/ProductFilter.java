package ru.alexkrasnova.spring.lesson2.model.filters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alexkrasnova.spring.lesson2.dto.validation.ValidProductFilters;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ApiModel("Фильтр для продукта")
public class ProductFilter<T> {

    @ValidProductFilters
    @ApiModelProperty("Поле, по которому происходит фильтрация")
    private String fieldName;

    @ApiModelProperty("Тип операции")
    private OperationType operationType;

    @NotNull
    @ApiModelProperty("Значение")
    private T value;

    public enum OperationType {
        EQUALS, NOT_EQUALS, LIKE, GREATER_THEN, LESS_THEN
    }
}
