package ru.alexkrasnova.spring.lesson2.repository;

import ru.alexkrasnova.spring.lesson2.model.Product;
import java.util.List;

public interface ProductRepository {

    List<Product> getProducts();

    Product getProductById(String id);
}
