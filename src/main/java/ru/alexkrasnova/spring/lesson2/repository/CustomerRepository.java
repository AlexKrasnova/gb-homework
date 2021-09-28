package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexkrasnova.spring.lesson2.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

}
