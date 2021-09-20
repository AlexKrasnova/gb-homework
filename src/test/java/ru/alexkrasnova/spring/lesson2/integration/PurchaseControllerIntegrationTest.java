package ru.alexkrasnova.spring.lesson2.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.alexkrasnova.spring.lesson2.dto.ProductDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseDetailingWithProductDetailsDTO;
import ru.alexkrasnova.spring.lesson2.dto.PurchaseWithDetailsDTO;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.model.Product;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;
import ru.alexkrasnova.spring.lesson2.repository.ProductRepository;
import ru.alexkrasnova.spring.lesson2.repository.PurchaseRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PurchaseControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/v1/purchases";

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void tearDown() {
        purchaseRepository.deleteAll();
    }

    @Test
    public void getByIdSuccess() {

        Customer customer = new Customer();
        customer.setName("Мила");
        customerRepository.save(customer);

        Product product = new Product();
        product.setName("Молоко");
        product.setCompany("Агуша");
        product.setPrice(BigDecimal.valueOf(99.9));
        product = productRepository.save(product);

        PurchaseDetailing purchaseDetailing = new PurchaseDetailing();
        purchaseDetailing.setProductId(product.getId());
        purchaseDetailing.setPrice(BigDecimal.valueOf(89.9));
        purchaseDetailing.setNumber(2);
        List<PurchaseDetailing> purchaseDetailings = new ArrayList<>();
        purchaseDetailings.add(purchaseDetailing);

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setDate(Instant.now());
        purchase.setPurchaseDetailings(purchaseDetailings);
        purchaseDetailing.setPurchase(purchase);
        purchaseRepository.save(purchase);

        ResponseEntity<PurchaseWithDetailsDTO> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/" + purchase.getId(), PurchaseWithDetailsDTO.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        PurchaseWithDetailsDTO actualPurchase = actual.getBody();
        Assertions.assertThat(actualPurchase).isNotNull();

        Assertions.assertThat(actualPurchase.getCustomer().getId()).isEqualTo(customer.getId());
        Assertions.assertThat(actualPurchase.getCustomer().getName()).isEqualTo(customer.getName());

        Assertions.assertThat(actualPurchase.getDate()).isEqualTo(purchase.getDate());

        for (PurchaseDetailingWithProductDetailsDTO actualPurchaseDetailing : actualPurchase.getPurchaseDetailings()) {
            Assertions.assertThat(actualPurchaseDetailing.getProduct().getId()).isEqualTo(purchaseDetailing.getProduct().getId());
            Assertions.assertThat(actualPurchaseDetailing.getProduct().getName()).isEqualTo(purchaseDetailing.getProduct().getName());
            Assertions.assertThat(actualPurchaseDetailing.getProduct().getCompany()).isEqualTo(purchaseDetailing.getProduct().getCompany());
            Assertions.assertThat(actualPurchaseDetailing.getPrice()).isEqualTo(purchaseDetailing.getPrice());
            Assertions.assertThat(actualPurchaseDetailing.getNumber()).isEqualTo(purchaseDetailing.getNumber());
        }
    }

    @Test
    public void saveSuccess() {

    }
}
