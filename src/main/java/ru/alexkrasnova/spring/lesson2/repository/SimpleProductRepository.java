package ru.alexkrasnova.spring.lesson2.repository;

import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.exception.NoSuchProductException;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SimpleProductRepository implements ProductRepository {

    @Getter
    private List<Product> products;

    @Override
    public Product getProductById(String id) {
        for(Product product: products) {
            if(product.getId().equals(id)) {
                return product;
            }
        }
        throw new NoSuchProductException("Продукта с таким id не существует.");
    }

    @PostConstruct
    public void init() {
        products = new CopyOnWriteArrayList<>();
        products.add(new Product("1", "Молоко", new BigDecimal("89.50")));
        products.add(new Product("2", "Кефир", new BigDecimal("79")));
        products.add(new Product("3", "Ряженка", new BigDecimal("78.90")));
        products.add(new Product("4", "Мацони", new BigDecimal("100")));
        products.add(new Product("5", "Сметана", new BigDecimal("49.50")));
    }
}
