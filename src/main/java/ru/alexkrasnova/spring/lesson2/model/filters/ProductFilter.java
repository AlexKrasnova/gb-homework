package ru.alexkrasnova.spring.lesson2.model.filters;

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
public class ProductFilter<T> {

    @ValidProductFilters
    private String fieldName;

    private OperationType operationType;

    @NotNull
    private T value;

    public enum OperationType {
        EQUALS, NOT_EQUALS, LIKE, GREATER_THEN, LESS_THEN
    }
}
