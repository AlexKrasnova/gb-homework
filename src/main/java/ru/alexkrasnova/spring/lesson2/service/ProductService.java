package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
