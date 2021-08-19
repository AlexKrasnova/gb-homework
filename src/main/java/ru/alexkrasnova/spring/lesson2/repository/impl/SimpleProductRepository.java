package ru.alexkrasnova.spring.lesson2.repository.impl;

import lombok.Getter;
import ru.alexkrasnova.spring.lesson2.Product;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleProductRepository implements ProductRepository {

    @Getter
    private List<Product> products;

    public Product getProductById(Integer id) {
        for(Product product: products) {
            if(product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @PostConstruct
    public void init() {
        products = new CopyOnWriteArrayList<>();
        products.add(new Product(1, "Молоко", new BigDecimal("89.50")));
        products.add(new Product(2, "Кефир", new BigDecimal("79")));
        products.add(new Product(3, "Ряженка", new BigDecimal("78.90")));
        products.add(new Product(4, "Мацони", new BigDecimal("100")));
        products.add(new Product(5, "Сметана", new BigDecimal("49.50")));
    }
}
