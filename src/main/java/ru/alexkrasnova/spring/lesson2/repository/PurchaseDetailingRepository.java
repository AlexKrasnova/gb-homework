package ru.alexkrasnova.spring.lesson2.repository;

import ru.alexkrasnova.spring.lesson2.model.PurchaseDetailing;

public interface PurchaseDetailingRepository {

    void save(PurchaseDetailing purchaseDetailing);
}
