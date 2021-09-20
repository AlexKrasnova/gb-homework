package ru.alexkrasnova.spring.lesson2.unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;
import ru.alexkrasnova.spring.lesson2.service.CustomerService;

import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = CustomerService.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void findAllSuccess() {
        Mockito.doReturn(Collections.emptyList()).when(customerRepository).findAll();
        List<Customer> actual = customerService.findAll();
        Assertions.assertThat(actual).isEmpty();
    }

}
