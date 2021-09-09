package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilters;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;
import ru.alexkrasnova.spring.lesson2.repository.specification.ProductSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void updateById(Long id, Product product) {
        Product oldProduct = findById(id);
        oldProduct.setPrice(product.getPrice());
        oldProduct.setName(product.getName());
        oldProduct.setCompany(product.getCompany());
        productRepository.save(oldProduct);
    }

    public List<Product> findByFilters(List<ProductFilter> productFilters) {
        return productRepository.findAll(ProductSpecification.getSpecification(productFilters));
    }
}
