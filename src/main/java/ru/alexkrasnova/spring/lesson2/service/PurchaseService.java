package ru.alexkrasnova.spring.lesson2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexkrasnova.spring.lesson2.model.Purchase;
import ru.alexkrasnova.spring.lesson2.repository.PurchaseDetailingRepository;
import ru.alexkrasnova.spring.lesson2.repository.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    public final PurchaseRepository purchaseRepository;
    public final PurchaseDetailingRepository purchaseDetailingRepository;

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow();
    }

    public Long save(Purchase purchase) {
        //todo: Разобраться, почему не сохраняется детализация заказа
        return purchaseRepository.save(purchase).getId();
    }

    public void deleteById(Long id) {
        purchaseRepository.deleteById(id);
    }
}
