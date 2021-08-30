package ru.alexkrasnova.spring.lesson2.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.exception.NoSuchProductException;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class SimpleProductRepository implements ProductRepository {


    @Getter
    private Map<Long, Product> products;

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(Long id) {
        if (products.containsKey(id)) {
            return products.get(id);
        }
        throw new NoSuchProductException("Продукта с таким id не существует.");
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(products.size() + 1L);
        }
        products.put(product.getId(), product);
    }

    @PostConstruct
    public void init() {
        products = new ConcurrentHashMap<>();
        products.put(1L, new Product(1L, "Молоко", "Домик в деревне", new BigDecimal("89.50")));
        products.put(2L, new Product(2L, "Кефир", "Простоквашино", new BigDecimal("79")));
        products.put(3L, new Product(3L, "Ряженка", "Valio", new BigDecimal("78.90")));
        products.put(4L, new Product(4L, "Мацони", "Домик в деревне", new BigDecimal("100")));
        products.put(5L, new Product(5L, "Сметана", "Простоквашино", new BigDecimal("49.50")));
    }
}
