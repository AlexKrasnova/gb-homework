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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = CustomerService.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void findAllSuccess() {

        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1L, "Маша");
        Customer customer2 = new Customer(2L, "Саша");
        customers.add(customer1);
        customers.add(customer2);

        Mockito.doReturn(customers).when(customerRepository).findAll();

        List<Customer> actual = customerService.findAll();

        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual.size()).isEqualTo(customers.size());
        Assertions.assertThat(actual.get(0).getId()).isEqualTo(customer1.getId());
        Assertions.assertThat(actual.get(0).getName()).isEqualTo(customer1.getName());
        Assertions.assertThat(actual.get(1).getId()).isEqualTo(customer2.getId());
        Assertions.assertThat(actual.get(1).getName()).isEqualTo(customer2.getName());
    }

}
