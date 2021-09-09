package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.filters.ProductFilter;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {



    /*List<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    void updateById(Long id, Product product);

    List<Product> findByFilters(List<ProductFilter> productFilters);*/
}
