package ru.alexkrasnova.spring.lesson2.repository.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter.OperationType.EQUALS;

@UtilityClass
public class ProductSpecification {

    public Specification<Product> getSpecification(List<ProductFilter> productFilters) {
        return productFilters.stream()
                .map(it -> {
                    final ProductFilter.OperationType operationType = it.getOperationType();
                    final String fieldName = it.getFieldName();
                    switch (operationType) {
                        case EQUALS: {
                            if ("name".equals(fieldName)) {
                                return nameEq((String) it.getValue());
                            } else if ("company".equals(fieldName)) {
                                return companyEq((String) it.getValue());
                            } else if ("price".equals(fieldName)) {
                                return priceEq(new BigDecimal(it.getValue().toString()));
                            }
                            break;
                        }
                        case GREATER_THEN: {
                            if ("price".equals(fieldName)) {
                                return priceGt(new BigDecimal(it.getValue().toString()));
                            }
                            break;
                        }
                        case LESS_THEN: {
                            if ("price".equals(fieldName)) {
                                return priceLt(new BigDecimal(it.getValue().toString()));
                            }
                            break;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .reduce(Specification::and)
                .orElse(null);
    }

    private static Specification<Product> nameEq(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    private static Specification<Product> companyEq(String company) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("company"), company);
    }

    private static Specification<Product> priceEq(Number price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("company"), price);
    }

    private static Specification<Product> nameLike(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Product> priceGt(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), price);
    }

    private static Specification<Product> priceLt(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), price);
    }


}
