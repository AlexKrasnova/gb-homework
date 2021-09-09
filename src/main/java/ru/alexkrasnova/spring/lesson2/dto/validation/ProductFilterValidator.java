package ru.alexkrasnova.spring.lesson2.dto.validation;

import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductFilterValidator implements ConstraintValidator<ValidProductFilters, List<ProductFilter>> {

    private final Map<String, String> fields = new HashMap<>();

    @Override
    public void initialize(ValidProductFilters constraintAnnotation) {
        fields.put("name", "String");
        fields.put("company", "String");
        fields.put("price", "Number");
    }

    @Override
    public boolean isValid(List<ProductFilter> productFilters, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = true;
        for (ProductFilter productFilter : productFilters) {
            if (!fields.containsKey(productFilter.getFieldName())) {
                result = false;
            }
            if (fields.get(productFilter.getFieldName()).equals("String")
                    && (productFilter.getOperationType().equals(ProductFilter.OperationType.LESS_THEN) || productFilter.getOperationType().equals(ProductFilter.OperationType.GREATER_THEN))) {
                result = false;
            }
            if (fields.get(productFilter.getFieldName()).equals("Number")
                    && productFilter.getOperationType().equals(ProductFilter.OperationType.LIKE)) {
                result = false;
            }
/*            if (fields.get(productFilter.getFieldName()).equals("String") && !productFilter.getValue().getClass().isInstance(String.class)) {
                result = false;
            }
            if(fields.get(productFilter.getFieldName()).equals("Number") && !productFilter.getValue().getClass().isAssignableFrom(Number.class)) {
                result = false;
            }*/
        }
        return result;
    }
}
