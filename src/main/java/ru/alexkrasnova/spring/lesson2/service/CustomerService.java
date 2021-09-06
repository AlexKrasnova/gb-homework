package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.model.Customer;
import ru.alexkrasnova.spring.lesson2.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
