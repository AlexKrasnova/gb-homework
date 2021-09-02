package ru.alexkrasnova.spring.lesson2.model.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductFilter<T> {

    private String fieldName;
    private OperationType operationType;
    private T value;

    public enum OperationType {
        EQUALS, NOT_EQUALS, LIKE, GREATER_THEN, LESS_THEN
    }
}
