package ru.alexkrasnova.spring.lesson2.repository;

import ru.alexkrasnova.spring.lesson2.model.Purchase;

import java.util.List;

public interface PurchaseRepository {

    public List<Purchase> findAll();

    public Purchase findById(Long id);

    Long save(Purchase purchase);
}
