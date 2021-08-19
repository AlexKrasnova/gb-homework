package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.stereotype.Component;
import ru.alexkrasnova.spring.lesson2.Product;

import java.util.List;

@Component
public interface ProductRepository {

    List<Product> getProducts();

    Product getProductById(Integer id);
}
