package ru.alexkrasnova.spring.lesson2.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.alexkrasnova.spring.lesson2.dto.CustomerDTO;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;

public class CustomerControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/v1/customers";

    @Autowired
    private CustomerRepository customerRepository;


    @AfterEach
    void tearDown (){
        customerRepository.deleteAll();
    }

    @Test
    public void findByIdSuccess() {
        Customer savesCustomer = new Customer();
        savesCustomer.setName("Васёк");
        customerRepository.save(savesCustomer);
        ResponseEntity<CustomerDTO> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/" +savesCustomer.getId(), CustomerDTO.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actual.getBody()).isNotNull();
        Assertions.assertThat(actual.getBody().getName()).isEqualTo("Васёк");
    }

    @Test
    public void findByIdFail() {
        ResponseEntity<CustomerDTO> actual = restTemplate.getForEntity("http://localhost:" + port + BASE_URL + "/1", CustomerDTO.class);
        Assertions.assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
