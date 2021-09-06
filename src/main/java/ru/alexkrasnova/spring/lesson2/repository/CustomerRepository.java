package ru.alexkrasnova.spring.lesson2.repository;

import ru.alexkrasnova.spring.lesson2.model.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findAll();

    Customer findById(Long id);

    void deleteById(Long id);

    void save(Customer customer);
}
