package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
