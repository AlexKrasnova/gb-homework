package ru.alexkrasnova.spring.lesson2.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;

import java.math.BigDecimal;

public class ProductControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/v1/products";

    @Autowired
    private ProductRepository productRepository;


    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void findAllSuccess() {
        Product product1 = new Product();
        product1.setName("Молоко");
        product1.setCompany("Агуша");
        product1.setPrice(BigDecimal.valueOf(99.9));
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Кефир");
        product2.setCompany("Домик в деревне");
        product2.setPrice(BigDecimal.valueOf(55.5));
        productRepository.save(product2);

        ResponseEntity<ProductDTO[]> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL, ProductDTO[].class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isNotNull();

        Assertions.assertThat(actual.getBody()[0].getId()).isEqualTo(product1.getId());
        Assertions.assertThat(actual.getBody()[0].getName()).isEqualTo(product1.getName());
        Assertions.assertThat(actual.getBody()[0].getCompany()).isEqualTo(product1.getCompany());
        Assertions.assertThat(actual.getBody()[0].getPrice()).isEqualTo(product1.getPrice());

        Assertions.assertThat(actual.getBody()[1].getId()).isEqualTo(product2.getId());
        Assertions.assertThat(actual.getBody()[1].getName()).isEqualTo(product2.getName());
        Assertions.assertThat(actual.getBody()[1].getCompany()).isEqualTo(product2.getCompany());
        Assertions.assertThat(actual.getBody()[1].getPrice()).isEqualTo(product2.getPrice());
    }

}
