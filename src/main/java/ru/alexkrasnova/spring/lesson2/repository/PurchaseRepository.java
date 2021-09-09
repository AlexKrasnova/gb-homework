package ru.alexkrasnova.spring.lesson2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alexkrasnova.spring.lesson2.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
