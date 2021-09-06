package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.repository.DatabaseProductRepository;
import ru.alexkrasnova.spring.lesson2.repository.DatabasePurchaseRepository;
import ru.alexkrasnova.spring.lesson2.repository.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    public final PurchaseRepository purchaseRepository;

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Long save(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
