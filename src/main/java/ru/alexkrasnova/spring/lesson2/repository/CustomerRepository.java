package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexkrasnova.spring.lesson2.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
